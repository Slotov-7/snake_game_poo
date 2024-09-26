package game;

import game.food.*;

import java.awt.*;
import java.awt.event.KeyEvent;


public class Snake {
    private static final int UNIT_SIZE = 30;
    private final int[] x;
    private final int[] y;
    private int bodyParts;
    private int foodsEaten;
    private char direction;
    private static int DELAY = 85;


    public Snake(int gameUnits, int initialBodyParts) {// Inicializa a cobra
        x = new int[gameUnits];
        y = new int[gameUnits];
        bodyParts = initialBodyParts;
        foodsEaten = 0;
        direction = 'D';
        DELAY = 85;
    }

    public void move() {// Move a cobra
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        try {
            switch (direction) {
                case 'U' -> y[0] = y[0] - UNIT_SIZE;
                case 'D' -> y[0] = y[0] + UNIT_SIZE;
                case 'L' -> x[0] = x[0] - UNIT_SIZE;
                case 'R' -> x[0] = x[0] + UNIT_SIZE;
            }
        } catch (GameException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean checkCollision() {// Verifica se a cobra colidiu com ela mesma
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkOutOfBounds(int screenWidth, int screenHeight) {// Verifica se a cobra saiu da tela
        return (x[0] < 0 || x[0] >= screenWidth || y[0] < 0 || y[0] >= screenHeight);
    }

    public void grow(int numberOfParts) {// Adiciona um novo segmento na mesma posição que o último segmento
        for (int i = 0; i < numberOfParts; i++) {

            x[bodyParts] = x[bodyParts - 1];
            y[bodyParts] = y[bodyParts - 1];

            bodyParts++;
        }

    }
    public void updateFoodsEaten(Food food) {// Atualiza a quantidade de alimentos comidos
        if (food instanceof SuperApple || food instanceof SuperBanana) {
            foodsEaten += 2;// Super alimentos valem por 2
        } else if(food instanceof Apple || food instanceof Banana) {
            foodsEaten++;// Alimentos normais valem por 1
        }
    }

    public int getFoodsEaten() {// Retorna a quantidade de alimentos comidos
        return foodsEaten;
    }

    public void setDirection(char direction) {// Define a direção da cobra
        if (this.direction == 'U' && direction != 'D' ||
                this.direction == 'D' && direction != 'U' ||
                this.direction == 'L' && direction != 'R' ||
                this.direction == 'R' && direction != 'L') {
            this.direction = direction;
        }
    }

    public void handleKeyPress(int keyCode) {// Define a direção da cobra com base na tecla pressionada
        switch (keyCode) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> setDirection('L');
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> setDirection('R');
            case KeyEvent.VK_UP, KeyEvent.VK_W -> setDirection('U');
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> setDirection('D');
        }
    }


    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getDelay() {
        return DELAY;
    }

    public void setDelay(int delay) {
        DELAY = delay;
    }

    public void draw(Graphics g) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
    }
}

