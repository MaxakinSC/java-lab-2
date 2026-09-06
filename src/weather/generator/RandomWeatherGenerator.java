package weather.generator;

import weather.model.WeatherState;

import java.util.Random;

public class RandomWeatherGenerator {
    private final Random random = new Random();

    public WeatherState generate() {
        boolean sun = random.nextBoolean();
        boolean rain = random.nextBoolean();
        boolean snow = random.nextBoolean();
        boolean cloud = random.nextBoolean();

        return new WeatherState(sun, rain, snow, cloud);
    }
}
