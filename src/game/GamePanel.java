package game;

import game.food.*;
import music.Music;
import screen.GameOver;
import utils.*;

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

import static utils.ScreenUtils.getScreenHeight;
import static utils.ScreenUtils.getScreenWidth;

public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH = 1080; // ou usa getScreenWidth();
    public static final int SCREEN_HEIGHT = 720; // ou usa getScreenHeight();
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
    private final Music music;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.music = new Music();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        snake = new Snake(GAME_UNITS, BODY_PARTS);
        food = generateFood();
        this.setBackground(new Color(230, 229, 229));
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.handleKeyPress(e.getKeyCode());
            }
        });
        try {
            music.play("src/music/game.wav");
            startGame();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new GameException("Error during the game initialization: " + e.getMessage());
        }
    }

    private Food generateFood() {
        Random random = new Random();
        int type = random.nextInt(8); // Atualizado para considerar 6 tipos
    
        return switch (type) {
            case 0, 1, 2 -> new Apple(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 3 opções para Maçã
            case 3, 4, 5 -> new Banana(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 3 opções para Banana
            case  6 -> new SuperApple(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 1 opção para SuperMaçã
            case  7 -> new SuperBanana(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE); // 1 opção para SuperBanana
            default -> throw new GameException("Unexpected value: " + type);
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
        draw(g);
    }

    public void draw(Graphics g) {
        try {
            Font pixelFont = TextFont.getPixelFont(32f);

            if (running) {
                // Desenha a comida
                food.draw(g);

                // Desenha a cobra
                snake.draw(g);

                // Exibe o placar
                g.setColor(Color.BLACK);
                g.setFont(pixelFont);
                FontMetrics metrics = getFontMetrics(g.getFont());
                String scoreText = "Scoreboard: " + snake.getFoodsEaten();
                g.drawString(scoreText,
                        (SCREEN_WIDTH - metrics.stringWidth(scoreText)) / 2,
                        g.getFont().getSize() + 15);
            } else {
                gameOver(); // Se o jogo terminou, chama o metodo de fim de jogo
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Lança uma GameException para tratar o erro
            throw new GameException("Error during the game drawing: " + e.getMessage());
        }
    }


    public void checkFood() {
        if (snake.getX()[0] == food.getFoodX() && snake.getY()[0] == food.getFoodY()) {

            if (food instanceof SuperApple) {
                // Aplicar o efeito da SuperApple
                System.out.println("A cobra comeu uma super maçã!");
            } else if (food instanceof SuperBanana) {
                // Aplicar o efeito da SuperBanana
                System.out.println("A cobra comeu uma super banana!");
            } else if (food instanceof Apple) {
                // Aplicar o efeito da Apple
                System.out.println("A cobra comeu uma maçã!");
            } else if (food instanceof Banana) {
                // Aplicar o efeito da Banana
                System.out.println("A cobra comeu uma banana!");
            }
            snake.updateFoodsEaten(food);
            food.applyEffect(snake);
            snake.grow();
            food = generateFood();
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
        music.stop();
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
