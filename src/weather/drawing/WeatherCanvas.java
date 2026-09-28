package weather.drawing;

import weather.model.WeatherState;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class WeatherCanvas extends Canvas {
    private WeatherState weatherState;
    private final Random random = new Random();
    private int precipitationCount = 6;

    public WeatherCanvas(WeatherState weatherState) {
        this.weatherState = weatherState;
    }

    @Override
    public void paint(Graphics graphics) {
        if (weatherState.isSun()) {
            drawSun(graphics);
        }

        if (weatherState.isCloud()) {
            drawCloud(graphics);
        }

        if (weatherState.isRain()) {
            drawRain(graphics);
        }

        if (weatherState.isSnow()) {
            drawSnow(graphics);
        }
    }

    public void setWeatherState(WeatherState weatherState) {
        this.weatherState = weatherState;
        repaint();
    }

    public void setPrecipitationCount(int precipitationCount) {
        this.precipitationCount = precipitationCount;
        repaint();
    }

    private void drawSun(Graphics graphics) {
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(50, 50, 80, 80);

        graphics.drawLine(90, 30, 90, 45);
        graphics.drawLine(90, 135, 90, 150);
        graphics.drawLine(30, 90, 45, 90);
        graphics.drawLine(135, 90, 150, 90);

        graphics.drawLine(58, 58, 45, 45);
        graphics.drawLine(122, 58, 135, 45);
        graphics.drawLine(58, 122, 45, 135);
        graphics.drawLine(122, 122, 135, 135);
    }

    private void drawRain(Graphics graphics) {
        graphics.setColor(Color.BLUE);

        for (int i = 0; i < precipitationCount; i++) {
            int x = random.nextInt(50, 430);
            int y = random.nextInt(180, 300);

            graphics.drawLine(x, y, x - 10, y + 25);
        }
    }

    private void drawSnow(Graphics graphics) {
        graphics.setColor(Color.CYAN);

        for (int i = 0; i < precipitationCount; i++) {
            int x = random.nextInt(50, 430);
            int y = random.nextInt(180, 300);

            drawSnowflake(graphics, x, y);
        }
    }

    private void drawSnowflake(Graphics graphics, int x, int y) {
        graphics.drawLine(x, y - 10, x, y + 10);
        graphics.drawLine(x - 10, y, x + 10, y);
        graphics.drawLine(x - 7, y - 7, x + 7, y + 7);
        graphics.drawLine(x + 7, y - 7, x - 7, y + 7);
    }

    private void drawCloud(Graphics graphics) {
        graphics.setColor(Color.LIGHT_GRAY);

        graphics.fillOval(170, 100, 100, 70);
        graphics.fillOval(220, 70, 110, 100);
        graphics.fillOval(280, 100, 100, 70);
        graphics.fillRect(210, 120, 130, 50);
    }
}
