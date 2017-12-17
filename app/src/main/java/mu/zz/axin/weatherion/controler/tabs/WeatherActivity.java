package mu.zz.axin.weatherion.controler.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import mu.zz.axin.weatherion.R;
import mu.zz.axin.weatherion.controler.WeatherFetchrService;
import mu.zz.axin.weatherion.controler.detail.ForecastDetailActivity;
import mu.zz.axin.weatherion.model.Weather;
import mu.zz.axin.weatherion.model.database.WeatherDataBaseHelper;

public class WeatherActivity extends AppCompatActivity implements WeatherForecastFragment.OnListFragmentInteractionListener {


    WeatherDataBaseHelper mWeatherDataBaseHelper;
    private static final String EXTRA_WEATHER_ID = "weather_id";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        startService(new Intent(WeatherActivity.this, WeatherFetchrService.class));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherDataBaseHelper = new WeatherDataBaseHelper(getApplicationContext());
        mWeatherDataBaseHelper.cleanDataBase(mWeatherDataBaseHelper.getWritableDatabase());
        mWeatherDataBaseHelper.close();

    }

    @Override
    public void onWeatherSelected(Weather weather) {
        Intent intent = new Intent(this, ForecastDetailActivity.class);
        intent.putExtra(EXTRA_WEATHER_ID, weather.getId());
        startActivity(intent);
    }

//deleted default PlaceholderFragment

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    CurrentWeatherFragment currentWeatherTab = new CurrentWeatherFragment();
                    return currentWeatherTab;
                case 1:
                    WeatherForecastFragment weatherForecastTab = new WeatherForecastFragment();
                    return weatherForecastTab;
                case 2:
                    WeatherMapFragment weatherMapTab = new WeatherMapFragment();
                    return weatherMapTab;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "CURRENT";
                case 1:
                    return "FORECAST";
                case 2:
                    return "MAP";
            }
            return null;
        }
    }
}
