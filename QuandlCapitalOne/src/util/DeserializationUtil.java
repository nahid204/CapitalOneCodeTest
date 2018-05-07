package util;

import data.Entity;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;

public class DeserializationUtil {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static ArrayList<Entity> getDataEntities(String ticker, DateMonth dateMonth)
            throws IOException,
            ParseException,
            java.text.ParseException ,
            NoDataException
    {
        String data = DataUtil.getData(ticker, dateMonth);
        return getDataEntities(data);
    }

    public static ArrayList<Entity> getDataEntities(String data)
            throws ParseException, java.text.ParseException, NoDataException
    {
        JSONObject obj = (JSONObject) new JSONParser().parse(data);
        JSONObject dataTable = (JSONObject) obj.get("datatable");
        JSONArray dataArray = (JSONArray) dataTable.get("data");

        ArrayList<Entity> list = new ArrayList<Entity>();

        ListIterator iterator = dataArray.listIterator();
        if(!iterator.hasNext())
        {
            throw new NoDataException();
        }
        while(iterator.hasNext())
        {
            JSONArray row = (JSONArray) iterator.next();
            ListIterator rowIterator = row.listIterator();
            //-----
            String ticker = (String)rowIterator.next();
            Entity entity = new Entity(ticker);
            //--
            String dateString = (String) rowIterator.next();
            Date date = DATE_FORMAT.parse(dateString);
            entity.setDate(date);
            //-- Prices
            double openPrice = (double)rowIterator.next();
            entity.setOpenPrice(openPrice);
            double highPrice = (double)rowIterator.next();
            entity.setHighPrice(highPrice);
            double lowPrice = (double)rowIterator.next();
            entity.setLowPrice(lowPrice);
            double closePrice = (double)rowIterator.next();
            entity.setClosePrice(closePrice);
            double volume = (double)rowIterator.next();
            entity.setVolume(volume);


            list.add(entity);

        }
        return list;

    }


}
