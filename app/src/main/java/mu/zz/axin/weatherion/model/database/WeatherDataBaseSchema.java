package mu.zz.axin.weatherion.model.database;

public class WeatherDataBaseSchema {
    public static final class WeatherTable {
        public static final String TABLE_NAME = "weather_forecast_table";

        public static final class Colums {
            public static final String KEY_ID = "_id";
            public static final String KEY_DATETIME = "_dt_txt";
            public static final String KEY_TEMPERATURE = "_temp";
            public static final String KEY_PRESSURE = "_pressure";
            public static final String KEY_HUMIDITY = "_humidity";
            public static final String KEY_WIND_SPEED = "_wind_speed";
            public static final String KEY_WEATHER_ICON = "_weather_icon";
        }

    }
}
