package mu.zz.axin.weatherion.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WeatherDataBaseHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "OpenWeather.db";

    private String CREATE_TABLE = "CREATE TABLE "+ WeatherDataBaseSchema.WeatherTable.TABLE_NAME+" ("
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_ID+" INTEGER PRIMARY KEY,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_DATETIME+" TEXT,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_TEMPERATURE+" TEXT,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_PRESSURE+" TEXT,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_HUMIDITY+" TEXT,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_WIND_SPEED +" TEXT,"
            + WeatherDataBaseSchema.WeatherTable.Colums.KEY_WEATHER_ICON +" TEXT)";
    private static String DROP_TABLE = "DROP TABLE IF EXISTS "+ WeatherDataBaseSchema.WeatherTable.TABLE_NAME;

    public WeatherDataBaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cleanDataBase(db);
    }


    public void cleanDataBase(SQLiteDatabase db) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

}
