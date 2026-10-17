package weather.drawing;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class LogoCanvas extends Canvas {
    private final Image logo = Toolkit.getDefaultToolkit()
            .getImage("resources/logo.png");
    private int textX = 0;

    @Override
    public void paint(Graphics graphics) {
        //graphics.drawString("Погодний інформер", 170, 180);
        graphics.drawImage(logo, 0, 0, getWidth(), getHeight(), this);

        graphics.drawString("Погодний інформер", textX, 30);
    }

    public void moveText() {
        textX += 2;

        int textWidth = getFontMetrics(getFont())
                .stringWidth("Погодний інформер");

        if (textX > getWidth()) {
            textX = -textWidth;
        }
    }
}
