package weather.window;

import javax.swing.JFrame;

import weather.drawing.WeatherCanvas;
import weather.model.WeatherState;

public class WeatherWindow extends JFrame {
    //private final WeatherState weatherState;

    public WeatherWindow(WeatherState weatherState) {
        //this.weatherState = weatherState;

        setTitle("Стан погоди");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        WeatherCanvas weatherCanvas = new WeatherCanvas(weatherState);
        add(weatherCanvas);
    }
}
