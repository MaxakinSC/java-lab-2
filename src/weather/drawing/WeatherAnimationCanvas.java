package weather.drawing;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Color;

public class WeatherAnimationCanvas extends Canvas implements Runnable {

    private final Image sun = Toolkit.getDefaultToolkit()
            .getImage("resources/sun.png");

    private final Image cloud1 = Toolkit.getDefaultToolkit()
            .getImage("resources/cloud1.png");

    private final Image cloud2 = Toolkit.getDefaultToolkit()
            .getImage("resources/cloud2.png");

    private final Image cloud2Avocado = Toolkit.getDefaultToolkit()
            .getImage("resources/cloud2_avogado.png");

    private final Image water1 = Toolkit.getDefaultToolkit()
            .getImage("resources/water1.png");

    private final Image water2 = Toolkit.getDefaultToolkit()
            .getImage("resources/water2.png");

    private final Image water3 = Toolkit.getDefaultToolkit()
            .getImage("resources/water3.png");

    private final MediaTracker tracker = new MediaTracker(this);

    private int cloud2X = 50;
    private int cloud2Y = 250;
    private int cloud2SpeedX = 2;
    private int cloud2SpeedY = 1;

    private int cloud1X = -180;
    private int drop1Y = 230;
    private int drop2Y = 270;
    private int drop3Y = 310;
    private int drop4Y = 250;
    private int drop5Y = 290;
    private int drop6Y = 330;

    private int sunSize = 120;
    private int sunSizeChange = 1;
    private int sunCounter = 0;

    public WeatherAnimationCanvas() {
        tracker.addImage(sun, 0);
        tracker.addImage(cloud1, 0);
        tracker.addImage(cloud2, 0);
        tracker.addImage(water1, 0);
        tracker.addImage(water2, 0);
        tracker.addImage(water3, 0);
        tracker.addImage(cloud2Avocado, 0);

        setBackground(new Color(210, 235, 255));
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(
                cloud2Avocado,
                cloud2X,
                cloud2Y,
                160,
                133,
                this
        );



        int sunX = 510 - sunSize / 2;
        int sunY = 100 - sunSize / 2;

        graphics.drawImage(
                sun,
                sunX,
                sunY,
                sunSize,
                sunSize,
                this
        );

        graphics.drawImage(
                cloud1,
                cloud1X,
                120,
                180,
                120,
                this
        );

        graphics.drawImage(
                water1,
                cloud1X + 45,
                drop1Y,
                25,
                35,
                this
        );

        graphics.drawImage(
                water2,
                cloud1X + 80,
                drop2Y,
                25,
                35,
                this
        );

        graphics.drawImage(
                water3,
                cloud1X + 115,
                drop3Y,
                25,
                35,
                this
        );

        graphics.drawImage(
                water2,
                cloud1X + 60,
                drop4Y,
                20,
                30,
                this
        );

        graphics.drawImage(
                water3,
                cloud1X + 95,
                drop5Y,
                20,
                30,
                this
        );

        graphics.drawImage(
                water1,
                cloud1X + 130,
                drop6Y,
                20,
                30,
                this
        );
    }

    @Override
    public void run() {
        try {
            tracker.waitForAll();
        } catch (InterruptedException exception) {
            return;
        }

        if (tracker.isErrorAny()) {
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            sunCounter++;

            if (sunCounter >= 3) {
                sunSize += sunSizeChange;

                if (sunSize >= 135 || sunSize <= 120) {
                    sunSizeChange = -sunSizeChange;
                }

                sunCounter = 0;
            }



            cloud1X += 2;
            cloud2X += cloud2SpeedX;
            cloud2Y += cloud2SpeedY;

            if (cloud2X <= 0 || cloud2X >= getWidth() - 160) {
                cloud2SpeedX = -cloud2SpeedX;
            }

            if (cloud2Y <= 0 || cloud2Y >= getHeight() - 133) {
                cloud2SpeedY = -cloud2SpeedY;
            }



            if (cloud1X > getWidth()) {
                cloud1X = -180;
            }

            drop1Y += 2;
            drop2Y += 3;
            drop3Y += 1;
            drop4Y += 3;
            drop5Y += 2;
            drop6Y += 4;

            if (drop1Y > 360) {
                drop1Y = 230;
            }

            if (drop2Y > 360) {
                drop2Y = 230;
            }

            if (drop3Y > 360) {
                drop3Y = 230;
            }

            if (drop4Y > 360) {
                drop4Y = 230;
            }

            if (drop5Y > 360) {
                drop5Y = 230;
            }

            if (drop6Y > 360) {
                drop6Y = 230;
            }

            repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException exception) {
                return;
            }
        }
    }
}