package weather.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    private Clip clip;
    private AudioInputStream audioInputStream;

    public void play(String fileName) {
        File soundFile = new File(fileName);

        try {
            audioInputStream =
                    AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (UnsupportedAudioFileException exception) {
            exception.printStackTrace();
        } catch (LineUnavailableException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }

        if (audioInputStream != null) {
            try {
                audioInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}