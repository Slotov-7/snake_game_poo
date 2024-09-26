package game.food;

import game.Snake;
import java.awt.Color;


public class Banana extends Food {

    public Banana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = getColor();
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    @Override
    public void applyEffect(Snake snake) {//aplica o efeito da banana na cobra
        snake.grow(1);
        increaseSpeed(snake);
    }
}
