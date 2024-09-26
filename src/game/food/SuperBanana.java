package game.food;

import game.Snake;
import java.awt.Graphics;

public class SuperBanana extends Banana {

    public SuperBanana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();  // Corrigido o erro de sintaxe
    }
    @Override
    public void increaseSpeed(Snake snake){
        int DELAY = (int) Math.max((85 - 35 * (1 - Math.exp(-snake.getFoodsEaten() / 10.0))), 35);
        snake.setDelay(DELAY);
    }
    @Override
    public void applyEffect(Snake snake) {
        snake.moreParts();
        snake.moreParts();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
