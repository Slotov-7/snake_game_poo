package screen;

import game.GameException;
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

public class HomeScreen extends JFrame {
    private final Musica musica; // Referência para a instância de Musica
    private boolean isMuted = false; // Estado do som (mutado ou não)

    public HomeScreen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Font pixelFont = TextFont.getPixelFont(32f);
        // Inicializa a música
        musica = new Musica(); // Inicializa a música
        musica.play("snake_game_poo-master/src/music/homescreen.wav"); // Toca a música da tela inicial

        // Dimensões da janela
        setTitle("SNAKE GAME - HOME SCREEN");
        int screenWidth = 1080;
        int screenHeight = 720;

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
        JButton muteButton = muteButton(pixelFont); // Botão para mutar/desmutar

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

    private JButton muteButton(Font pixelFont) {
        JButton muteButton = new JButton();
        muteButton.setBackground(new Color(248, 155, 155, 255));
        muteButton.setFocusPainted(false);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Carrega e redimensiona as imagens
        ImageIcon muteIcon = resizeImageIcon(new ImageIcon("snake_game_poo-master/src/assets/buttonSound.png"), 30, 30);
        ImageIcon unmuteIcon = resizeImageIcon(new ImageIcon("snake_game_poo-master/src/assets/buttonMuted.png"), 30, 30);
        muteButton.setIcon(muteIcon); // Define a imagem no botão

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMuted) {
                    try {
                        musica.play("src/music/homescreen.wav"); // Retoma a música
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                        throw new RuntimeException(ex);

                    } catch (GameException ex) {
                        throw new GameException("Error: " + ex);

                    }
                    muteButton.setIcon(muteIcon); // Muda para o ícone de mute
                } else {
                    musica.stop(); // Para a música
                    muteButton.setIcon(unmuteIcon); // Muda para o ícone de unmute
                }
                isMuted = !isMuted; // Alterna o estado
            }
        });

        return muteButton;
    }

    private ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage(); // Obtém a imagem
        Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH); // Redimensiona
        return new ImageIcon(newImg); // Retorna uma nova ImageIcon
    }

    private JButton exitButton(Font pixelFont) {
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

    private JButton creditButton(Font pixelFont) {
        JButton creditButton = new JButton("Credits");
        creditButton.setFont(pixelFont);
        creditButton.setForeground(Color.WHITE);
        creditButton.setBackground(new Color(203, 161, 36));
        creditButton.setFocusPainted(false);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Créditos"
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel creditsLabel = new JLabel("<html>Developers: <br>\n<br> Alicia Vitoria Sousa Santos <br>\n <br> Allex Lemos de Souza Pinheiro<br>\n <br> Débora Diana Gonçalves dos Santos <br>\n <br> Guilherme Henrique Santos Araújo <br>\n <br> Miguel Lucas Santana Freire <br>\n <br> Rafael Gomes Oliveira Santos <br>\n<br>© 2024 - Snake Game<br> \n <br> All Rights Reserved</html>");
                Font pixelFont = TextFont.getPixelFont(16f);
                creditsLabel.setFont(pixelFont);
                JOptionPane.showMessageDialog(null, creditsLabel, "Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return creditButton;
    }

    private JButton jButton(Font pixelFont) {
        JButton playButton = new JButton("Play");
        playButton.setFont(pixelFont);
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(30, 144, 255));  // Azul
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Para a música se não estiver mutada
                if (!isMuted) {
                    musica.stop();
                }

                try {
                    new GameFrame().setVisible(true);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();  // Fecha a tela inicial
            }
        });
        return playButton;
    }

    @Override
    public void dispose() {
        if (musica != null) {
            musica.stop();
        }
        super.dispose();
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
    }
}
