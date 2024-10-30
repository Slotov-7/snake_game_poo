package utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import game.GameFrame;
import music.Music;
import screen.HomeScreen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class UtilsButtons {



    // Metodo para criar o botão "Play"
    public static JButton createPlayButton(Font pixelFont, Music music, boolean isMuted, JFrame frame) {
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
            frame.dispose();  // Fecha a tela inicial
        });
        return playButton;
    }

    // Metodo para criar o botão "Credits"
    public static JButton createCreditButton(Font pixelFont) {
        JButton creditButton = new JButton("Credits");
        creditButton.setFont(pixelFont);
        creditButton.setForeground(Color.WHITE);
        creditButton.setBackground(new Color(203, 161, 36));
        creditButton.setFocusPainted(false);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        creditButton.addActionListener(e -> {
            JLabel creditsLabel = new JLabel("<html>Developers: <br> Allex Lemos de Souza Pinheiro<br>\n <br> Débora Diana Gonçalves dos Santos <br>\n <br> Guilherme Henrique Santos Araújo <br>\n <br> Miguel Lucas Santana Freire <br> \n<br>© 2024 - Snake Game<br> \n <br> All Rights Reserved</html>");
            Font pixelFont1 = TextFont.getPixelFont(16f);
            creditsLabel.setFont(pixelFont1);
            JOptionPane.showMessageDialog(null, creditsLabel, "Credits", JOptionPane.INFORMATION_MESSAGE);
        });
        return creditButton;
    }

    // Metodo para criar o botão "Exit"
    public static JButton createExitButton(Font pixelFont) {
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(pixelFont);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255, 69, 0));
        exitButton.setFocusPainted(false);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Sair"
        exitButton.addActionListener(e -> {
            System.exit(0);  // Fecha o programa
        });
        return exitButton;
    }

    // Metodo para criar o botão "Mute" usando AtomicBoolean para alternar o estado de som
    public static JButton createMuteButton(Music music, AtomicBoolean isMuted, String musicFile) {
        JButton muteButton = new JButton();
        muteButton.setBackground(Color.GRAY);
        muteButton.setFocusPainted(false);
        muteButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ícones de som ativado e desativado
        ImageIcon muteIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(UtilsButtons.class.getClassLoader().getResource("assets/buttonSound.png"))));
        ImageIcon unmuteIcon = resizeImageIcon(new ImageIcon(Objects.requireNonNull(UtilsButtons.class.getClassLoader().getResource("assets/buttonMuted.png"))));

        // Define o ícone inicial com base no estado de mute
        muteButton.setIcon(isMuted.get() ? unmuteIcon : muteIcon);

        // Ação do botão "Mute"
        muteButton.addActionListener(e -> {
            if (isMuted.get()) {
                try {
                    music.play(musicFile); // Toca a música novamente
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                muteButton.setIcon(muteIcon); // Atualiza ícone para mute
            } else {
                music.stop(); // Para a música
                muteButton.setIcon(unmuteIcon); // Atualiza ícone para unmute
            }
            isMuted.set(!isMuted.get()); // Alterna o estado de mute
        });

        return muteButton;
    }

    // Metodo auxiliar para redimensionar ícones
    private static ImageIcon resizeImageIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }
    public static JButton createRestartButton(Font pixelFont, JFrame currentFrame) {
        JButton restartButton = new JButton("Restart Game");

        restartButton.setFont(pixelFont);
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(new Color(9, 149, 232));
        restartButton.setFocusPainted(false);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartButton.addActionListener(e -> {
            currentFrame.dispose();  // Fecha a janela atual
            try {
                new GameFrame().setVisible(true);  // Reinicia o jogo
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return restartButton;
    }
    public static JButton createHomeScreenButton(Font pixelFont, JFrame currentFrame) {

        JButton homeScreenButton = new JButton("Home Screen");

        homeScreenButton.setFont(pixelFont);
        homeScreenButton.setForeground(Color.WHITE);
        homeScreenButton.setBackground(new Color(110, 7, 35));
        homeScreenButton.setFocusPainted(false);
        homeScreenButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        homeScreenButton.addActionListener(e -> {
            currentFrame.dispose();  // Fecha a janela atual
            try {
                new HomeScreen().setVisible(true);  // Volta para a tela inicial
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return homeScreenButton;
    }
}
