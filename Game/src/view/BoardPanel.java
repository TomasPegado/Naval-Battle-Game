package view;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private static final int MARGIN = 20; // Margem ao redor do tabuleiro

    public BoardPanel() {
        // Define o tamanho preferido com a margem incluída
        setPreferredSize(new Dimension(450 + 2 * MARGIN, 450 + 2 * MARGIN));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int gridSize = 30;
        int boardWidth = 15;
        int boardHeight = 15;

        // Desenha linhas horizontais e verticais com margem
        for (int i = 0; i <= boardWidth; i++) {
            g2d.drawLine(MARGIN + i * gridSize, MARGIN, MARGIN + i * gridSize, MARGIN + boardHeight * gridSize);
        }
        for (int i = 0; i <= boardHeight; i++) {
            g2d.drawLine(MARGIN, MARGIN + i * gridSize, MARGIN + boardWidth * gridSize, MARGIN + i * gridSize);
        }

        // Desenha coordenadas com margem
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 1; i <= boardWidth; i++) {
            g2d.drawString(Integer.toString(i), MARGIN + i * gridSize - 15, MARGIN + boardHeight * gridSize + 20);
        }
        for (int i = 0; i < boardHeight; i++) {
            g2d.drawString(Character.toString((char) ('A' + i)), MARGIN - 20, MARGIN + i * gridSize + 20);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450 + 2 * MARGIN, 450 + 2 * MARGIN); // Dimensão do tabuleiro com a margem
    }
}
