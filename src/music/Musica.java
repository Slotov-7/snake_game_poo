package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    private Clip clip;
    private boolean isPaused = false;
    private long clipTimePosition = 0;

    // Método para tocar a música
    public void play(String arquivo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(arquivo).getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Método para parar a música
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }

    // Método para pausar a música
    public void pause() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
            isPaused = true;
        }
    }

    // Método para retomar a música
    public void resume() {
        if (clip != null && isPaused) {
            clip.setMicrosecondPosition(clipTimePosition);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isPaused = false;
        }
    }

    // Verifica se está pausado
    public boolean isPaused() {
        return isPaused;
    }
}
