package mu.zz.axin.weatherion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class WeatherDBHelper  extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "OpenWeather.db";
    private static final String TABLE_NAME = "weather_table";
    private static final String KEY_ID = "_id";
    private static final String KEY_DATETIME = "_dt_txt";
    private static final String KEY_TEMP = "_temp";
    private static final String KEY_PRESSURE = "_pressure";
    private static final String KEY_WEATHERMAIN = "_weathermain";
    private static final String KEY_WEATHERDESCRIPTION = "_weatherdescription";
    private static final String KEY_WEATHERICON = "_weathericon";
    private static final String KEY_WIND = "_wind";

    String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_DATETIME+" TEXT,"+KEY_TEMP+" TEXT,"+KEY_PRESSURE+" TEXT,"+KEY_WEATHERMAIN+" TEXT,"+KEY_WEATHERDESCRIPTION+" TEXT,"+KEY_WEATHERICON+" TEXT,"+KEY_WIND+" TEXT)";
    String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public WeatherDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addWeather(Weather weather) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues values = new ContentValues();
            values.put(KEY_DATETIME, weather.getDt_txt());
            values.put(KEY_TEMP, weather.getTemp());
            values.put(KEY_PRESSURE,weather.getPressure());
            values.put(KEY_WEATHERMAIN,weather.getWeathermain());
            values.put(KEY_WEATHERDESCRIPTION,weather.getWeatherdescription());
            values.put(KEY_WEATHERICON,weather.getWeathericon());
            values.put(KEY_WIND,weather.getWind());
            db.insert(TABLE_NAME, null, values);
            db.close();
        }catch (Exception e){
            Log.e("problem",e+"");
        }
    }


    public ArrayList<Weather> getAllWeather() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Weather> weatherList = null;
        try{
            weatherList = new ArrayList<>();
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            if(!cursor.isLast())
            {
                while (cursor.moveToNext())
                {
                    Weather weather = new Weather();
                    weather.setId(cursor.getInt(0));
                    weather.setDt_txt(cursor.getString(1));
                    weather.setTemp(cursor.getString(2));
                    weather.setPressure(cursor.getString(3));
                    weather.setWeathermain(cursor.getString(4));
                    weather.setWeatherdescription(cursor.getString(5));
                    weather.setWeathericon(cursor.getString(6));
                    weather.setWind(cursor.getString(7));
                    weatherList.add(weather);
                }
            }
            cursor.close();
            db.close();
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return weatherList;
    }


    public int getWeatherCount() {
        int num;
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            String QUERY = "SELECT * FROM "+TABLE_NAME;
            Cursor cursor = db.rawQuery(QUERY, null);
            num = cursor.getCount();
            cursor.close();
            db.close();
            return num;
        }catch (Exception e){
            Log.e("error",e+"");
        }
        return 0;
    }
}
