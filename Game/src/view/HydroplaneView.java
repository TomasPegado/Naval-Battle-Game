package view;

import java.awt.*;

public class HydroplaneView extends ShipView {

    public HydroplaneView(int x, int y) {
        super(x, y);
        setShipSize(3);
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        g2D.setColor(Color.decode("#326B00"));
        g2D.fillRect(x, y, 20, 20);

        g2D.fillRect(x + 20, y - 20, 20, 20);
        g2D.fillRect(x + 40, y, 20, 20);

    }
}
