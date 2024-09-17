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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import static utils.ScreenUtils.getScreenHeight;
import static utils.ScreenUtils.getScreenWidth;

public class HomeScreen extends JFrame {
    private final Musica musica; // Referência para a instância de Musica


    public HomeScreen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        // Configurações da janela
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
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Título com uma fonte customizada e cor
//        JLabel titleLabel = new JLabel("SNAKE GAME", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
//        titleLabel.setForeground(new Color(0, 255, 0));
//        panel.add(titleLabel, BorderLayout.NORTH);

        // Painel de botões com BoxLayout (alinhamento vertical)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(50, 50, 50, 0)); // Fundo transparente
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Alinha na vertical

        // Botão "Jogar"
        JButton playButton = new RoundButton("Jogar");
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
                try {
                    new GameFrame().setVisible(true);  // Abre o jogo
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
                dispose();  // Fecha a tela inicial
            }
        });

        // Botão "Créditos"
        JButton creditButton = new RoundButton("Créditos");
        creditButton.setFont(new Font("Arial", Font.PLAIN, 48));
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
        JButton exitButton = new RoundButton("Sair");
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

class RoundButton extends JButton {
    private boolean hover = false; // Indica quando o mouse está sobre o botão

    public RoundButton(String label) {
        super(label);

        setFocusPainted(false);  // Remove o foco padrão
        setContentAreaFilled(false);  // Remove o preenchimento padrão
        setOpaque(false);  // Torna o botão transparente para desenhar o fundo customizado
        setBorderPainted(false);  // Remove a borda padrão
        setMargin(new Insets(10, 20, 10, 20));  // Adiciona preenchimento ao redor do texto

        // Listener para o efeito hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Suaviza as bordas

        // Define a cor do botão dependendo do estado de hover
        if (hover) {
            g2.setColor(getBackground().darker());  // Um tom mais escuro para hover
        } else {
            g2.setColor(getBackground());  // Cor normal
        }

        // Desenha o fundo arredondado
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);  // Ajusta o arredondamento

        // Desenha o texto
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int stringWidth = fm.stringWidth(getText());
        int stringHeight = fm.getAscent();
        g2.drawString(getText(), (getWidth() - stringWidth) / 2, (getHeight() + stringHeight) / 2 - 3);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 50, 50);  // Borda arredondada
        g2.dispose();
    }
}
