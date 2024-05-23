package view;

import java.awt.*;
import javax.swing.JPanel;

public abstract class ShipView extends JPanel {

    protected int panelPositionX;
    protected int panelPositionY;
    protected int shipSize;
    protected boolean selected;
    protected String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    ShipView(int x, int y) {

        this.panelPositionX = x;
        this.panelPositionY = y;
        this.selected = false;
    }

    abstract void paintShip(Graphics2D g2d);

    public void is_Selected(Graphics2D g2d) {

    }

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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
