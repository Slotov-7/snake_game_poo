package game.comida;

import game.Snake;
import java.awt.Color;
import java.awt.Graphics;

public class SuperBanana extends Banana {

    public SuperBanana(int screenWidth, int screenHeight, int unitSize) {
        super(screenWidth, screenHeight, unitSize);
        this.foodColor = setColor(); 

    @Override
    public Color setColor() {
        return Color.YELLOW; 
    }

    @Override
    public void aplicarEfeito(Snake cobra) {
        int aumentoVelocidade = 20;
        cobra.setDelay(cobra.getDelay() + aumentoVelocidade);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(foodColor);
        g.fillRect(foodX, foodY, unitSize, unitSize);
    }
}
