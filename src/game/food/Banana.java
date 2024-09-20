package game.food;

import game.Snake;
import java.awt.Color;


public class Banana extends Food {

    public Banana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor(); 
    }

    @Override
    public Color setColor() {
        return Color.YELLOW;
    }

    @Override
    public void applyEffect(Snake snake) {
        snake.moreParts();
        increaseSpeed(snake);
    }
}
