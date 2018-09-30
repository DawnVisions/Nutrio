package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dawnvisions.nutrio.WeightTracker;

import java.util.ArrayList;
import java.util.List;

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
}
