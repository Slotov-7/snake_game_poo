package screen;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanelGameOverScreen extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanelGameOverScreen() {
        // Carrega a imagem de fundo
        backgroundImage = new ImageIcon("snake_game_poo-master/src/assets/snakeGameOver.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
