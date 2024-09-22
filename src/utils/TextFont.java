package utils;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public class TextFont {
    public static Font getPixelFont(float size) {
        try {
            // Carrega a fonte pixelada do arquivo
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/utils/PressStart2P.ttf"));

            // Registra a fonte no ambiente gráfico
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            // Retorna a fonte com o tamanho especificado
            return pixelFont.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a fonte.");
        }
        return null; // Retorna null se ocorrer um erro
    }
}

