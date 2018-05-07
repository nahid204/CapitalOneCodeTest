package data;

import util.DeserializationUtil;

import java.util.Date;

public class Entity {
    protected String ticker;
    protected Date date;
    protected double openPrice;
    protected double closePrice;

    protected double highPrice;
    protected double lowPrice;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    protected double volume;
    public Entity(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getDateString()
    {
        return DeserializationUtil.DATE_FORMAT.format(date);
    }

    @Override
    public String toString() {
        return "{" +
                "ticker='" + ticker + '\'' +
                ", date=" + getDateString() +
                '}';
    }
}
