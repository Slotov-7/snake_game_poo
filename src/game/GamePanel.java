package game;

import screen.GameOver;
import screen.HomeScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import static utils.ScreenUtils.getScreenHeight;
import static utils.ScreenUtils.getScreenWidth;


public class GamePanel extends JPanel implements ActionListener {

    public static final int SCREEN_WIDTH =  getScreenWidth();
    public static final int SCREEN_HEIGHT = (int) (getScreenHeight() * 0.90);
    public static final int UNIT_SIZE = 30;
    public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    public static int DELAY = 85;
    public final int[] x = new int[GAME_UNITS];
    public final int[] y = new int[GAME_UNITS];
    public int bodyParts = 6;
    public int foodsEaten;
    public int foodX;
    public int foodY;
    private Color foodColor;
    public char direction = 'D';
    public boolean running = false;
    Timer timer;
    Random random;
    private JFrame frame;
    public GamePanel(JFrame frame) {
        this.frame = frame;
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new MyKayAdapter());
        startGame();
    }

    public void startGame() {
        generateFood();
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
        if(running) {
            /*
            for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }

             */
            g.setColor(foodColor);
            g.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Placar: "+foodsEaten, (SCREEN_WIDTH - metrics.stringWidth("Placar: "+foodsEaten))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }

    private Color randomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    public void generateFood() {
        foodX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        foodY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        foodColor = randomColor();
    }
    public void move() {
        for (int i = bodyParts; i > 0 ; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkFood() {
        if((x[0] == foodX) && (y[0] == foodY)){
            bodyParts++;
            foodsEaten++;
            generateFood();
            DELAY = (int) Math.max((85 - 55 * (1 - Math.exp(-foodsEaten / 10.0))),20 );
            timer.setDelay(DELAY);
        }
    }

    public void checkCollisions() {
        //checa se a cabeça colide com o corpo
        for(int i = bodyParts; i>0;i--){
            if((x[0]) == x[i] && (y[0]) == y[i]){
                running = false;
            }
        }
        //checa se a cabeça toca na borda esquerda
        if (x[0] < 0) {
            running = false;
        }
         //checa se a cabeça toca na borda direita
         if (x[0] > SCREEN_WIDTH) {
            running = false;
        }
        //checa se a cabeça toca na borda de cima
        if (y[0] < 0) {
            running = false;
        }
         //checa se a cabeça toca na borda de baixo
         if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }
         
        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
        frame.dispose();
        new GameOver(foodsEaten).setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(running){
            move();
            checkFood();
            checkCollisions();

        }
        repaint();
    }
    public class MyKayAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                default:
                    break;
            }
        }
    }


}
