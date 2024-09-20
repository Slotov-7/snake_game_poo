package game;

import music.Musica;
import screen.GameOver;
import game.food.Food;
import game.food.Banana;
import game.food.SuperBanana;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 1080; 
    public static final int SCREEN_HEIGHT = 720; 
    public static final int UNIT_SIZE = 30;
    public static final int INITIAL_DELAY = 85;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static final int BODY_PARTS = 4; 
    Timer timer;
    private final JFrame frame;
    private final Snake snake;
    private Food food;
    public boolean running = false;
    private int DELAY = INITIAL_DELAY;
    private final Musica musica;

    public GamePanel(JFrame frame) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.frame = frame;
        this.musica = new Musica();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        snake = new Snake(GAME_UNITS, BODY_PARTS);
        food = gerarComida(); 
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

    private Food gerarComida() {
        Random random = new Random();
        int tipo = random.nextInt(6); // Atualizado para considerar 6 tipos
    
        return switch (tipo) {
            case 0, 1, 2 -> new game.food.Apple(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 3 opções para Maçã
            case 3, 4, 5 -> new Banana(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 3 opções para Banana
            case  6 -> new game.food.SuperApple(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 1 opção para SuperMaçã
            case  7 -> new SuperBanana(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 1 opção para SuperBanana
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

    public void checkFood() {
        if (snake.getX()[0] == food.getFoodX() && snake.getY()[0] == food.getFoodY()) {
            food.applyEffect(snake);
            snake.grow();
            food = gerarComida();
            DELAY = snake.getDelay();
            timer.setDelay(DELAY);
        }
    }

    public void checkCollisions() { 
        if (snake.checkCollision() || snake.checkOutOfBounds(SCREEN_WIDTH, SCREEN_HEIGHT)) {
            timer.stop();
            running = false;
        }
    }

    public void gameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException { 
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
