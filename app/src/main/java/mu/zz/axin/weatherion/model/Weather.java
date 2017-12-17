package mu.zz.axin.weatherion.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Axin on 18.04.2017.
 */

public class Weather {
    private String mId;
    private String mTemperature;
    private String mWindSpeed;
    private String mHumidity;
    private String mPressure;
    private String mWeatherIcon;
    private String mWeatherDateTime;

    public Weather(String temperature, String windSpeed, String humidity, String pressure,
                   String weatherIcon, String weatherDateTime) {

        this.mTemperature = temperature;
        this.mWindSpeed = windSpeed;
        this.mHumidity = humidity;
        this.mPressure = pressure;
        this.mWeatherIcon = weatherIcon;
        this.mWeatherDateTime = weatherDateTime;
    }

    public Weather(String id, String temperature, String windSpeed, String humidity,
                   String pressure, String weatherIcon, String weatherDateTime) {
        mId = id;
        mTemperature = temperature;
        mWindSpeed = windSpeed;
        mHumidity = humidity;
        mPressure = pressure;
        mWeatherIcon = weatherIcon;
        mWeatherDateTime = weatherDateTime;
    }

    public String getId() {
        return mId;
    }
    String getWeatherDateTime() {
        return mWeatherDateTime;
    }
    public String getTemperature() {
        return mTemperature;
    }
    String getWindSpeed() {
        return mWindSpeed;
    }
    String getHumidity() {
        return mHumidity;
    }
    String getPressure() {
        return mPressure;
    }
    public String getWeatherIcon() {
        return mWeatherIcon;
    }

    public String weatherDate() {
        Date date = getDateFromTS();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String weatherDate = dateFormat.format(date);
        return weatherDate;
    }
    public String weatherTime() {
        Date date = getDateFromTS();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String weatherTime = dateFormat.format(date);
        return weatherTime;
    }

    private Date getDateFromTS(){
        Date time = new Date(Long.valueOf(mWeatherDateTime)*1000);
        return time;
    }

}
