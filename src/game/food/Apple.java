package game.food;

import game.Snake;
import java.awt.Color;

public class Apple extends Food {

    public Apple(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();
    }
    public Color setColor() {
        return Color.RED;
    }
    @Override
    public void applyEffect(Snake snake) {
        snake.moreParts();
        increaseSpeed(snake);
    }
}
