package mu.zz.axin.weatherion.controler.tabs;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import mu.zz.axin.weatherion.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherMapFragment extends Fragment {
    View fragmentLayout;
    ImageView mImageView, mImageView2;

    public WeatherMapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentLayout = inflater.inflate(R.layout.fragment_weather_map, container, false);
        mImageView = (ImageView) fragmentLayout.findViewById(R.id.imageView);
        mImageView2 = (ImageView) fragmentLayout.findViewById(R.id.imageView2);
        Picasso.with(getActivity())
                .load("https://api.tiles.mapbox.com/v4/mapbox.streets/3/4/2.png?access_token=pk.eyJ1IjoiYW5leGdlYXIiLCJhIjoiY2oyMzFpanN4MDAwcDJxbzV6MnZ3ZnlueCJ9.0rA6RmqCCNCkm5a8gmD5ag")
                .into(mImageView);
        Picasso.with(getActivity())
                .load("http://tile.openweathermap.org/map/clouds_new/3/4/2.png?appid=d77b2dfd27d6de8624f0381045f68e98")
                .into(mImageView2);

        return fragmentLayout;
    }



}