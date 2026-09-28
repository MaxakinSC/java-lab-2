package weather.window;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class InfoWindow extends JFrame {

    public InfoWindow() {
        setTitle("Інформація");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel infoLabel = new JLabel("Погодний інформер — програма для відображення стану погоди.");
        add(infoLabel);
    }
}