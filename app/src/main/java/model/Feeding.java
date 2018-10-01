package model;


import android.content.ContentValues;

import com.dawnvisions.nutrio.WeightTracker;

import java.util.Date;
import java.util.UUID;

import database.FeedingTable;
import database.WeightTable;

public class Feeding
{

    private String id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer min;
    private String type;
    private String amount;
    private String side;

    public Feeding()
    {
    }

    public Feeding(Integer year, Integer month, Integer day, Integer hour, Integer min, String type, String amount, String side)
    {
        id = UUID.randomUUID().toString();
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.type = type;
        this.amount = amount;
        this.side = side;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public Integer getDay()
    {
        return day;
    }

    public void setDay(Integer day)
    {
        this.day = day;
    }

    public Integer getHour()
    {
        return hour;
    }

    public void setHour(Integer hour)
    {
        this.hour = hour;
    }

    public Integer getMin()
    {
        return min;
    }

    public void setMin(Integer min)
    {
        this.min = min;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getSide()
    {
        return side;
    }

    public void setSide(String side)
    {
        this.side = side;
    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(9);
        values.put(FeedingTable.COLUMN_ID, id);
        values.put(FeedingTable.COLUMN_YEAR, year);
        values.put(FeedingTable.COLUMN_MONTH, month);
        values.put(FeedingTable.COLUMN_DAY, day);
        values.put(FeedingTable.COLUMN_TIME_HOUR, hour);
        values.put(FeedingTable.COLUMN_TIME_MIN, min);
        values.put(FeedingTable.COLUMN_TYPE, type);
        values.put(FeedingTable.COLUMN_AMOUNT, amount);
        values.put(FeedingTable.COLUMN_SIDE, side);

        return values;
    }
}
