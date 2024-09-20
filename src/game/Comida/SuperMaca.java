package game.comida;

import game.Snake;
import java.awt.Color;
import java.awt.Graphics;

public class SuperMaca extends Maca {

    public SuperMaca(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor();
    }

    @Override
    public Color setColor() {
        return Color.RED;
    }

    @Override
    public void aplicarEfeito(Snake snake) {
        snake.maisPartes();
        snake.maisPartes();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
