package music;
import javax.sound.sampled.*;

import java.io.File;
import java.io.IOException;


public class Musica {
    File file = new File("src/music/som.wav");

    public static void main(String[] args) {
        Musica musica = new Musica();
        try {
            musica.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(String.valueOf(file)).getAbsoluteFile());

          Clip clip = AudioSystem.getClip();
          clip.open(audioStream);
          clip.start();
          clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
}


