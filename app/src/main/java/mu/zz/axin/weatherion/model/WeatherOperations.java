package mu.zz.axin.weatherion.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.controler.tabs.WeatherActivity;
import mu.zz.axin.weatherion.model.database.WeatherCursorWrapper;
import mu.zz.axin.weatherion.model.database.WeatherDataBaseHelper;
import mu.zz.axin.weatherion.model.database.WeatherDataBaseSchema;


public class WeatherOperations {
    private static WeatherOperations sWeatherOperations;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static WeatherOperations get(Context context) {
        if (sWeatherOperations == null) {
            sWeatherOperations = new WeatherOperations(context);
        }
        return sWeatherOperations;
    }

    public WeatherOperations(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new WeatherDataBaseHelper(mContext).getWritableDatabase();
    }

    public void addWeather(Weather weather) {
        ContentValues values = getContentValues(weather);
        mDatabase.insert(WeatherDataBaseSchema.WeatherTable.TABLE_NAME, null, values);
    }


    public List<Weather> getWeatherList() {
        List<Weather> weatherList = new ArrayList<>();
        WeatherCursorWrapper cursorWrapper = queryWeatherList(null, null);

        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            weatherList.add(cursorWrapper.getWeather());
            cursorWrapper.moveToNext();
        }
        cursorWrapper.close();

        return weatherList;
    }

    public Weather getWeather(String id){
        WeatherCursorWrapper cursorWrapper = queryWeatherList(WeatherDataBaseSchema.WeatherTable.Colums.KEY_ID +
        " = ?", new String[] { id });
        try {
            if(cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            return cursorWrapper.getWeather();
        } finally {
            cursorWrapper.close();
        }
    }

    public void updateWeather(Weather weather) {
        String mId = weather.getId();
        ContentValues values = getContentValues(weather);

        mDatabase.update(WeatherDataBaseSchema.WeatherTable.TABLE_NAME,
                values,
                WeatherDataBaseSchema.WeatherTable.Colums.KEY_ID,
                new String[] {mId});
    }

    private static ContentValues getContentValues (Weather weather) {
        ContentValues values = new ContentValues();
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_ID, weather.getId());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_DATETIME, weather.getWeatherDateTime());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_TEMPERATURE, weather.getTemperature());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_PRESSURE, weather.getPressure());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_HUMIDITY, weather.getHumidity());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_WIND_SPEED, weather.getWindSpeed());
        values.put(WeatherDataBaseSchema.WeatherTable.Colums.KEY_WEATHER_ICON, weather.getWeatherIcon());

        return values;
    }

    private WeatherCursorWrapper queryWeatherList(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(WeatherDataBaseSchema.WeatherTable.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new WeatherCursorWrapper(cursor);
    }
    Calendar calendar;
    Date date;
    SimpleDateFormat dateFormat;
    java.util.Formatter formatter;
    public void setWeatherView(TextView dateTimeTextView, TextView countryTextView,
                               TextView cityTextView, TextView temperatureTextView,
                               TextView windTextView, TextView humidityTextView,
                               TextView pressureTextView,
                               ImageView iconImageView,
                               Weather mWeather, Context context) {

        calendar = Calendar.getInstance();
        if (context instanceof WeatherActivity) {
            date = calendar.getTime();
        } else {
            date = new Date(Long.valueOf(mWeather.getWeatherDateTime())*1000);
        }

        dateFormat = new SimpleDateFormat("dd.MM.yyyy 'at' HH:mm");
        formatter = new java.util.Formatter();
        dateTimeTextView.setText((formatter.format(dateTimeTextView.getText().toString(),
                dateFormat.format(date))).toString());
        formatter = new java.util.Formatter();
        countryTextView.setText((formatter.format(countryTextView.getText().toString(), "Ukraine")).toString());
        formatter = new java.util.Formatter();
        cityTextView.setText((formatter.format(cityTextView.getText().toString(), "Kiev")).toString());
        formatter = new java.util.Formatter();
        temperatureTextView.setText((formatter.format(temperatureTextView.getText().toString(),
                mWeather.getTemperature())).toString());
        formatter = new java.util.Formatter();
        windTextView.setText((formatter.format(windTextView.getText().toString(),
                mWeather.getWindSpeed())).toString());
        formatter = new java.util.Formatter();
        humidityTextView.setText((formatter.format(humidityTextView.getText().toString(),
                mWeather.getHumidity())).toString());
        formatter = new java.util.Formatter();
        pressureTextView.setText((formatter.format(pressureTextView.getText().toString(),
                mWeather.getPressure())).toString());
        selectIcon(iconImageView, mWeather);
    }

    private void selectIcon(ImageView iconImageView,
                            Weather mWeather) {
        switch (mWeather.getWeatherIcon()) {
            case "01d":
                iconImageView.setImageResource(R.drawable.ic_01d);
                break;
            case "01n":
                iconImageView.setImageResource(R.drawable.ic_01n);
                break;
            case "02d":
                iconImageView.setImageResource(R.drawable.ic_02d);
                break;
            case "02n":
                iconImageView.setImageResource(R.drawable.ic_02n);
                break;
            case "03d":
                iconImageView.setImageResource(R.drawable.ic_03d);
                break;
            case "03n":
                iconImageView.setImageResource(R.drawable.ic_03n);
                break;
            case "04d":
                iconImageView.setImageResource(R.drawable.ic_04d);
                break;
            case "04n":
                iconImageView.setImageResource(R.drawable.ic_04n);
                break;
            case "09d":
                iconImageView.setImageResource(R.drawable.ic_09d);
                break;
            case "09n":
                iconImageView.setImageResource(R.drawable.ic_09n);
                break;
            case "10d":
                iconImageView.setImageResource(R.drawable.ic_10d);
                break;
            case "10n":
                iconImageView.setImageResource(R.drawable.ic_10n);
                break;
            case "11d":
                iconImageView.setImageResource(R.drawable.ic_11d);
                break;
            case "11n":
                iconImageView.setImageResource(R.drawable.ic_11n);
                break;
            case "13d":
                iconImageView.setImageResource(R.drawable.ic_13d);
                break;
            case "13n":
                iconImageView.setImageResource(R.drawable.ic_13n);
                break;
            case "50d":
                iconImageView.setImageResource(R.drawable.ic_50d);
                break;
            case "50n":
                iconImageView.setImageResource(R.drawable.ic_50n);
                break;
        }
    }

}
