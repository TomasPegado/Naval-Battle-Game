package view;

import java.awt.*;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;

public abstract class ShipView extends JPanel {

    protected int panelPositionX;
    protected int panelPositionY;
    protected int shipSize;
    protected String color;
    protected boolean selected = false;
    protected List<CoordinateView> coordenadas;
    protected boolean sunk = false;
    protected int orientacao = 0;
    protected boolean invalidPosition;

    ShipView(int size) {
        this.shipSize = size;
        this.coordenadas = new ArrayList<>();
    }

    ShipView(int x, int y) {

        this.panelPositionX = x;
        this.panelPositionY = y;
        this.coordenadas = new ArrayList<>();

    }

    protected boolean placeShip(BoardPanel gameBoard, int x, int y, int orientacao) {

        List<List<CoordinateView>> board = gameBoard.getBoard();

        if (orientacao == 0) { // Posicionamento em 0° graus

            for (int i = x; i < x + this.shipSize; i++) {

                this.addCoordinate(board, i, y);
            }
            return true;
        } else if (orientacao == 1) { // Posicionamento em 90° graus

            for (int i = y; i > y - this.shipSize; i--) {

                this.addCoordinate(board, x, i);
            }
            return true;

        } else if (orientacao == 2) {

            for (int i = x; i > x - this.shipSize; i--) {

                this.addCoordinate(board, i, y);
            }
            return true;
        } else if (orientacao == 3) {

            for (int i = y; i < y + this.shipSize; i++) {

                this.addCoordinate(board, x, i);
            }
            return true;
        }
        return false;
    }

    protected void turnCoordinates(BoardPanel.Coordinates coord) {

        if (this.orientacao == 0) {
            coord.x = coord.x + this.shipSize - 1;
            coord.y = coord.y + this.shipSize - 1;
        } else if (this.orientacao == 1) {
            coord.x = coord.x + this.shipSize - 1;
            coord.y = coord.y - this.shipSize + 1;
        } else if (orientacao == 2) {
            coord.x = coord.x - this.shipSize + 1;
            coord.y = coord.y - this.shipSize + 1;
        } else if (orientacao == 3) {
            coord.x = coord.x - this.shipSize + 1;
            coord.y = coord.y + this.shipSize - 1;
        }
    }

    protected boolean clearCoordinates() {

        if (!this.coordenadas.isEmpty()) {
            for (CoordinateView previousCoord : this.coordenadas) {
                previousCoord.setSelected(false);
                previousCoord.setShip(null);
            }
            this.coordenadas.clear();
            return true;
        }
        return false;
    }

    protected void addCoordinate(List<List<CoordinateView>> board, int x, int y) {
        System.out.println("Ship View - placeShip.addCoordinate: " + x + ", " + y);
        CoordinateView coord = board.get(x).get(y);
        System.out.println("ShipView - addCoordinate: " + coord.getX() + ", " + coord.getY());
        coord.setShip(this);
        coord.setWater(false);
        coord.setSelected(true);
        this.coordenadas.add(coord);

    }

    public boolean contains(int px, int py) {
        boolean contains = px >= panelPositionX && px <= panelPositionX + 20 * shipSize && py >= panelPositionY
                && py <= panelPositionY + 20;
        System.out.println("Checking if point (" + px + ", " + py + ") is within ship at (" + panelPositionX + ", "
                + panelPositionY + ") with width " + 20 * shipSize + " and height " + 20 + ": " + contains);
        return contains;
    }

    abstract void paintShip(Graphics2D g2d);

    protected int getPanelPositionX() {
        return panelPositionX;
    }

    protected int getPanelPositionY() {
        return panelPositionY;
    }

    protected int getShipSize() {
        return shipSize;
    }

    protected void setPanelPositionX(int panelPositionX) {
        this.panelPositionX = panelPositionX;
    }

    protected void setPanelPositionY(int panelPositionY) {
        this.panelPositionY = panelPositionY;
    }

    protected void setBoardPosition(int x, int y) {
        this.panelPositionX = x;
        this.panelPositionY = y;
    }

    protected void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    protected String getColor() {
        return color;
    }

    protected boolean isSelected() {
        return selected;
    }

    protected void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public int getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(int orientacao) {
        this.orientacao = orientacao;
    }

    public boolean isInvalidPosition() {
        return invalidPosition;
    }

    public void setInvalidPosition(boolean invalidPosition) {
        this.invalidPosition = invalidPosition;
    }

    protected void deselectShip() {
        for (CoordinateView coord : coordenadas) {
            coord.setSelected(false);
        }
    }

    protected void selectShip() {
        for (CoordinateView coord : coordenadas) {
            coord.setSelected(true);
        }
    }

}
