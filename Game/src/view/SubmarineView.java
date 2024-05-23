package view;

import java.awt.*;

public class SubmarineView extends ShipView {

    public SubmarineView(int x, int y) {
        super(x, y);
        setShipSize(1);
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        g2D.setColor(Color.GREEN);
        g2D.fillRect(x, y, 20, 20);

    }
}
