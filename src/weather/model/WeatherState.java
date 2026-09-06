package weather.model;

public class WeatherState {
    private final boolean sun;
    private final boolean rain;
    private final boolean snow;
    private final boolean cloud;

    public WeatherState(boolean sun, boolean rain, boolean snow, boolean cloud) {
        this.sun = sun;
        this.rain = rain;
        this.snow = snow;
        this.cloud = cloud;
    }

    public boolean isSun() {
        return sun;
    }

    public boolean isRain() {
        return rain;
    }

    public boolean isSnow() {
        return snow;
    }

    public boolean isCloud() {
        return cloud;
    }
}
