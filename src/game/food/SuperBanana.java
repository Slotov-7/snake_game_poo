package game.food;

import game.Snake;
import java.awt.Graphics;

public class SuperBanana extends Banana {

    public SuperBanana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = getColor();  // Corrigido o erro de sintaxe
    }
    @Override
    public void increaseSpeed(Snake snake){//aumenta a velocidade da cobra
        int DELAY = (int) Math.max((85 - 30 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 35);
        snake.setDelay(DELAY);
    }
    @Override
    public void applyEffect(Snake snake) {//aplica o efeito da super banana na cobra
        snake.grow(2);
        increaseSpeed(snake);
    }

    @Override
    public void draw(Graphics g) {//desenha a super banana
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
