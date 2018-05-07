package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateMonth implements Comparable<DateMonth>{
    private int year;
    private int month;

    public DateMonth(String dateMonthString) throws InvalidDateMonthStringException {

        try
        {
            String[] split = dateMonthString.split("-");
            this.year = Integer.parseInt(split[0]);
            this.month = Integer.parseInt(split[1]);
        }
        catch (Exception ex)
        {
            throw new InvalidDateMonthStringException();
        }
    }
    public DateMonth(int year, int month) {
        this.year = year;
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d", this.year, this.month);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DateMonth)
        {
            DateMonth d = (DateMonth) o;
            return this.year == d.year && this.month==d.month;
        }
        else
        {
            return false;
        }

    }

    public DateMonth next()
    {
        int newYear =year;
        int newMonth = month+1;
        if(newMonth>12)
        {
            newMonth = 1;
            newYear = newYear+1;
        }
        return new DateMonth(newYear, newMonth);
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        return result;
    }

    public static ArrayList<DateMonth> getDateMonths(DateMonth from , DateMonth to) throws InvalidDateException {
        ArrayList<DateMonth> list = new ArrayList<DateMonth>();
        if(from.compareTo(to) >0)
        {
            throw new InvalidDateException();
        }
        else if(from.compareTo(to) ==0)
        {
            list.add(from);
        }
        else
        {
            DateMonth current = from;
            while (current.compareTo(to)<=0)
            {
                list.add(current);
                current = current.next();
            }
        }
        return list;
    }

    @Override
    public int compareTo(DateMonth o) {
        if(this.equals(o))
        {
            return 0;
        }
        else if(this.year != o.year)
        {
            return this.year - o.year;
        }
        else
        {
            return this.month - o.month;
        }
    }
}
