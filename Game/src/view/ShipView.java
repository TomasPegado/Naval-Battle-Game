package view;

import java.awt.*;
import javax.swing.JPanel;

public abstract class ShipView extends JPanel {
    
    protected int panelPositionX;
    protected int panelPositionY;
    protected int shipSize;

    ShipView(int x , int y){

        this.panelPositionX = x;
        this.panelPositionY = y;
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

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    
}

