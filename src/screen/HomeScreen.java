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
import java.util.Objects;

//import static utils.ScreenUtils.getScreenHeight;
//import static utils.ScreenUtils.getScreenWidth;

public class HomeScreen extends JFrame {
    private final Music music; // Referência para a instância de Music
    private boolean isMuted = false; // Estado do som (mutado ou não)

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

        JButton playButton = jButton(pixelFont);
        JButton creditButton = creditButton(pixelFont);
        JButton exitButton = exitButton(pixelFont);
        // Botão para mutar/desmutar
        JButton muteButton = muteButton(); // Botão para mutar/desmutar

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

    private JButton muteButton() {// Botão para mutar/desmutar
        JButton muteButton = new JButton();
        muteButton.setBackground(new Color(248, 155, 155, 255));
        muteButton.setFocusPainted(false);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Carrega e redimensiona as imagens

        ImageIcon muteIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/buttonSound.png"))));
        if (muteIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Ícone de mute não encontrado: assets/buttonSound.png");
        }
        ImageIcon unmuteIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/buttonMuted.png"))));
        if (unmuteIcon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Ícone de unmute não encontrado: assets/buttonMuted.png");
        }

        muteButton.setIcon(muteIcon); // Define a imagem no botão

        muteButton.addActionListener(e -> {// Ação do botão
            if (isMuted) {
                try {
                    music.play("music/homescreen.wav"); // Retoma a música
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

    private JButton exitButton(Font pixelFont) {// Botão "Sair"
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(pixelFont);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255, 69, 0));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Sair"
        exitButton.addActionListener(e -> {// Fecha o programa
            System.exit(0);  // Fecha o programa
        });
        return exitButton;
    }

    private JButton creditButton(Font pixelFont) {// Botão "Créditos"
        JButton creditButton = new JButton("Credits");
        creditButton.setFont(pixelFont);
        creditButton.setForeground(Color.WHITE);
        creditButton.setBackground(new Color(203, 161, 36));
        creditButton.setFocusPainted(false);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Créditos"
        creditButton.addActionListener(e -> {
            JLabel creditsLabel = new JLabel("<html>Developers: <br>\n<br> Alicia Vitoria Sousa Santos <br>\n <br> Allex Lemos de Souza Pinheiro<br>\n <br> Débora Diana Gonçalves dos Santos <br>\n <br> Guilherme Henrique Santos Araújo <br>\n <br> Miguel Lucas Santana Freire <br>\n <br> Rafael Gomes Oliveira Santos <br>\n<br>© 2024 - Snake Game<br> \n <br> All Rights Reserved</html>");
            Font pixelFont1 = TextFont.getPixelFont(16f);
            creditsLabel.setFont(pixelFont1);
            JOptionPane.showMessageDialog(null, creditsLabel, "Credits", JOptionPane.INFORMATION_MESSAGE);
        });
        return creditButton;
    }

    private JButton jButton(Font pixelFont) {// Botão "Jogar"
        JButton playButton = new JButton("Play");
        playButton.setFont(pixelFont);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(30, 144, 255));  // Azul
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(e -> {
            // Para a música se não estiver mutada
            if (!isMuted) {
                music.stop();
            }

            try {
                new GameFrame().setVisible(true);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
            dispose();  // Fecha a tela inicial
        });
        return playButton;
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
