package mu.zz.axin.weatherion;

public class Weather {
    private int id;
    private String dt_txt;
    private String temp;
    private String pressure;
    private String weathermain;
    private String weatherdescription;
    private String weathericon;
    private String wind;

    public Weather() {
    }

    public Weather(String dt_txt, String temp, String pressure, String weathermain, String weatherdescription, String weathericon, String wind) {
        this.dt_txt = dt_txt;
        this.temp = temp;
        this.pressure = pressure;
        this.weathermain = weathermain;
        this.weatherdescription = weatherdescription;
        this.weathericon = weathericon;
        this.wind = wind;
    }

    public Weather(int id, String dt_txt, String temp, String pressure, String weathermain, String weatherdescription, String weathericon, String wind) {
        this.id = id;
        this.dt_txt = dt_txt;
        this.temp = temp;
        this.pressure = pressure;
        this.weathermain = weathermain;
        this.weatherdescription = weatherdescription;
        this.weathericon = weathericon;
        this.wind = wind;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getWeathermain() {
        return weathermain;
    }

    public void setWeathermain(String weathermain) {
        this.weathermain = weathermain;
    }

    public String getWeatherdescription() {
        return weatherdescription;
    }

    public void setWeatherdescription(String weatherdescription) {
        this.weatherdescription = weatherdescription;
    }

    public String getWeathericon() {
        return weathericon;
    }

    public void setWeathericon(String weathericon) {
        this.weathericon = weathericon;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }
}
