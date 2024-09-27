package screen;

import music.Music;
import utils.TextFont;
import utils.UtilsButtons;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;


public class GameOver extends JFrame {
    private final Music music;

    public GameOver(int score) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Toca a música de game over
        music = new Music();
        music.play("music/gameover.wav");
        Font pixelFont = TextFont.getPixelFont(32f);
        // Configurações da janela
        this.setTitle("Game Over");
        int screenWidth =  1080; //ou getScreenWidth();
        int screenHeight =  720; //ou getScreenHeight();

        setSize(screenWidth,screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal com a imagem de fundo
        BackgroundPanelGameOverScreen backgroundPanel = new BackgroundPanelGameOverScreen();
        backgroundPanel.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        // Painel para o Scoreboard, transparente
        JPanel scorePanel = new JPanel();
        scorePanel.setOpaque(false); // Tornar o painel transparente
        scorePanel.setLayout(new FlowLayout());

        JLabel scoreLabel = new JLabel("Your score: " + score + "!", SwingConstants.CENTER);//mostra a pontuação
        scoreLabel.setFont(pixelFont);
        scoreLabel.setForeground(Color.WHITE);
        scorePanel.add(scoreLabel);

        backgroundPanel.add(scorePanel, BorderLayout.NORTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50, 0));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        this.add(buttonPanel, BorderLayout.SOUTH);

        JButton homeScreenButton = UtilsButtons.createHomeScreenButton(pixelFont, this);
        JButton restartButton = UtilsButtons.createRestartButton(pixelFont, this);
        JButton exitButton = UtilsButtons.createExitButton(pixelFont);
        AtomicBoolean isMuted = new AtomicBoolean(false);
        JButton muteButton = UtilsButtons.createMuteButton(music, isMuted, "music/gameover.wav");
        // Adiciona os botões ao painel
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(homeScreenButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento superior
        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(muteButton); // Adiciona o botão de mute
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);
    }

    @Override
    public void dispose() {
        // Para a música quando a tela é fechada
        if (music != null) {
            music.stop();
        }
        super.dispose(); // Chama o metodo dispose da superclasse
    }
}
