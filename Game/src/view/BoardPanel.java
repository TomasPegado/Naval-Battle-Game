package view;

import javax.swing.*;

import model.ShipType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BoardPanel extends JPanel {

    private final int gridSize = 40; // Tamanho de cada célula
    private final int margin = 20; // Margem ao redor do tabuleiro
    private final int boardWidth = 15; // Largura do tabuleiro
    private final int boardHeight = 15; // Altura do tabuleiro
    private Map<Point, ShipType> shipPositions; // Mapa para armazenar as posições dos navios

    public BoardPanel() {
        this.shipPositions = new HashMap<>();
        this.setPreferredSize(new Dimension(gridSize * boardWidth + 2 * margin, gridSize * boardHeight + 2 * margin));
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getMargin() {
        return margin;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void addShip(int x, int y, ShipType shipType) {
        shipPositions.put(new Point(x, y), shipType);
        repaint(); // Redesenha o painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha o tabuleiro
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardHeight; j++) {
                g.drawRect(margin + i * gridSize, margin + j * gridSize, gridSize, gridSize);
            }
        }

        // Desenha os navios
        for (Map.Entry<Point, ShipType> entry : shipPositions.entrySet()) {
            Point point = entry.getKey();
            ShipType shipType = entry.getValue();
            int x = point.x;
            int y = point.y;

            // Aqui, você pode customizar a aparência dos navios
            g.setColor(Color.GRAY); // Cor do navio
            g.fillRect(margin + x * gridSize, margin + y * gridSize, gridSize, gridSize);
        }
    }
}
