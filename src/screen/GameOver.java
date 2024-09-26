package screen;
import game.GameException;
import game.GameFrame;
import music.Music;
import utils.TextFont;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;

import java.io.IOException;

import static utils.ScreenUtils.getScreenHeight;
import static utils.ScreenUtils.getScreenWidth;

public class GameOver extends JFrame {
    private final Music music;
    private boolean isMuted = false;
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

        JButton homeScreenButton = homeScreenButton(pixelFont);

        JButton restartButton = restartButton(pixelFont);

        JButton exitButton = exitButton(pixelFont);
        JButton muteButton = muteButton();
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

    private static JButton exitButton(Font pixelFont) {//botão para sair do jogo
        JButton exitButtonGameOver = new JButton("Exit Game"); //botão para sair do jogo
        exitButtonGameOver.setFont(pixelFont);
        exitButtonGameOver.setForeground(Color.WHITE);
        exitButtonGameOver.setBackground(new Color(255, 69, 0));
        exitButtonGameOver.setFocusPainted(false);
        exitButtonGameOver.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Sair"
        exitButtonGameOver.addActionListener(e -> {
            System.exit(0);  // Fecha o programa
        });
        return exitButtonGameOver;
    }

    private JButton restartButton(Font pixelFont) {//botão para reiniciar o jogo
        JButton restartButton = new JButton("Restart Game"); //botão para reiniciar o jogo

        restartButton.setFont(pixelFont);
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(new Color(9, 149, 232));
        restartButton.setFocusPainted(false);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartButton.addActionListener(e -> {//volta a tela inicial
            dispose();
            try {
                new GameFrame().setVisible(true);  // Abre o jogo
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return restartButton;
    }

    private JButton homeScreenButton(Font pixelFont) {//botão para reiniciar o jogo
        JButton homeScreenButton = new JButton("Home Screen"); //botão para reiniciar o jogo
        homeScreenButton.setFont(pixelFont);
        homeScreenButton.setForeground(Color.WHITE);
        homeScreenButton.setBackground(new Color(110, 7, 35));
        homeScreenButton.setFocusPainted(false);
        homeScreenButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeScreenButton.addActionListener(e -> {//volta a tela inicial
            dispose();
            try {
                new HomeScreen().setVisible(true);  // Abre o jogo
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return homeScreenButton;
    }


    @Override
    public void dispose() {
        // Para a música quando a tela é fechada
        if (music != null) {
            music.stop();
        }
        super.dispose(); // Chama o metodo dispose da superclasse
    }

    private JButton muteButton() {//botão para mutar o som
        JButton muteButton = new JButton();
        muteButton.setBackground(new Color(248, 155, 155, 255));
        muteButton.setFocusPainted(false);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Carrega e redimensiona as imagens
        ImageIcon muteIcon = resizeImageIcon(new ImageIcon("src/assets/buttonSound.png"));
        ImageIcon unmuteIcon = resizeImageIcon(new ImageIcon("src/assets/buttonMuted.png"));
        muteButton.setIcon(muteIcon); // Define a imagem no botão

        muteButton.addActionListener(e -> {
            if (isMuted) {
                try {
                    music.play("music/gameover.wav"); // Retoma a música
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);

                } catch (GameException ex) {
                    throw new GameException("Error: " + ex);

                }
                muteButton.setIcon(muteIcon); // Muda para o ícone de mute
            } else {
                music.stop(); // Para a música
                muteButton.setIcon(unmuteIcon); // Muda para o ícone de unmute
            }
            isMuted = !isMuted; // Alterna o estado
        });

        return muteButton;
    }
    private ImageIcon resizeImageIcon(ImageIcon icon) {
        Image img = icon.getImage(); // Obtém a imagem
        Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Redimensiona
        return new ImageIcon(newImg); // Retorna uma nova ImageIcon
    }
}
