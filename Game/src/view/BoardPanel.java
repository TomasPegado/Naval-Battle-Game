package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardPanel extends JPanel {

    private static final int MARGIN = 20; // Margem ao redor do tabuleiro
    private ShipView selectedShip;

    public BoardPanel() {
        // Define o tamanho preferido com a margem incluída
        setPreferredSize(new Dimension(450 + 2 * MARGIN, 450 + 2 * MARGIN));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                placeShip(e.getX(), e.getY());
            }
        });
    }

    public void setSelectedShip(ShipView ship) {
        this.selectedShip = ship;
    }

    private void placeShip(int x, int y) {
        if (selectedShip != null) {
            int gridSize = 30;
            int boardX = (x - MARGIN) / gridSize;
            int boardY = (y - MARGIN) / gridSize;

            // Atualizar posição do navio
            selectedShip.setPanelPositionX(boardX * gridSize + MARGIN);
            selectedShip.setPanelPositionY(boardY * gridSize + MARGIN);
            repaint(); // Repinta o tabuleiro para refletir a nova posição
        }
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
            g2d.drawString(Integer.toString(i), MARGIN + i * gridSize - 15, 15);
        }
        for (int i = 0; i < boardHeight; i++) {
            g2d.drawString(Character.toString((char) ('A' + i)), MARGIN - 20, MARGIN + i * gridSize + 20);
        }

        // Desenha o navio selecionado
        if (selectedShip != null) {
            g2d.setColor(Color.BLUE); // Cor para o navio no tabuleiro
            selectedShip.paintShip(g2d);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450 + 2 * MARGIN, 450 + 2 * MARGIN); // Dimensão do tabuleiro com a margem
    }
}
