package mu.zz.axin.weatherion.controler.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.controler.ForecastRecyclerViewAdapter;
import mu.zz.axin.weatherion.model.Weather;
import mu.zz.axin.weatherion.model.WeatherOperations;


public class WeatherForecastFragment extends Fragment {

    RecyclerView recyclerView;
    ForecastRecyclerViewAdapter forecastRecyclerViewAdapter;
    private OnListFragmentInteractionListener mListener;

    public WeatherForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_recycler_view, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            updateUI();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void updateUI() {
        WeatherOperations weatherOperations = WeatherOperations.get(getActivity());
        List<Weather> weatherList = weatherOperations.getWeatherList();

        if (forecastRecyclerViewAdapter == null) {
            forecastRecyclerViewAdapter = new ForecastRecyclerViewAdapter(weatherList, mListener);
            recyclerView.setAdapter(forecastRecyclerViewAdapter);
        } else {
            forecastRecyclerViewAdapter.setWeatherList(weatherList);
            forecastRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    public interface OnListFragmentInteractionListener {
        void onWeatherSelected(Weather weather);
    }

}
