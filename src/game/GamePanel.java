package game;

import music.Musica;
import screen.GameOver;
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

public class GamePanel extends JPanel implements ActionListener  {

    public static final int SCREEN_WIDTH =  getScreenWidth();
    public static final int SCREEN_HEIGHT = (int) (getScreenHeight() * 0.90);
    public static final int UNIT_SIZE = 30;
    public static final int INITIAL_DELAY = 85;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;;
    public static final int bodyParts = 4;
    Timer timer;
    private final JFrame frame;
    private final Snake snake;
    private final Food food;
    public boolean running = false;
    public int DELAY = INITIAL_DELAY;
    private final Musica musica;

    public GamePanel(JFrame frame) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.frame = frame;

        this.musica = new Musica();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        snake = new Snake(GAME_UNITS, bodyParts);
        food = new Food(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
        this.setBackground(Color.white);
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
            snake.grow();
            food.generateFood();
            DELAY = (int) Math.max((INITIAL_DELAY - 55 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 20);
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
