package data;

import org.json.simple.parser.ParseException;
import util.DateMonth;
import util.DeserializationUtil;
import util.NoDataException;

import java.io.IOException;
import java.util.ArrayList;


public class MonthlyReport {
    private String ticker;
    private DateMonth dateMonth;
    private double averageOpen;
    private double averageClose;
    private double averageVolume;
    private int recordCount =0;

    private ArrayList<Entity> dataEntities;
    public MonthlyReport(String ticker, DateMonth dateMonth) throws ParseException, java.text.ParseException, IOException, NoDataException {
        this.ticker = ticker;

        this.dateMonth = dateMonth;
        calculate();
    }
    private void calculate() throws ParseException, java.text.ParseException, IOException, NoDataException {
        this.dataEntities = DeserializationUtil.getDataEntities(ticker, dateMonth);

        double sumOpen = 0;
        double sumClose = 0;
        double sumVolume = 0;
        recordCount = dataEntities.size();
        for(Entity e: dataEntities)
        {
            sumOpen+= e.getOpenPrice();
            sumClose+= e.getClosePrice();
            sumVolume+= e.getVolume();
        }
        this.averageOpen = sumOpen/recordCount;
        this.averageClose = sumClose/recordCount;
        this.averageVolume = sumVolume/recordCount;
    }

    public double getAverageOpen() {
        return averageOpen;
    }

    public double getAverageClose() {
        return averageClose;
    }

    public double getAverageVolume() {
        return averageVolume;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public ArrayList<Entity> getBusyDays(double securityAverageVolume)
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        double limit = securityAverageVolume * 1.1;
        for(Entity e: dataEntities)
        {
            if(e.getVolume()> limit)
            {
                list.add(e);
            }
        }

        return list;
    }

    public ArrayList<Entity> getLosingDays()
    {
        ArrayList<Entity> list = new ArrayList<Entity>();
        for(Entity e: dataEntities)
        {
            if(e.getClosePrice()<e.getOpenPrice())
            {
                list.add(e);
            }
        }

        return list;
    }


    @Override
    public String toString() {
        String monthString = "\"month\":\""+dateMonth+"\"";
        String averageOpenString ="\"average_open\":\""+String.format("%.2f", this.averageOpen)+"\"";
        String averageCloseString ="\"average_close\":\""+String.format("%.2f", this.averageClose)+"\"";
        String averageVolumeString ="\"average_volume\":\""+String.format("%.2f", this.averageVolume)+"\"";

        return "{"+monthString+", "+averageOpenString+", "
                +averageCloseString+","+averageVolumeString+"}";

    }
}
