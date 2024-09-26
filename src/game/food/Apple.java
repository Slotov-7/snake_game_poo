package game.food;

import game.Snake;
import java.awt.Color;

public class Apple extends Food {

    public Apple(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = getColor();
    }
    public Color getColor() {
        return Color.RED;
    }
    @Override
    public void applyEffect(Snake snake) {//aplica o efeito da maçã na cobra
        snake.grow(1);
        increaseSpeed(snake);
    }
}
