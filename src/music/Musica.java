package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica {
    private Clip clip;

    // Método para tocar a música
    public void play(String arquivo) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // Carrega o arquivo de áudio
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(arquivo).getAbsoluteFile());

        // Cria o Clip e abre o áudio
        clip = AudioSystem.getClip();
        clip.open(audioStream);

        // Toca o áudio e configura o loop
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY); // O áudio vai se repetir continuamente
    }

    // Método para parar a música
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close(); // Fecha o Clip para liberar recursos
        }
    }
}
