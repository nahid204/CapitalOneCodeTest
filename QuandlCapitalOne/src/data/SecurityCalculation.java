package data;

import org.json.simple.parser.ParseException;
import util.DateMonth;
import util.InvalidDateException;
import util.NoDataException;

import java.io.IOException;
import java.util.ArrayList;

public class SecurityCalculation {

    private String ticker;

    private double averageVolume;
    private int losingDays=-1;
    private ArrayList<MonthlyReport> monthlyReports =new ArrayList<MonthlyReport>();

    public SecurityCalculation(String ticker, DateMonth from, DateMonth to)
            throws InvalidDateException, ParseException, java.text.ParseException, IOException, NoDataException {
        this.ticker = ticker;

        ArrayList<DateMonth> dateMonths = DateMonth.getDateMonths(from , to);

        double volumeSum =0;
        int count=0;
        for(DateMonth dm : dateMonths)
        {
            MonthlyReport report = new MonthlyReport(ticker, dm);
            monthlyReports.add(report);
            volumeSum+= report.getAverageVolume()*report.getRecordCount();
            count+= report.getRecordCount();
        }

        this.averageVolume = volumeSum/count;
    }

    public ArrayList<Entity> getBusyDays()
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for(MonthlyReport report: monthlyReports)
        {
            list.addAll(report.getBusyDays(this.averageVolume));
        }

        return list;
    }
    public ArrayList<Entity> getLosingDays()
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for(MonthlyReport report: monthlyReports)
        {
            list.addAll(report.getLosingDays());
        }

        return list;
    }
    public int getLosingDaysCount()
    {
        if(this.losingDays<0)
        {
            int count =0;
            for(MonthlyReport report: monthlyReports)
            {
                count+= report.getLosingDays().size();
            }

            this.losingDays = count;
        }

        return this.losingDays;

    }
    public String getLosingDaysReport()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"ticker\":\""+this.ticker+"\", \"total_losing_days\":"+getLosingDaysCount()+"}");

        return sb.toString();
    }
    public String getBusyDaysReport()
    {
        ArrayList<Entity> list = getBusyDays();
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"ticker\":\""+this.ticker+"\", \"average_volume\":"+String.format("%.2f", this.averageVolume)+", ");
        sb.append("\"busy_days\":[");
        for(Entity e: list)
        {
            sb.append("{\"date\":\""+e.getDateString()+"\", \"volume\":"+String.format("%.2f", e.getVolume())+"}");
        }
        sb.append("]}");

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"name\":\""+this.ticker+"\", \"data\":[");
        for(MonthlyReport report: monthlyReports)
        {
            sb.append(report);
            sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}
