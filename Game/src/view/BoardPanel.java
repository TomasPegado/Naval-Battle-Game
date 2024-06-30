package view;

import javax.swing.*;

import model.GameBoard;
import model.PositionPair;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class BoardPanel extends JPanel {

    protected static final int MARGIN = 25; // Margem ao redor do tabuleiro
    protected static final int GRID_SIZE = 30; // Tamanho de cada célula
    private static final int BOARD_WIDTH = 15;
    private static final int BOARD_HEIGHT = 15;
    private List<List<CoordinateView>> board; // Matriz de coordenadas
    private ShipView selectedShip;
    private ShipPositionListener shipPositionListener;
    private ObservableHelper observableHelper; // Instância de Observable
    protected List<ShipView> shipsList = new ArrayList<>();
    private int currentPositionX;
    private int currentPositionY;
    private boolean is_PositionBoard;

    public BoardPanel() {
        // Define o tamanho preferido com a margem incluída
        setPreferredSize(new Dimension(BOARD_WIDTH * GRID_SIZE + 2 * MARGIN, BOARD_HEIGHT * GRID_SIZE + 2 * MARGIN));
        initializeBoard();
        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int boardX = (e.getX() - MARGIN + 1) / GRID_SIZE;
                int boardY = (e.getY() - MARGIN + 1) / GRID_SIZE;

                if (boardX >= 0 && boardX < BOARD_WIDTH && boardY >= 0 && boardY < BOARD_HEIGHT) {
                    CoordinateView coord = board.get(boardX).get(boardY);

                    if (is_PositionBoard) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            if (selectedShip != null) {
                                for (CoordinateView c : selectedShip.coordenadas) {
                                    System.out.println("Selected Ship coordenadas: " + c.getX() + ", " + c.getY());
                                }
                                Coordinates turnCoords = new Coordinates(selectedShip.coordenadas.get(0).getX() - 1,
                                        selectedShip.coordenadas.get(0).getY() - 1);

                                selectedShip.turnCoordinates(turnCoords);

                                setCurrentPositionX(selectedShip.coordenadas.get(0).getX() - 1);
                                setCurrentPositionY(selectedShip.coordenadas.get(0).getY() - 1);
                                ShipPlacementEvent event = new ShipPlacementEvent(selectedShip, turnCoords.x,
                                        turnCoords.y,
                                        (selectedShip.getOrientacao() + 1) % 4);

                                observableHelper.setChanged();
                                observableHelper.notifyObservers(event);

                            }
                        } else if (selectedShip == null) {

                            if (coord.getShip() != null) {
                                selectedShip = coord.getShip();
                                setCurrentPositionX(boardX);
                                setCurrentPositionY(boardY);
                                replaceShip();
                            } else {
                                System.out.println("No Ship Selected on the Board");
                            }
                        } else {
                            // Notificar observadores sobre o evento de clique com informações adicionais
                            System.out.println("Board Panel antes do ShipPlacement Event: boardX = " + boardX
                                    + " boardY = " + boardY);
                            shipPlacementEventNotifier(boardX, boardY);
                            // ShipPlacementEvent event = new ShipPlacementEvent(selectedShip, boardX,
                            // boardY,
                            // selectedShip.getOrientacao());
                            // if (!selectedShip.coordenadas.isEmpty()) {
                            // setCurrentPositionX(selectedShip.coordenadas.get(0).getX() - 1);
                            // setCurrentPositionY(selectedShip.coordenadas.get(0).getY() - 1);
                            // }

                            // observableHelper.setChanged();
                            // observableHelper.notifyObservers(event);

                        }
                    } else {

                        ShotEvent event = new ShotEvent(boardX, boardY);
                        observableHelper.setChanged();
                        observableHelper.notifyObservers(event);
                    }

                }

            }
        });

    }

    protected void shipPlacementEventNotifier(int boardX, int boardY) {

        ShipPlacementEvent event = new ShipPlacementEvent(selectedShip, boardX, boardY,
                selectedShip.getOrientacao());
        if (!selectedShip.coordenadas.isEmpty()) {
            setCurrentPositionX(selectedShip.coordenadas.get(0).getX() - 1);
            setCurrentPositionY(selectedShip.coordenadas.get(0).getY() - 1);
        }

        observableHelper.setChanged();
        observableHelper.notifyObservers(event);

    }

    protected void performEscAction() {
        // Código específico para o BoardPanel quando ESC é pressionado
        System.out.println("ESC pressed in BoardPanel");
        if (is_PositionBoard) {

            if (selectedShip != null) {
                if (!selectedShip.isInvalidPosition()) {
                    selectedShip.deselectShip();
                    selectedShip = null;
                    repaint();
                } else { // Retorna navio para o weapons panel se estiver em uma posição invalida
                    if (shipPositionListener != null) {
                        setCurrentPositionX(selectedShip.coordenadas.get(0).getX() - 1);
                        setCurrentPositionY(selectedShip.coordenadas.get(0).getY() - 1);
                        selectedShip.clearCoordinates();
                        shipsList.remove(selectedShip);
                        shipPositionListener.shipReturned(selectedShip);

                        ShipPlacementEvent event = new ShipPlacementEvent(selectedShip, true);
                        observableHelper.setChanged();
                        observableHelper.notifyObservers(event);

                        selectedShip = null;
                        repaint();
                    }
                }

            }
        }

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

    public ShipView getSelectedShip() {
        return selectedShip;
    }

    public void setShipPositionListener(ShipPositionListener listener) {
        this.shipPositionListener = listener;
    }

    private void replaceShip() {

        selectedShip.selectShip();
        repaint();
    }

    protected void placeShip(int boardX, int boardY, int orientacao, boolean invalidPosition) {

        if (!selectedShip.clearCoordinates()) {
            if (shipPositionListener != null) {
                shipPositionListener.shipPositioned(selectedShip);
            }
        }

        boolean success = selectedShip.placeShip(this, boardX, boardY, orientacao);

        if (success) {

            selectedShip.setInvalidPosition(invalidPosition);
            if (shipsList.contains(selectedShip)) {
                System.out
                        .println(selectedShip + "Changed Position to (" + (boardX + 1) + ", " + (char) (boardY + 65)
                                + ")");
            } else {
                shipsList.add(selectedShip);
                System.out.println("Ship added to the board");
            }
            selectedShip.setOrientacao(orientacao);

        } else {
            System.out.println(
                    "Failed to Place Ship in the given Coordinates " + (boardX + 1) + ", " + (char) (boardY + 65));
        }

        repaint(); // Repinta o tabuleiro para refletir a nova posição

        // selectedShip = null; // Deseleciona o navio após posicionar

    }

    public void updateBoard(GameBoard gameBoard) {
        // 1. Get the data from the GameBoard
        PositionPair[][] gameBoardData = gameBoard.GetGameBoard();

        // 2. Update the CoordinateView objects in your board
        for (int i = 0; i < BOARD_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                CoordinateView coordView = board.get(i).get(j);
                PositionPair gameBoardCoord = gameBoardData[i][j];

                // Update the CoordinateView based on the GameBoard data
                if (gameBoardCoord.getShip() != null) {
                    // If there's a ship, create a ShipView and set it on the CoordinateView
                    ShipView shipView = ShipFactoryView.createShipView(gameBoardCoord.getShip().GetSize());
                    coordView.setShip(shipView); // Set the ShipView on the CoordinateView
                } else if (gameBoardCoord.getHit()) {
                    // If it's a hit, mark the CoordinateView as hit
                    coordView.setWater(true);
                }
            }
        }

        // 3. Repaint the BoardPanel
        repaint();
    }

    protected void firstShotHit(int boardX, int boardY, int size) {
        CoordinateView coord = board.get(boardX).get(boardY);
        ShipView ship = ShipFactoryView.createShipView(size);

        coord.setShip(ship);
        if (size == 1) {
            ship.setSunk(true);
        }
        repaint();
        System.out.println("BoardPanel painted shot at " + boardX + ", " + boardY);

    }

    protected void shotHitAgain(int boardX, int boardY, int previousHitCoordX, int previousHitCoordY, boolean sunk) {

        CoordinateView previousHitCoord = board.get(previousHitCoordX).get(previousHitCoordY);
        ShipView ship = previousHitCoord.getShip();
        CoordinateView coord = board.get(boardX).get(boardY);
        ship.coordenadas.add(coord);
        coord.setShip(ship);

        if (sunk) {
            ship.setSunk(sunk);
        }
        repaint();
        System.out.println("BoardPanel painted new hit at " + boardX + ", " + boardY);

    }

    protected void shotWater(int boardX, int boardY) {
        CoordinateView coord = board.get(boardX).get(boardY);
        coord.setWater(true);
        repaint();
        System.out.println("BoardPanel painted water hit at " + boardX + ", " + boardY);

    }

    public List<ShipView> getShipsList() {
        return shipsList;
    }

    public void setCurrentPositionX(int currentPositionX) {
        this.currentPositionX = currentPositionX;
    }

    public void setCurrentPositionY(int currentPositionY) {
        this.currentPositionY = currentPositionY;
    }

    public int getCurrentPositionX() {
        return currentPositionX;
    }

    public int getCurrentPositionY() {
        return currentPositionY;
    }

    public void setIs_PositionBoard(boolean is_PositionBoard) {
        this.is_PositionBoard = is_PositionBoard;
    }

    public boolean is_PositionBoard() {
        return is_PositionBoard;
    }

    protected List<List<CoordinateView>> getBoard() {
        return board;
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

        void shipReturned(ShipView ship);
    }

    public void addObserver(java.util.Observer observer) {
        observableHelper.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        observableHelper.deleteObserver(observer);
    }

    protected class Coordinates {
        protected int x;
        protected int y;

        protected Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
