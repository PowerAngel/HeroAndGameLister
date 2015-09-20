package com.example.golvmopp.heroandgamelister;

/**
 * Created by Golvmopp on 2015-09-20.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class AbstractHeroDB
{
    static String myLogTag = "MyTag";

    public static final String DB_TABLE_HERO = "hero";

    public static final String KEY_HEROID = "hero_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_MAINATTRIBUTE = "main_attribute";
    public static final String KEY_MOVEMENTSPEED = "movement_speed";
    public static final String KEY_RANGE = "range";
    public static final String KEY_STARTINT = "start_int";
    public static final String KEY_STARTAGI = "start_agi";
    public static final String KEY_STARTSTR = "start_str";
    public static final String KEY_STARTDAMAGE = "start_damage";
    public static final String KEY_STARTARMOR = "start_armor";
    public static final String KEY_STARTHEALTH = "start_health";
    public static final String KEY_STARTMANA = "start_mana";
    public static final String KEY_INTPERLVL = "int_per_level";
    public static final String KEY_AGIPERLVL = "agi_per_level";
    public static final String KEY_STRPERLVL = "str_per_level";

    public static final String TAG = "HeroDB";
    public static final String DB_NAME = "heroes";
    protected SQLiteDatabase database;

    public static final String [] columns = {KEY_HEROID, KEY_NAME, KEY_MAINATTRIBUTE, KEY_MOVEMENTSPEED, KEY_RANGE, KEY_STARTINT, KEY_STARTAGI,
            KEY_STARTSTR, KEY_STARTDAMAGE, KEY_STARTARMOR, KEY_STARTHEALTH, KEY_STARTMANA, KEY_INTPERLVL, KEY_AGIPERLVL, KEY_STRPERLVL};

    public static final int DB_VERSION = 1;

    private MyDbHelper mDbHelper;
    private Context context;

    private static final String DB_CREATE_HERO = "create table " + DB_TABLE_HERO + " (" + KEY_HEROID + " text primary key, " + KEY_NAME + " text not null, " + KEY_MAINATTRIBUTE +
            " text not null, " + KEY_MOVEMENTSPEED + " integer not null, " + KEY_RANGE + " integer not null, " + KEY_STARTINT + " decimal not null, " + KEY_STARTAGI + " decimal not null, " +
            KEY_STARTSTR + " decimal not null, " + KEY_STARTDAMAGE + " integer not null, " + KEY_STARTARMOR + " decimal not null, " + KEY_STARTHEALTH + " integer not null, " +
            KEY_STARTMANA + " integer not null, " + KEY_INTPERLVL + " decimal not null, " + KEY_AGIPERLVL + " decimal not null, " + KEY_STRPERLVL + " decimal not null);";

    protected abstract void createTestData();

    public void open(boolean writable, Context ctx) throws SQLException
    {
        this.context = ctx;
        mDbHelper = new MyDbHelper(context);
        if(writable)
        {
            database = mDbHelper.getWritableDatabase();
            if(mDbHelper.isFirstTime())
            {
                createTestData();
            }
        }
        else
        {
            database = mDbHelper.getReadableDatabase();
        }

    }

    public void close()
    {
        mDbHelper.close();
    }

    protected Cursor getAllHeroesCursor()
    {
        return database.query(DB_TABLE_HERO, columns, null, null, null, null, null);
    }

    protected static class MyDbHelper extends SQLiteOpenHelper // Local help class
    {
        private boolean firstTime = false;
        MyDbHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqldb) // called if needed
        {
            Log.v(myLogTag, "sqldb.execSQL(DB_CREATE_HERO ska precis köras");
            sqldb.execSQL(DB_CREATE_HERO);
            Log.v(myLogTag, "sqldb.execSQL(DB_CREATE_HERO har precis körts");
            firstTime = true;
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_HERO);
            onCreate(db);
        }
        public boolean isFirstTime()
        {
            return firstTime;
        }
    }
}
