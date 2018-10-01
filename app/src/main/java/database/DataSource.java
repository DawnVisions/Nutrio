package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Feeding;
import model.Weight;

public class DataSource
{
    private Context context;
    private SQLiteDatabase database;
    private SQLiteOpenHelper helper;

    public DataSource (Context context)
    {
        this.context = context;
        helper = new DBHelper(context);
        database = helper.getReadableDatabase();
    }

    public void open()
    {
        database = helper.getReadableDatabase();
    }

    public void close()
    {
        helper.close();
    }

    public Weight createWeight(Weight weight)
    {
        ContentValues values = weight.toValues();
        database.insert(WeightTable.TABLE_ITEMS, null, values);
        return weight;
    }

    public List<Weight> getAllWeights()
    {
        List<Weight> weights = new ArrayList<>();
        Cursor cursor = database.query(WeightTable.TABLE_ITEMS, WeightTable.ALL_COLUMNS, null,null, null, null, null);

        while(cursor.moveToNext())
        {
            Weight weight = new Weight();
            weight.setId(cursor.getString(cursor.getColumnIndex(WeightTable.COLUMN_ID)));
            weight.setYear(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_YEAR)));
            weight.setMonth(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_MONTH)));
            weight.setDay(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_DAY)));
            weight.setGrams(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_WEIGHT_G)));
            weight.setPound(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_WEIGHT_LB)));
            weight.setOunce(cursor.getInt(cursor.getColumnIndex(WeightTable.COLUMN_WEIGHT_OZ)));
            weights.add(weight);
        }
        return weights;
    }

    public Feeding createFeeding(Feeding feeding)
    {
        ContentValues values = feeding.toValues();
        database.insert(FeedingTable.TABLE_ITEMS, null, values);
        return feeding;
    }

    public List<Feeding> getAllFeedings()
    {
        List<Feeding> feedings = new ArrayList<>();
        Cursor cursor = database.query(FeedingTable.TABLE_ITEMS, FeedingTable.ALL_COLUMNS, null,null, null, null, null);

        while(cursor.moveToNext())
        {
            Feeding feeding = new Feeding();
            feeding.setId(cursor.getString(cursor.getColumnIndex(FeedingTable.COLUMN_ID)));
            feeding.setYear(cursor.getInt(cursor.getColumnIndex(FeedingTable.COLUMN_YEAR)));
            feeding.setMonth(cursor.getInt(cursor.getColumnIndex(FeedingTable.COLUMN_MONTH)));
            feeding.setDay(cursor.getInt(cursor.getColumnIndex(FeedingTable.COLUMN_DAY)));
            feeding.setHour(cursor.getInt(cursor.getColumnIndex(FeedingTable.COLUMN_TIME_HOUR)));
            feeding.setMin(cursor.getInt(cursor.getColumnIndex(FeedingTable.COLUMN_TIME_MIN)));
            feeding.setType(cursor.getString(cursor.getColumnIndex(FeedingTable.COLUMN_TYPE)));
            feeding.setAmount(cursor.getString(cursor.getColumnIndex(FeedingTable.COLUMN_AMOUNT)));
            feeding.setSide(cursor.getString(cursor.getColumnIndex(FeedingTable.COLUMN_SIDE)));

            feedings.add(feeding);
        }
        return feedings;
    }
}
