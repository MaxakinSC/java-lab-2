package weather.window;

import javax.swing.JFrame;
import weather.drawing.AuthorCanvas;

public class AuthorWindow extends JFrame {
    public AuthorWindow() {
        setTitle("Про автора");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        AuthorCanvas authorCanvas = new AuthorCanvas();
        add(authorCanvas);
    }
}
