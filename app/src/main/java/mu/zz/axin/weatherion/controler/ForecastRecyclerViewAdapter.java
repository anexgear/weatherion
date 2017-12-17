package mu.zz.axin.weatherion.controler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.controler.tabs.WeatherForecastFragment;
import mu.zz.axin.weatherion.model.Weather;

public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<ForecastRecyclerViewAdapter.ViewHolder> {

    private List<Weather> mWeatherList;
    private final WeatherForecastFragment.OnListFragmentInteractionListener mListener;

    public ForecastRecyclerViewAdapter(List<Weather> items, WeatherForecastFragment.OnListFragmentInteractionListener listener) {
        mWeatherList = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_forecast_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mWeather = mWeatherList.get(position);
        holder.cardviewDateTextView.setText(mWeatherList.get(position).weatherDate());
        holder.cardviewTimeTextView.setText(mWeatherList.get(position).weatherTime());
        holder.cardviewTemperatureTextView.setText(getIntTemperature(position));
        
        setWeatherIcon(holder, position);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onWeatherSelected(holder.mWeather);
                }
            }
        });
    }

    public String getIntTemperature(int position) {
        Double doubleTemprature = Double.valueOf(mWeatherList.get(position).getTemperature());
        int intTemprature = doubleTemprature.intValue();
        return String.valueOf(intTemprature);
    }

    private void setWeatherIcon(ViewHolder holder, int position) {
        switch (mWeatherList.get(position).getWeatherIcon()) {
            case "01d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_01d);
                break;
            case "01n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_01n);
                break;
            case "02d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_02d);
                break;
            case "02n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_02n);
                break;
            case "03d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_03d);
                break;
            case "03n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_03n);
                break;
            case "04d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_04d);
                break;
            case "04n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_04n);
                break;
            case "09d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_09d);
                break;
            case "09n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_09n);
                break;
            case "10d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_10d);
                break;
            case "10n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_10n);
                break;
            case "11d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_11d);
                break;
            case "11n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_11n);
                break;
            case "13d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_13d);
                break;
            case "13n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_13n);
                break;
            case "50d":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_50d);
                break;
            case "50n":
                holder.cardviewIconImageView.setImageResource(R.drawable.ic_50n);
                break;
        }
    }


    @Override
    public int getItemCount() {
        return mWeatherList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView cardviewDateTextView, cardviewTimeTextView, cardviewTemperatureTextView;
        Weather mWeather;
        ImageView cardviewIconImageView;

         ViewHolder(View view) {
            super(view);
            mView = view;
            cardviewDateTextView = (TextView) view.findViewById(R.id.cardviewDateTextView);
            cardviewTimeTextView = (TextView) view.findViewById(R.id.cardviewTimeTextView);
            cardviewTemperatureTextView = (TextView) view.findViewById(R.id.cardviewTemperatureTextView);
            cardviewIconImageView = (ImageView) view.findViewById(R.id.cardviewIconImageView);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + cardviewTimeTextView.getText() + "'";
        }
    }

    public void setWeatherList (List<Weather> weatherList) {
        mWeatherList = weatherList;

    }

}
