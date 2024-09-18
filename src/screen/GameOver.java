package screen;
import music.Musica;


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
        musica.play("src/music/gameover.wav");
        this.setTitle("Game Over");
        int screenWidth =  1080; //ou getScreenWidth();
        int screenHeight =  720; //ou getScreenHeight();
        setSize(screenWidth,screenHeight);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JLabel scoreLabel = new JLabel("Scoreboard: " + score, SwingConstants.CENTER);//mostra a pontuação
        scoreLabel.setFont(new Font("Ink Free", Font.BOLD, 40));
        scoreLabel.setForeground(Color.RED);
        this.add(scoreLabel, BorderLayout.NORTH);

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        gameOverLabel.setForeground(Color.RED);
        this.add(gameOverLabel, BorderLayout.CENTER);

        JPanel buttonPanel = jPanel(screenWidth, screenHeight);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    private JPanel jPanel(int screenWidth, int screenHeight) {
        JButton restartButton = new JButton("Restart game"); //botão para reiniciar o jogo
        restartButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setOpaque(false);
        restartButton.setBounds((screenWidth - 300) / 2, screenHeight / 2 + 50, 300, 40);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//volta a tela inicial
                dispose();
                try {
                    new HomeScreen().setVisible(true);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restartButton);
        return buttonPanel;
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
