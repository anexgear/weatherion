package mu.zz.axin.weatherion.controler;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import org.json.JSONException;

import mu.zz.axin.weatherion.model.NetworkFetcher;
import mu.zz.axin.weatherion.model.database.WeatherDataBaseHelper;

public class WeatherFetchrService extends Service {
    WeatherDataBaseHelper mWeatherDataBaseHelper;
    public WeatherFetchrService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        FetchItemsTask task = new FetchItemsTask();
        task.execute();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            NetworkFetcher networkFetcher = new NetworkFetcher();
            try {
                networkFetcher.getWeatherForecast(getApplication());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }

    }
}
