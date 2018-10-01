package database;

public class FeedingTable
{
    public static final String TABLE_ITEMS = "feedings";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_DAY = "day";
    public static final String COLUMN_TIME_HOUR = "hour";
    public static final String COLUMN_TIME_MIN = "min";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_SIDE = "side";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY,
            COLUMN_TIME_HOUR, COLUMN_TIME_MIN, COLUMN_TYPE, COLUMN_AMOUNT, COLUMN_SIDE};


    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_YEAR + " INTEGER," +
                    COLUMN_MONTH + " INTEGER," +
                    COLUMN_DAY + " INTEGER," +
                    COLUMN_TIME_HOUR + " INTEGER," +
                    COLUMN_TIME_MIN + " INTEGER," +
                    COLUMN_TYPE + " TEXT," +
                    COLUMN_AMOUNT + " TEXT," +
                    COLUMN_SIDE + " TEXT" + ");";

}