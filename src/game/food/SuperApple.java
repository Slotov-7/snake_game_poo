package game.food;

import game.Snake;
import java.awt.Color;
import java.awt.Graphics;

public class SuperApple extends Apple {

    public SuperApple(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();
    }

    @Override
    public Color setColor() {
        return Color.RED;
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.maisPartes();
        snake.maisPartes();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
