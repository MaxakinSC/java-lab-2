package weather.drawing;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;

public class AuthorCanvas extends Canvas {
    private final Image authorImage = Toolkit.getDefaultToolkit()
            .getImage("resources/author.png");

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(authorImage, 40, 40, 150, 150, this);

        graphics.drawString("ПІБ: Вайн Максим Євгенович", 220, 80);
        graphics.drawString("Група: 1БС-25б", 220, 110);
        graphics.drawString("Факультет: ФІТКІ", 220, 140);
    }
}
