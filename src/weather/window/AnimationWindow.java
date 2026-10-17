package weather.window;

import javax.swing.JFrame;
import weather.drawing.WeatherAnimationCanvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import weather.sound.SoundPlayer;

public class AnimationWindow extends JFrame {

    public AnimationWindow() {
        setTitle("Анімація погоди");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        WeatherAnimationCanvas animationCanvas = new WeatherAnimationCanvas();

        add(animationCanvas);

        Thread animationThread = new Thread(animationCanvas);
        animationThread.start();

        SoundPlayer soundPlayer = new SoundPlayer();

        soundPlayer.play(
                "resources/Water_Under_The_Eaves.wav"
        );


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                animationThread.interrupt();
                soundPlayer.stop();
            }
        });
    }
}