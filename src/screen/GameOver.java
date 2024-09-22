package screen;
import game.GameFrame;
import music.Musica;
import utils.TextFont;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameOver extends JFrame {
    private final Musica musica;

    public GameOver(int score) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Toca a música de game over
        musica = new Musica();
        musica.play("snake_game_poo-master/src/music/gameover.wav");
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

        JLabel scoreLabel = new JLabel("Your score: " + score, SwingConstants.CENTER);
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

        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(homeScreenButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento superior
        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões

        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(backgroundPanel);
    }

    private static JButton exitButton(Font pixelFont) {
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(pixelFont);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255, 69, 0));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Sair"
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Fecha o programa
            }
        });
        return exitButton;
    }

    private JButton restartButton(Font pixelFont) {
        JButton restartButton = new JButton("Restart Game"); //botão para reiniciar o jogo

        restartButton.setFont(pixelFont);
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(new Color(9, 149, 232));
        restartButton.setFocusPainted(false);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//volta a tela inicial
                dispose();
                try {
                    new GameFrame().setVisible(true);  // Abre o jogo
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return restartButton;
    }

    private JButton homeScreenButton(Font pixelFont) {
        JButton homeScreenButton = new JButton("Home Screen"); //botão para reiniciar o jogo
        homeScreenButton.setFont(pixelFont);
        homeScreenButton.setForeground(Color.WHITE);
        homeScreenButton.setBackground(new Color(110, 7, 35));
        homeScreenButton.setFocusPainted(false);
        homeScreenButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//volta a tela inicial
                dispose();
                try {
                    new HomeScreen().setVisible(true);  // Abre o jogo
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return homeScreenButton;
    }


    @Override
    public void dispose() {
        // Para a música quando a tela é fechada
        if (musica != null) {
            musica.stop();
        }
        super.dispose(); // Chama o método dispose da superclasse
    }

}
