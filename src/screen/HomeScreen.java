package screen;

import music.Music;
import utils.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;




public class HomeScreen extends JFrame {
    private final Music music; // Referência para a instância de Music

    public HomeScreen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Font pixelFont = TextFont.getPixelFont(32f);
        // Inicializa a música
        music = new Music(); // Inicializa a música
        music.play("music/homescreen.wav"); // Toca a música da tela inicial

        // Dimensões da janela
        setTitle("SNAKE GAME - HOME SCREEN");
        int screenWidth = 1080; //ou getScreenWidth();
        int screenHeight = 720; //ou getScreenHeight();

        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a janela

        // Painel principal com a imagem de fundo
        BackgroundPanelHomeScreen backgroundPanel = new BackgroundPanelHomeScreen();
        backgroundPanel.setLayout(new BorderLayout());

        // Painel de botões com BoxLayout (alinhamento vertical)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50, 0)); // Fundo transparente
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Alinha na vertical

        // Estado do som (mutado ou não)
        AtomicBoolean isMuted = new AtomicBoolean(false);
        JButton playButton = UtilsButtons.createPlayButton(pixelFont, music, isMuted.get(), this);
        JButton creditButton = UtilsButtons.createCreditButton(pixelFont);
        JButton exitButton = UtilsButtons.createExitButton(pixelFont);
        // Botão para mutar/desmutar
        JButton muteButton = UtilsButtons.createMuteButton(music, isMuted, "music/homescreen.wav");


        // Adiciona espaçamento entre os botões
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento superior
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(creditButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(muteButton); // Adiciona o botão de mute
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões

        // Adiciona o painel de botões à tela principal
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel à janela
        add(backgroundPanel);
    }


    @Override
    public void dispose() {// Para a música quando a tela é fechada
        if (music != null) {
            music.stop();
        }
        super.dispose();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
    }
}
