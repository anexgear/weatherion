package mu.zz.axin.weatherion.controler.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.model.Weather;
import mu.zz.axin.weatherion.model.WeatherOperations;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastDetailFragment extends Fragment {
    private static final String ARG_WEATHER_ID = "weather_id";
    private WeatherOperations mWeatherOperations;
    private Weather mWeather;
    private Callbacks mCallbacks;

    private TextView dateTimeTextView, countryTextView, cityTextView,
            temperatureTextView, windTextView, humidityTextView, pressureTextView;
    private ImageView iconImageView;


    public ForecastDetailFragment() {
    }

    public interface Callbacks {
        void onWeatherUpdated(Weather weather);
    }

    public static ForecastDetailFragment newInstance(String weatherId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_WEATHER_ID, weatherId);

        ForecastDetailFragment fragment = new ForecastDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String weatherId = (String) getArguments().getSerializable(ARG_WEATHER_ID);
        mWeather = WeatherOperations.get(getActivity()).getWeather(weatherId);
    }

    @Override
    public void onPause() {
        super.onPause();
//        WeatherOperations.get(getActivity()).updateWeather(mWeather);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_current_weather, container, false);
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
        mWeatherOperations = new WeatherOperations(getActivity());
        mWeatherOperations.setWeatherView(dateTimeTextView, countryTextView, cityTextView,
                temperatureTextView, windTextView, humidityTextView, pressureTextView, iconImageView,
                mWeather, getActivity());

    }
}
