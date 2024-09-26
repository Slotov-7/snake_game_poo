package game.food;
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

    public void generateFood() {//gera a comida em uma posição aleatória
        foodX = random.nextInt(screenWidth / unitSize) * unitSize;
        foodY = random.nextInt(screenHeight / unitSize) * unitSize;
    }

    public abstract Color getColor();

    public abstract void applyEffect(Snake snake);//aplica o efeito da comida na cobra

    public void increaseSpeed(Snake snake){//aumenta a velocidade da cobra
        int DELAY = (int) Math.max((85 - 55 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 35);
        snake.setDelay(DELAY);
    }
    public void draw(Graphics g) {//desenha a comida
        g.setColor(getColor());
        g.fillOval(foodX, foodY, unitSize, unitSize); 
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}
