package game.Comida;

import game.Snake;
import java.awt.Color;

public class Banana extends Food {

    public Banana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor(); // Define a cor corretamente
    }

    @Override
    public Color setColor() {
        return Color.YELLOW;
    }

    @Override
    public void aplicarEfeito(Snake cobra) {
        cobra.maisVelocidade(); // Apenas aumenta a velocidade
        // Remova a linha abaixo
        // cobra.grow(); 
    }
}
