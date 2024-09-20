package game;

import game.Comida.Food;
import game.Comida.Banana;
import game.Comida.Maca;
import music.Musica;
import screen.GameOver;

import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import static utils.ScreenUtils.getScreenHeight;
import static utils.ScreenUtils.getScreenWidth;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 1080; // ou getScreenWidth();
    public static final int SCREEN_HEIGHT = 720; // ou getScreenHeight();
    public static final int UNIT_SIZE = 30;
    public static final int INITIAL_DELAY = 85;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static final int BODY_PARTS = 4; // Constante para o número inicial de partes do corpo
    Timer timer;
    private final JFrame frame;
    private final Snake snake;
    private Food food; // Mudado para usar a classe Comida
    public boolean running = false;
    public int DELAY = INITIAL_DELAY;
    private final Musica musica;

    public GamePanel(JFrame frame) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.frame = frame;

        this.musica = new Musica();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        snake = new Snake(GAME_UNITS, BODY_PARTS);
        food = gerarComida(); // Gerando comida inicial
        this.setBackground(new Color(230, 229, 229));
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.handleKeyPress(e.getKeyCode());
            }
        });
        musica.play("src/music/game.wav");
        startGame();
    }

    private Food gerarComida() { // Método para gerar comida aleatória
        Random random = new Random();
        int tipo = random.nextInt(2); // Para gerar Maca ou Banana
        return switch (tipo) {
            case 0 -> new Maca(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
            case 1 -> new Banana(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
            default -> throw new IllegalStateException("Unexpected value: " + tipo);
        };
    }

    public void startGame() {
        running = true;
        DELAY = 85;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            draw(g);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics g) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (running) {
            food.draw(g);
            snake.draw(g);

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("scoreboard: " + snake.getFoodsEaten(),
                    (SCREEN_WIDTH - metrics.stringWidth("scoreboard: " + snake.getFoodsEaten())) / 2,
                    g.getFont().getSize());
        } else {
            gameOver();
        }
    }

    public void checkFood() { // Verifica se a cobra comeu a comida
        if (snake.getX()[0] == food.getFoodX() && snake.getY()[0] == food.getFoodY()) {
            if (food instanceof Banana) {
                snake.maisVelocidade(); // Aumenta a velocidade se a comida for uma banana
            }
            snake.grow(); // Aumenta o tamanho da cobra
            food = gerarComida(); // Gera nova comida
            DELAY = (int) Math.max((INITIAL_DELAY - 55 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 20);
            timer.setDelay(DELAY);
        }
    }

    public void checkCollisions() { // Verifica se a cobra colidiu com ela mesma ou com as bordas
        if (snake.checkCollision() || snake.checkOutOfBounds(SCREEN_WIDTH, SCREEN_HEIGHT)) {
            timer.stop();
            running = false;
        }
    }

    public void gameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException { // Finaliza o jogo e mostra a tela de game over
        musica.stop();
        frame.dispose();
        new GameOver(snake.getFoodsEaten()).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            snake.move();
            checkFood();
            checkCollisions();
        }
        repaint();
    }
}
