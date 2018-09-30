package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
