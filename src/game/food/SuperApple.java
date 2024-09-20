package game.food;

import game.Snake;
import java.awt.Graphics;

public class SuperApple extends Apple {

    public SuperApple(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();
    }
    @Override
    public void increaseSpeed(Snake snake){
        int DELAY = (int) Math.max((85 - 45 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 35);
        snake.setDelay(DELAY);
    }
    @Override
    public void applyEffect(Snake snake) {
        super.applyEffect(snake);
        snake.moreParts();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
