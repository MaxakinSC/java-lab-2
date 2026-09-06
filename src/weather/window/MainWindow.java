package weather.window;

import javax.swing.JFrame;
import weather.drawing.LogoCanvas;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Погодний інформер");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LogoCanvas logoCanvas = new LogoCanvas();
        add(logoCanvas);
    }
}
