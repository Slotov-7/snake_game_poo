package game.food;

import game.Snake;
import java.awt.Graphics;

public class SuperApple extends Apple {

    public SuperApple(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = getColor();
    }
    @Override
    public void increaseSpeed(Snake snake){//aumenta a velocidade da cobra
        int DELAY = (int) Math.max((85 - 40 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 35);
        snake.setDelay(DELAY);
    }
    @Override
    public void applyEffect(Snake snake) {//aplica o efeito da super maçã na cobra
        super.applyEffect(snake);
        snake.grow(2);
    }

    @Override
    public void draw(Graphics g) {//desenha a super maçã
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
