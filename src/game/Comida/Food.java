package game.Comida; // Corrigido para minúsculas para seguir convenções de pacotes

import java.awt.*;
import java.util.Random;

public abstract class Food { // Tornada abstrata para ser estendida
    protected int foodX;
    protected int foodY;
    protected Color foodColor;
    protected final int screenWidth;
    protected final int screenHeight;
    protected final int unitSize;
    protected final Random random;

    public Food(int screenWidth, int screenHeight, int unitSize) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        this.random = new Random();
        generateFood();
    }

    public void generateFood() { // Gera a comida em uma posição específica
        foodX = random.nextInt(screenWidth / unitSize) * unitSize;
        foodY = random.nextInt(screenHeight / unitSize) * unitSize;
        foodColor = setColor(); // Agora chamamos o método para definir a cor
    }

    protected abstract Color setColor(); // Método abstrato para definir a cor

    public void draw(Graphics g) { // Desenha a comida
        g.setColor(foodColor);
        g.fillOval(foodX, foodY, unitSize, unitSize);
    }

    public int getFoodX() {
        return foodX;
    }

    public int getFoodY() {
        return foodY;
    }
}
