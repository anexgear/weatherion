package mu.zz.axin.weatherion.controler.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.model.Weather;
import mu.zz.axin.weatherion.model.WeatherOperations;

public class ForecastDetailActivity extends AppCompatActivity implements ForecastDetailFragment.Callbacks {

    private static final String EXTRA_WEATHER_ID = "weather_id";

    private ViewPager mViewPager;
    private List<Weather> mWeatherList;
    public static Intent newIntent(Context packageContext, String weatherId) {
        Intent intent = new Intent(packageContext, ForecastDetailActivity.class);
        intent.putExtra(EXTRA_WEATHER_ID, weatherId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);


        String weatherId = (String) getIntent().getSerializableExtra(EXTRA_WEATHER_ID);
        mWeatherList = WeatherOperations.get(this).getWeatherList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.activity_forecast_view_pager);

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Weather weather = mWeatherList.get(position);
                return ForecastDetailFragment.newInstance(weather.getId());
            }

            @Override
            public int getCount() {
                return mWeatherList.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Weather weather = mWeatherList.get(position);
//                if (weather.weatherTime() != null) {
//                    setTitle(weather.weatherTime());
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int position = 0; position < mWeatherList.size(); position++) {
            if (mWeatherList.get(position).getId().equals(weatherId)) {
                mViewPager.setCurrentItem(position);
                break;
            }
        }
    }

    @Override
    public void onWeatherUpdated(Weather weather) {

    }

}
