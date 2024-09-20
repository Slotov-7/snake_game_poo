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
        int aumentoVelocidade = 10; 
        snake.setDelay(snake.getDelay() + aumentoVelocidade);
    }


    public int getAumentoVelocidade() {
        return 10;
    }
}
