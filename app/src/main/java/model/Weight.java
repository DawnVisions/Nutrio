package model;


import android.content.ContentValues;

import com.dawnvisions.nutrio.WeightTracker;

import java.util.Date;
import java.util.UUID;

import database.WeightTable;

public class Weight
{

    private String id;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer grams;
    private Integer pound;
    private Integer ounce;

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

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Integer getGrams()
    {
        return grams;
    }

    public void setGrams(Integer grams)
    {
        this.grams = grams;
    }

    public Integer getPound()
    {
        return pound;
    }

    public void setPound(Integer pound)
    {
        this.pound = pound;
    }

    public Integer getOunce()
    {
        return ounce;
    }

    public void setOunce(Integer ounce)
    {
        this.ounce = ounce;
    }

    public Weight(Integer year, Integer month, Integer day, Integer grams, Integer pound, Integer ounce)
    {
        this.id = UUID.randomUUID().toString();
        this.year = year;
        this.month = month;
        this.day = day;
        this.grams = grams;
        this.pound = pound;
        this.ounce = ounce;
    }

    public Weight()
    {

    }

    public ContentValues toValues()
    {
        ContentValues values = new ContentValues(7);
        values.put(WeightTable.COLUMN_ID, id);
        values.put(WeightTable.COLUMN_YEAR, year);
        values.put(WeightTable.COLUMN_MONTH, month);
        values.put(WeightTable.COLUMN_DAY, day);
        values.put(WeightTable.COLUMN_WEIGHT_G, grams);
        values.put(WeightTable.COLUMN_WEIGHT_LB, pound);
        values.put(WeightTable.COLUMN_WEIGHT_OZ, ounce);
        return values;
    }

}
