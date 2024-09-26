package screen;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanelGameOverScreen extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanelGameOverScreen() {
        // Carrega a imagem de fundo
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/snakeGameOver.jpg")));
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Imagem não encontrada: assets/snakeGameOver.jpg");
        }
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a imagem de fundo
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
