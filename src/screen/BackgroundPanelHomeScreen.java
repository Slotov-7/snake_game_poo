package screen;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BackgroundPanelHomeScreen extends JPanel {
    private final Image backgroundImage;

    public BackgroundPanelHomeScreen() {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("assets/snakeBG.jpg")));
        if (icon.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.out.println("Imagem não encontrada: assets/snakeBG.jpg");
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
