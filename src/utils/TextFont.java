package utils;
import java.awt.*;
import java.io.InputStream;
import java.io.IOException;

public class TextFont {
    public static Font getPixelFont(float size) {
        try {
            // Carrega a fonte pixelada do arquivo usando o ClassLoader
            InputStream fontStream = TextFont.class.getClassLoader().getResourceAsStream("utils/PressStart2P.ttf");
            if (fontStream == null) {
                System.out.println("Fonte não encontrada.");
                return null;
            }

            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            // Registra a fonte no ambiente gráfico
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);

            // Retorna a fonte com o tamanho especificado
            return pixelFont.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            System.out.println("Erro ao carregar a fonte.");
        }
        return null; // Retorna null se ocorrer um erro
    }
}


