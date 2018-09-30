package database;

public class WeightTable
{
    public static final String TABLE_ITEMS = "weights";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_WEIGHT_G = "grams";
    public static final String COLUMN_WEIGHT_LB = "pound";
    public static final String COLUMN_WEIGHT_OZ = "ounce";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY, COLUMN_WEIGHT_G, COLUMN_WEIGHT_LB, COLUMN_WEIGHT_OZ};


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_YEAR + " INTEGER," +
                    COLUMN_MONTH + " INTEGER," +
                    COLUMN_DAY + " INTEGER," +
                    COLUMN_WEIGHT_G + " INTEGER," +
                    COLUMN_WEIGHT_LB + " INTEGER," +
                    COLUMN_WEIGHT_OZ + " INTEGER" + ");";

}