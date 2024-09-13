package screen;

import game.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        // Configurações da janela
        setTitle("Snake Game - Tela Inicial");
        setSize(1080, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centraliza a janela

        // Painel principal
        JPanel panel = new SnakePanel();
        panel.setLayout(new BorderLayout());

        // Título com uma fonte customizada e cor
        JLabel titleLabel = new JLabel("SNAKE GAME", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(new Color(0, 255, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de botões com BoxLayout (alinhamento vertical)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50)); // Mesmo fundo do painel principal
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Alinha na vertical

        // Botão "Jogar"
        JButton playButton = new JButton("Jogar");
        playButton.setFont(new Font("Arial", Font.PLAIN, 48));
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(new Color(30, 144, 255));  // Azul
        playButton.setFocusPainted(false);
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão horizontalmente

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Código para iniciar o jogo
                System.out.println("Starting the game...");
                new GameFrame().setVisible(true);  // Abre o jogo
                dispose();  // Fecha a tela inicial
            }
        });

        // Botão "Créditos"
        JButton creditButton = new JButton("Créditos");
        creditButton.setFont(new Font("Arial", Font.PLAIN, 48));
        creditButton.setForeground(Color.WHITE);
        creditButton.setBackground(new Color(189, 124, 0));
        creditButton.setFocusPainted(false);
        creditButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ação do botão "Créditos"
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Arte: Miguel Lucas\nProgramação: Miguel Lucas\nMúsica: Miguel Lucas\n© 2024 - Snake Game", "Créditos", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Botão "Sair"
        JButton exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 48));
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

        // Adiciona o painel de botões à tela principal
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Adiciona o painel à janela
        add(panel);
    }

    public static void main(String[] args) {
        // Executa a tela inicial
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.setVisible(true);
    }
}

