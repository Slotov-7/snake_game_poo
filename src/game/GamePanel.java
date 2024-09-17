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
    public static final int bodyParts = 6;
    Timer timer;
    private final JFrame frame;
    private final Cobra cobra;
    private final Comida comida;
    public boolean running = false;
    public int DELAY = INITIAL_DELAY;

    public GamePanel(JFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        cobra = new Cobra(GAME_UNITS, bodyParts);
        comida = new Comida(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE);
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                cobra.handleKeyPress(e.getKeyCode());
            }
        });
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
            comida.draw(g);
            cobra.draw(g);

            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Placar: " + cobra.getFoodsEaten(),
                    (SCREEN_WIDTH - metrics.stringWidth("Placar: " + cobra.getFoodsEaten())) / 2,
                    g.getFont().getSize());
        } else {
            gameOver();
        }
    }

    public void checkFood() {
        if (cobra.getX()[0] == comida.getFoodX() && cobra.getY()[0] == comida.getFoodY()) {
            cobra.grow();
            comida.generateFood();
            DELAY = (int) Math.max((INITIAL_DELAY - 55 * (1 - Math.exp(-cobra.getFoodsEaten() / 10.0))), 20);
            timer.setDelay(DELAY);
        }
    }

    public void checkCollisions() {
        if (cobra.checkCollision() || cobra.checkOutOfBounds(SCREEN_WIDTH, SCREEN_HEIGHT)) {
            timer.stop();
            running = false;
        }
    }

    public void gameOver() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        frame.dispose();
        new GameOver(cobra.getFoodsEaten()).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            cobra.move();
            checkFood();
            checkCollisions();
        }
        repaint();
    }
}
