package utils;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ScreenUtils {

    public static int getScreenHeight() {//retorna a altura da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        return (int) (screenSize.getHeight() - screenInsets.top - screenInsets.bottom);
    }

    public static int getScreenWidth() {//retorna a largura da tela
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        return (int) (screenSize.getWidth() - (screenInsets.left + screenInsets.right));
    }
}
