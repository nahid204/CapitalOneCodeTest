package data;

import util.DateMonth;
import util.InvalidDateException;
import util.InvalidDateMonthStringException;
import util.NoDataException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class DataAnalysis {
    private ArrayList<SecurityCalculation> securityCalculations;
    private DateMonth dateMonthFrom;
    private DateMonth dateMonthTo;

    public DataAnalysis(String codes, DateMonth dateMonthFrom, DateMonth dateMonthTo)
            throws InvalidDateMonthStringException,
            InvalidDateException, ParseException, NoDataException, org.json.simple.parser.ParseException, IOException {

        this.securityCalculations = new ArrayList<SecurityCalculation>();
        String[] codeSplit = codes.split(" ");

        this.dateMonthFrom =dateMonthFrom;
        this.dateMonthTo = dateMonthTo;

        for(String code: codeSplit)
        {
            try
            {
                String ticker = code.trim();
                SecurityCalculation security = new SecurityCalculation(ticker, this.dateMonthFrom, this.dateMonthTo);
                this.securityCalculations.add(security);
            }
            catch(NoDataException e)
            {
                System.out.println("No data found for "+code);

            }

        }
    }
    public String getReport()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"data\":[");

        for(SecurityCalculation sec: securityCalculations)
        {
            sb.append(sec.toString());
            sb.append(",\r\n");
        }
        sb.append("]}");
        return sb.toString();

    }



    public String getBusyDayReport()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"data\":[");

        for(SecurityCalculation sec: securityCalculations)
        {
            sb.append(sec.getBusyDaysReport());
            sb.append(",\r\n");
        }
        sb.append("]}");
        return sb.toString();

    }

    public String getBiggestLoserReport()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"data\":[");

        ArrayList<SecurityCalculation> list = new ArrayList<SecurityCalculation>(securityCalculations);
        Collections.sort(list, new Comparator<SecurityCalculation>() {
            @Override
            public int compare(SecurityCalculation o1, SecurityCalculation o2) {
                return o2.getLosingDaysCount() - o1.getLosingDaysCount();
            }
        });
        for(SecurityCalculation sec: list)
        {
            sb.append(sec.getLosingDaysReport());
            sb.append(",\r\n");
        }
        sb.append("]}");
        return sb.toString();

    }



}
