package game.food;

import game.Snake;
import java.awt.Color;
import java.awt.Graphics;

public class SuperBanana extends Banana {

    public SuperBanana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();  // Corrigido o erro de sintaxe
    }

    @Override
    public Color setColor() {
        return Color.YELLOW;
    }

    @Override
    public void applyEffect(Snake snake) {
        int aumentoVelocidade = 20;
        snake.setDelay(snake.getDelay() + aumentoVelocidade);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
