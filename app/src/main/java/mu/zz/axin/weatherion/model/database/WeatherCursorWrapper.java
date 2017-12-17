package mu.zz.axin.weatherion.model.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import mu.zz.axin.weatherion.model.Weather;

public class WeatherCursorWrapper extends CursorWrapper {
    public WeatherCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Weather getWeather() {
        String mId = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_ID));
        String mDateTime = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_DATETIME));
        String mTemperature = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_TEMPERATURE));
        String mHumidity = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_HUMIDITY));
        String mPressure = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_PRESSURE));
        String mWindSpeed = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_WIND_SPEED));
        String mWeatherIcon = getString(getColumnIndex(WeatherDataBaseSchema.WeatherTable.Colums.KEY_WEATHER_ICON));

        Weather weather = new Weather(mId,mTemperature,mWindSpeed, mHumidity, mPressure, mWeatherIcon, mDateTime);
        return weather;

    }
}
