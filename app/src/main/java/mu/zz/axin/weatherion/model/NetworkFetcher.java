package mu.zz.axin.weatherion.model;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NetworkFetcher {
    //https://maps.googleapis.com/maps/api/place/textsearch/xml?query=%D0%9C%D0%BE%D1%81%D0%BA&types=locality|country|administrative_area_level_1|administrative_area_level_2&key=AIzaSyCvjTi6ppAYIEpbzBci04XPtdF0m3PAiKw
    private static final String TAG = "NetworkFetcher";
    private static final String OPENWEATHER_API_KEY = "d77b2dfd27d6de8624f0381045f68e98";
    private static final String WEATHER_UNITS = "metric";
    private static final String OPENWEATHER_CURRENT_API_URL = "http://api.openweathermap.org/data/2.5/find?";
    private static final String OPENWEATHER_FORECAST_API_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    private static final String TEST_CITY = "Kiev";
    public static final Uri OPENWEATHER_CURRENT_ENDPOINT = Uri.parse(OPENWEATHER_CURRENT_API_URL).buildUpon()
            .appendQueryParameter("q", TEST_CITY)
            .appendQueryParameter("units", WEATHER_UNITS)
            .appendQueryParameter("appid", OPENWEATHER_API_KEY)
            .build();
    public static final Uri OPENWEATHER_FORECAST_ENDPOINT = Uri.parse(OPENWEATHER_FORECAST_API_URL).buildUpon()
            .appendQueryParameter("q", TEST_CITY)
            .appendQueryParameter("units", WEATHER_UNITS)
            .appendQueryParameter("appid", OPENWEATHER_API_KEY)
            .build();

     private String mTemperature,
             mWindSpeed, mPressure, mHumidity, mWeatherIcon, mWeatherDateTime;

    private String weatherHttpRequest(String endpointURL){
        HttpURLConnection connection = null;
        InputStream inputStream;
        BufferedReader bufferedReader;
        try {
            URL connectionURL = new URL(endpointURL);
            connection = (HttpURLConnection) connectionURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            inputStream = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return (stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        } return null;
    }

    public Weather getCurrentWeather() throws JSONException {
        JSONObject jsonObject = new JSONObject(weatherHttpRequest(OPENWEATHER_CURRENT_ENDPOINT.toString()));
        JSONArray currentWeather = jsonObject.getJSONArray("list");
        mTemperature = currentWeather.getJSONObject(0).getJSONObject("main").getString("temp");
        mPressure = currentWeather.getJSONObject(0).getJSONObject("main").getString("pressure");
        mHumidity = currentWeather.getJSONObject(0).getJSONObject("main").getString("humidity");
        mWeatherDateTime = currentWeather.getJSONObject(0).getString("dt");
        mWindSpeed = currentWeather.getJSONObject(0).getJSONObject("wind").getString("speed");
        JSONArray jsonArray = currentWeather.getJSONObject(0).getJSONArray("weather");
        mWeatherIcon = jsonArray.getJSONObject(0).getString("icon");

        return new Weather(mTemperature, mWindSpeed, mHumidity, mPressure, mWeatherIcon, mWeatherDateTime);
    }


    public void getWeatherForecast(Context context) throws JSONException {
        JSONObject jsonObject = new JSONObject(weatherHttpRequest(OPENWEATHER_FORECAST_ENDPOINT.toString()));
        JSONArray jsonArrayForecast = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArrayForecast.length(); i++) {
            mTemperature = jsonArrayForecast.getJSONObject(i).getJSONObject("main").getString("temp");
            mPressure = jsonArrayForecast.getJSONObject(i).getJSONObject("main").getString("pressure");
            mHumidity = jsonArrayForecast.getJSONObject(i).getJSONObject("main").getString("humidity");
            mWeatherDateTime = jsonArrayForecast.getJSONObject(i).getString("dt");
            mWindSpeed = jsonArrayForecast.getJSONObject(i).getJSONObject("wind").getString("speed");
            JSONArray jsonInnerArray = jsonArrayForecast.getJSONObject(i).getJSONArray("weather");
            for (int j = 0; j < jsonInnerArray.length(); j++) {
                mWeatherIcon = jsonInnerArray.getJSONObject(j).getString("icon");
            }
            Weather weather = new Weather(mTemperature, mWindSpeed, mHumidity, mPressure, mWeatherIcon, mWeatherDateTime);
            WeatherOperations weatherOperations = new WeatherOperations(context);
            weatherOperations.addWeather(weather);
        }
    }



}

