package music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    private Clip clip;

    // Metodo para tocar a música
    public void play(String arquivo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL musicUrl = getClass().getClassLoader().getResource(arquivo);
        if (musicUrl == null) {
            System.out.println("Música não encontrada: " + arquivo);
            return; // Retorna se a música não for encontrada
        }

        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    // Metodo para parar a música
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
