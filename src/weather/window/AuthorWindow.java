package weather.window;

import javax.swing.*;

import weather.drawing.AuthorCanvas;

import java.awt.*;

public class AuthorWindow extends JFrame {
    public AuthorWindow() {
        setTitle("Про автора");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setCursor(new Cursor(Cursor.HAND_CURSOR));
        //setIconImage(new Icon());

        AuthorCanvas authorCanvas = new AuthorCanvas();
        add(authorCanvas);
    }
}
