package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    public static final String API_KEY="s-GMZ_xkw6CrkGYUWs1p";
    public static final String URL_FORMAT="https://www.quandl.com/api/v3/datatables/WIKI/PRICES?ticker=%s&date=%s&api_key=%s";

    public static final int[] DAYS = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

    public static String getData(String ticker, DateMonth dateMonth) throws IOException {
        String url = getURL(ticker, dateMonth);
        return getData(url);
    }
    public static  String getData(String urlToRead) throws IOException {

        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line="";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
//        System.out.println(urlToRead);
//        System.out.println(result.toString());
        return result.toString();

//        return "{\"datatable\":{\"data\":[[\"MSFT\",\"2017-01-03\",62.79,62.84,62.125,62.58,20694101.0,0.0,1.0,61.424575201426,61.473487906635,60.774036222147,61.219141839549,20694101.0],[\"MSFT\",\"2017-01-04\",62.48,62.75,62.12,62.3,21339969.0,0.0,1.0,61.121316429131,61.385445037259,60.769144951626,60.945230690378,21339969.0],[\"MSFT\",\"2017-01-05\",62.19,62.66,62.03,62.3,24875968.0,0.0,1.0,60.837622738919,61.297402167883,60.68110208225,60.945230690378,24875968.0],[\"MSFT\",\"2017-01-06\",62.3,63.15,62.04,62.84,19922919.0,0.0,1.0,60.945230690378,61.776746678931,60.690884623292,61.473487906635,19922919.0],[\"MSFT\",\"2017-01-09\",62.76,63.08,62.54,62.64,20382730.0,0.0,1.0,61.395227578301,61.708268891638,61.180011675381,61.277837085799,20382730.0],[\"MSFT\",\"2017-01-10\",62.73,63.07,62.28,62.62,18593004.0,0.0,1.0,61.365879955176,61.698486350597,60.925665608295,61.258272003716,18593004.0],[\"MSFT\",\"2017-01-11\",62.61,63.23,62.43,63.19,21517335.0,0.0,1.0,61.248489462674,61.855007007265,61.072403723922,61.815876843098,21517335.0],[\"MSFT\",\"2017-01-12\",63.06,63.4,61.95,62.61,20968223.0,0.0,1.0,61.688703809555,62.021310204976,60.602841753916,61.248489462674,20968223.0],[\"MSFT\",\"2017-01-13\",62.62,62.865,62.35,62.7,19422310.0,0.0,1.0,61.258272003716,61.49794425924,60.994143395587,61.33653233205,19422310.0],[\"MSFT\",\"2017-01-17\",62.68,62.7,62.03,62.53,20663983.0,0.0,1.0,61.316967249967,61.33653233205,60.68110208225,61.17022913434,20663983.0],[\"MSFT\",\"2017-01-18\",62.67,62.7,62.12,62.5,19670102.0,0.0,1.0,61.307184708925,61.33653233205,60.769144951626,61.140881511214,19670102.0],[\"MSFT\",\"2017-01-19\",62.24,62.98,62.195,62.3,18451655.0,0.0,1.0,60.886535444128,61.61044348122,60.84251400944,60.945230690378,18451655.0],[\"MSFT\",\"2017-01-20\",62.67,62.82,62.37,62.74,30213462.0,0.0,1.0,61.307184708925,61.453922824552,61.013708477671,61.375662496217,30213462.0],[\"MSFT\",\"2017-01-23\",62.7,63.1159,62.57,62.96,23097581.0,0.0,1.0,61.33653233205,61.743388213978,61.209359298507,61.590878399137,23097581.0],[\"MSFT\",\"2017-01-24\",63.2,63.735,62.94,63.52,24672940.0,0.0,1.0,61.82565938414,62.349025329876,61.571313317053,62.138700697477,24672940.0],[\"MSFT\",\"2017-01-25\",63.95,64.1,63.45,63.68,24654933.0,0.0,1.0,62.559349962274,62.706088077901,62.070222910185,62.295221354146,24654933.0],[\"MSFT\",\"2017-01-26\",64.12,64.535,63.55,64.27,43554645.0,0.0,1.0,62.725653159985,63.131628613219,62.168048320603,62.872391275612,43554645.0],[\"MSFT\",\"2017-01-27\",65.39,65.91,64.89,65.78,44817972.0,0.0,1.0,63.968035872293,64.476728006466,63.478908820203,64.349554972923,44817972.0],[\"MSFT\",\"2017-01-30\",65.69,65.79,64.8,65.13,31651445.0,0.0,1.0,64.261512103547,64.359337513965,63.390865950827,63.713689805206,31651445.0],[\"MSFT\",\"2017-01-31\",64.86,65.15,64.26,64.65,25270549.0,0.0,1.0,63.449561197078,63.73325488729,62.86260873457,63.2441278352,25270549.0]],\"columns\":[{\"name\":\"ticker\",\"type\":\"String\"},{\"name\":\"date\",\"type\":\"Date\"},{\"name\":\"open\",\"type\":\"BigDecimal(34,12)\"},{\"name\":\"high\",\"type\":\"BigDecimal(34,12)\"},{\"name\":\"low\",\"type\":\"BigDecimal(34,12)\"},{\"name\":\"close\",\"type\":\"BigDecimal(34,12)\"},{\"name\":\"volume\",\"type\":\"BigDecimal(37,15)\"},{\"name\":\"ex-dividend\",\"type\":\"BigDecimal(42,20)\"},{\"name\":\"split_ratio\",\"type\":\"double\"},{\"name\":\"adj_open\",\"type\":\"BigDecimal(50,28)\"},{\"name\":\"adj_high\",\"type\":\"BigDecimal(50,28)\"},{\"name\":\"adj_low\",\"type\":\"BigDecimal(50,28)\"},{\"name\":\"adj_close\",\"type\":\"BigDecimal(50,28)\"},{\"name\":\"adj_volume\",\"type\":\"double\"}]},\"meta\":{\"next_cursor_id\":null}}";

    }
    public static String getURL(String ticker, DateMonth dateMonth)
    {
        String dateRanges = dateRangeGenerator(dateMonth.getYear(), dateMonth.getMonth());
        return String.format(URL_FORMAT, ticker, dateRanges, API_KEY);
    }
    public static String dateRangeGenerator(int year, int month)
    {
        StringBuilder sb = new StringBuilder();
        int days = DAYS[month-1];
        if(isLeapYear(year) && month==2)
        {
            days = 29;
        }

        for(int i=0;i<days;i++)
        {
            sb.append(String.format("%04d-%02d-%02d", year, month, i+1));
            sb.append("%2c");
        }

        return sb.toString();
    }

    public static boolean isLeapYear(int year)
    {
        return year%400==0 || (year %4==0 && year %100 !=0);
    }



}
