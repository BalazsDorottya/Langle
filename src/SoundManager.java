import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SoundManager extends JFrame {
    public static void winGame() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("wav/winning.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

    public static void loseGame() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("wav/loosing.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("IO error");
        }
    }

}
