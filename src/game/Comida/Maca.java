package game.Comida;

import game.Snake;
import java.awt.Color;

public class Maca extends Food {

    public Maca(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor(); // Define a cor corretamente
    }

    @Override
    public Color setColor() {
        return Color.RED;
    }

    @Override
    public void aplicarEfeito(Snake cobra) {
        cobra.maisPartes();
    }
}