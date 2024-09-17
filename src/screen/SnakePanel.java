//package screen;
//
//import javax.swing.*;
//import java.awt.*;
//
//// Classe para desenhar a cobra e a maçã
//public class SnakePanel extends JPanel {
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        this.setBackground(new Color(50, 50, 50));
//
//        int width = getWidth();
//        int height = getHeight();
//
//        // Tamanho de cada bloco
//        int blockSize = 20;
//
//        // Calcula a posição centralizada da cobra
//        int snakeLength = 7; // Tamanho da cobra (número de blocos)
//        int snakeWidth = snakeLength * blockSize;
//        int startX = (width - snakeWidth) / 2;
//        int startY = height / 4;
//
//        // Desenho da cobra
//        g.setColor(new Color(0, 255, 0));
//        int[][] snake = { {0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}, {4, 1}, {4, 2} };  // Posições da cobra
//
//        for (int[] block : snake) {
//            g.fillRect(startX + block[0] * blockSize, startY + block[1] * blockSize, blockSize, blockSize);
//        }
//
//        // Desenho da maçã
//        g.setColor(new Color(255, 0, 0));
//        g.fillRect(startX + 6 * blockSize, startY, blockSize, blockSize);  // Posição da maçã à direita da cobra
//    }
//}
