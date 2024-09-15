package game;

import java.awt.*;
import java.util.Random;

public class Comida {
    private int foodX;
    private int foodY;
    private Color foodColor;
    private final int screenWidth;
    private final int screenHeight;
    private final int unitSize;
    private final Random random;

    public Comida(int screenWidth, int screenHeight, int unitSize) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        this.random = new Random();
        generateFood();
    }

    public void generateFood() {
        foodX = random.nextInt(screenWidth / unitSize) * unitSize;
        foodY = random.nextInt(screenHeight / unitSize) * unitSize;
        foodColor = randomColor();
    }

    private Color randomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillOval(foodX, foodY, unitSize, unitSize);
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }

    public Color getFoodColor() {
        return foodColor;
    }
}
