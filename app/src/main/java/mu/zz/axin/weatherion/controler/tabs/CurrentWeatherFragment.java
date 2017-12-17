package mu.zz.axin.weatherion.controler.tabs;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.model.NetworkFetcher;
import mu.zz.axin.weatherion.model.Weather;
import mu.zz.axin.weatherion.model.WeatherOperations;

public class CurrentWeatherFragment extends Fragment {

    TextView dateTimeTextView, countryTextView, cityTextView, temperatureTextView, windTextView,
            humidityTextView, pressureTextView;

    ImageView iconImageView;
    WeatherOperations mWeatherOperations;
    NetworkFetcher mFetcher;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        dateTimeTextView = (TextView) view.findViewById(R.id.dateTimeTextView);
        countryTextView = (TextView) view.findViewById(R.id.countryTextView);
        cityTextView = (TextView) view.findViewById(R.id.cityTextView);
        temperatureTextView = (TextView) view.findViewById(R.id.temperatureTextView);
        windTextView = (TextView) view.findViewById(R.id.windTextView);
        humidityTextView = (TextView) view.findViewById(R.id.humidityTextView);
        pressureTextView = (TextView) view.findViewById(R.id.pressureTextView);

        iconImageView = (ImageView) view.findViewById(R.id.iconImageView);

        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        AsyncTask<Void, Void, Weather> weatherTask = new FetchItemsTask();
        weatherTask.execute();
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Weather> {

        @Override
        protected Weather doInBackground(Void... voids) {
            NetworkFetcher networkFetcher = new NetworkFetcher();
            try {
                return networkFetcher.getCurrentWeather();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            mWeatherOperations = new WeatherOperations(getActivity());
            mWeatherOperations.setWeatherView(dateTimeTextView, countryTextView, cityTextView,
                    temperatureTextView, windTextView, humidityTextView, pressureTextView, iconImageView,
                    weather, getActivity());
        }
    }
}
