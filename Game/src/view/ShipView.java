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

    ShipView(int x, int y) {

        this.panelPositionX = x;
        this.panelPositionY = y;
        this.coordenadas = new ArrayList<>();

    }

    public boolean contains(int px, int py) {
        boolean contains = px >= panelPositionX && px <= panelPositionX + 20 * shipSize && py >= panelPositionY
                && py <= panelPositionY + 20;
        System.out.println("Checking if point (" + px + ", " + py + ") is within ship at (" + panelPositionX + ", "
                + panelPositionY + ") with width " + 20 * shipSize + " and height " + 20 + ": " + contains);
        return contains;
    }

    abstract void paintShip(Graphics2D g2d);

    public int getPanelPositionX() {
        return panelPositionX;
    }

    public int getPanelPositionY() {
        return panelPositionY;
    }

    public int getShipSize() {
        return shipSize;
    }

    public void setPanelPositionX(int panelPositionX) {
        this.panelPositionX = panelPositionX;
    }

    public void setPanelPositionY(int panelPositionY) {
        this.panelPositionY = panelPositionY;
    }

    public void setBoardPosition(int x, int y) {
        this.panelPositionX = x;
        this.panelPositionY = y;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
