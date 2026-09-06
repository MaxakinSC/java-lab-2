package weather.app;

import weather.generator.RandomWeatherGenerator;
import weather.input.ConsoleWeatherInput;
import weather.model.WeatherState;

public class Application {
    private final RandomWeatherGenerator generator = new RandomWeatherGenerator();
    private final ConsoleWeatherInput input = new ConsoleWeatherInput();

    public void run() {
        int inputMode = input.readInputMode();
        //System.out.println("Обраний режим: " + inputMode);
        WeatherState weatherState;

        if (inputMode == 1) {
            weatherState = input.readWeatherState();
        } else {
            weatherState = generator.generate();
            printWeatherState(weatherState);
        }
    }

    private void printWeatherState(WeatherState weatherState) {
        System.out.println (
                "Згенерований стан погоди: "
                        + "sun=" + (weatherState.isSun() ? 1 : 0)
                        + ", rain=" + (weatherState.isRain() ? 1 : 0)
                        + ", snow=" + (weatherState.isSnow() ? 1 : 0)
                        + ", cloud=" + (weatherState.isCloud() ? 1 : 0)
        );
    }
}
