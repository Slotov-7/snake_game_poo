package screen;

import game.GameFrame;
import music.Musica;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomeScreen extends JFrame {
    private final Musica musica; // Referência para a instância de Musica

    public HomeScreen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Inicializa a música
        musica = new Musica(); // Inicializa a música
        musica.play("src/music/homescreen.wav"); // Toca a música da tela inicial

        // Dimensões da janela
        setTitle("Snake Game - Tela Inicial");
        int screenWidth = 1080; // Ou coloca getScreenWidth()
        int screenHeight = 720 ; // Ou coloca getScreenHeight()

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


        JButton playButton = new JButton("play");
        playButton.setFont(new Font("Serif", Font.PLAIN, 48));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(30, 144, 255));  // Azul
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão horizontalmente

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para iniciar o jogo
                System.out.println("Starting the game...");
                try {
                    new GameFrame().setVisible(true);  // Abre o jogo
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();  // Fecha a tela inicial
            }
        });

        // Botão "Créditos"
        JButton creditButton = new JButton("credits");
        creditButton.setFont(new Font("Serif", Font.PLAIN, 48));
        creditButton.setForeground(Color.WHITE);
        creditButton.setBackground(new Color(203, 161, 36));
        creditButton.setFocusPainted(false);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Créditos"
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Arte: Miguel Lucas\nProgramação: Miguel e Lucas\nMúsica: Lucas Miguel\nAplicação: Miguel da XJ6\nEstagiários: Guilherme, Lex e Débora\n© 2024 - Snake Game", "Créditos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botão "Sair"
        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("DialogInput", Font.PLAIN, 48));
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

        // Adiciona espaçamento entre os botões
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento superior
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(creditButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espaçamento entre os botões

        // Adiciona o painel de botões à tela principal
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel à janela
        add(backgroundPanel);
    }

    @Override
    public void dispose() {
        // Para a música quando a tela é fechada
        if (musica != null) {
            musica.stop();
        }
        super.dispose(); // Chama o método dispose da superclasse
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Executa a tela inicial
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
    }
}

