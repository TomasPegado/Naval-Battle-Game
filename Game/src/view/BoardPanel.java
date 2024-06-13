package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {

    private static final int MARGIN = 20; // Margem ao redor do tabuleiro
    private static final int GRID_SIZE = 30; // Tamanho de cada célula
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private List<List<CoordinateView>> board; // Matriz de coordenadas
    private ShipView selectedShip;
    private ShipPositionListener shipPositionListener;
    private ObservableHelper observableHelper; // Instância de Observable

    public BoardPanel() {
        // Define o tamanho preferido com a margem incluída
        setPreferredSize(new Dimension(BOARD_WIDTH * GRID_SIZE + 2 * MARGIN, BOARD_HEIGHT * GRID_SIZE + 2 * MARGIN));
        initializeBoard();
        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable

        addMouseListener(new MouseAdapter() {
            @SuppressWarnings("deprecation")
            @Override
            public void mouseClicked(MouseEvent e) {
                int boardX = (e.getX() - MARGIN + 1) / GRID_SIZE;
                int boardY = (e.getY() - MARGIN + 1) / GRID_SIZE;

                if (boardX >= 0 && boardX < BOARD_WIDTH && boardY >= 0 && boardY < BOARD_HEIGHT) {

                    CoordinateView coord = board.get(boardX).get(boardY);
                    if (selectedShip == null) {

                        if (coord.getShip() != null) {
                            selectedShip = coord.getShip();

                            replaceShip();
                        } else {
                            System.out.println("No Ship Selected");
                        }
                    } else {
                        // Notificar observadores sobre o evento de clique com informações adicionais
                        ShipPlacementEvent event = new ShipPlacementEvent(selectedShip, boardX, boardY);
                        observableHelper.setChanged();
                        observableHelper.notifyObservers(event);
                        placeShip(boardX, boardY);
                    }
                }

            }
        });
    }

    private void initializeBoard() {
        board = new ArrayList<>();
        for (int i = 0; i < BOARD_WIDTH; i++) {
            List<CoordinateView> row = new ArrayList<>();
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                row.add(new CoordinateView(i + 1, j + 1));
            }
            board.add(row);
        }
        System.out.println("Board Inicializado");
    }

    public void setSelectedShip(ShipView ship) {
        this.selectedShip = ship;
    }

    public void setShipPositionListener(ShipPositionListener listener) {
        this.shipPositionListener = listener;
    }

    private void replaceShip() {

        for (CoordinateView coord : selectedShip.coordenadas) {
            coord.setSelected(true);
        }
        repaint();
    }

    public void placeShip(int boardX, int boardY) {
        CoordinateView coord = board.get(boardX).get(boardY);
        if (!selectedShip.coordenadas.isEmpty()) {
            for (CoordinateView previousCoord : selectedShip.coordenadas) {
                previousCoord.setSelected(false);
                previousCoord.setShip(null);
            }
            selectedShip.coordenadas.clear();
        } else if (shipPositionListener != null) {
            shipPositionListener.shipPositioned(selectedShip);
        }
        if (selectedShip instanceof HydroplaneView) {
            coord.setShip(selectedShip);
            coord.setWater(false);
            selectedShip.coordenadas.add(coord);

            coord = board.get(boardX - 1).get(boardY + 1);
            coord.setShip(selectedShip);
            coord.setWater(false);
            selectedShip.coordenadas.add(coord);

            coord = board.get(boardX + 1).get(boardY + 1);
            coord.setShip(selectedShip);
            coord.setWater(false);
            selectedShip.coordenadas.add(coord);
        } else {
            int size = selectedShip.getShipSize();
            for (int i = 0; i < size; i++) {
                coord = board.get(boardX + i).get(boardY);
                coord.setShip(selectedShip);
                coord.setWater(false);
                selectedShip.coordenadas.add(coord);
            }
        }
        System.out.println("Ship added to the board");
        repaint(); // Repinta o tabuleiro para refletir a nova posição

        selectedShip = null; // Deseleciona o navio após posicionar

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Desenha as coordenadas do tabuleiro
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                CoordinateView coord = board.get(i).get(j);
                coord.draw(g2d, GRID_SIZE);
            }
        }

        // Desenha coordenadas com margem
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        for (int i = 1; i <= BOARD_WIDTH; i++) {
            g2d.drawString(Integer.toString(i), MARGIN + i * GRID_SIZE - 15, 15);
        }
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            g2d.drawString(Character.toString((char) ('A' + i)), MARGIN - 20, MARGIN + i * GRID_SIZE + 21);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH * GRID_SIZE + 2 * MARGIN, BOARD_HEIGHT * GRID_SIZE + 2 * MARGIN); // Dimensão
                                                                                                           // do
                                                                                                           // tabuleiro
                                                                                                           // com a
                                                                                                           // margem
    }

    public interface ShipPositionListener {
        void shipPositioned(ShipView ship);
    }

    @SuppressWarnings("deprecation")
    public void addObserver(java.util.Observer observer) {
        observableHelper.addObserver(observer);
    }
}
