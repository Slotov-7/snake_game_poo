package game.comida;

import java.awt.*;
import java.util.Random;
import game.Snake;

public abstract class Food { 
    protected int foodX;
    protected int foodY;
    protected Color foodColor;
    protected final int screenWidth;
    protected final int screenHeight;
    protected final int unitSize;
    protected final Random random;

    public Food(int screenWidth, int screenHeight, int unitSize) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        this.random = new Random();
        generateFood();
    }

    public void generateFood() { 
        foodX = random.nextInt(screenWidth / unitSize) * unitSize;
        foodY = random.nextInt(screenHeight / unitSize) * unitSize;
  
    }

    protected abstract Color setColor(); 

    public abstract void aplicarEfeito(Snake snake);

    public void draw(Graphics g) { 
        g.setColor(setColor());
        g.fillOval(foodX, foodY, unitSize, unitSize); 
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}
