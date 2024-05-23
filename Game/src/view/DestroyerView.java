package view;

import java.awt.*;

public class DestroyerView extends ShipView {

    public DestroyerView(int x, int y) {
        super(x, y);
        setShipSize(y);
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        g2D.setColor(Color.YELLOW);
        g2D.fillRect(x, y, 20, 20);

        g2D.fillRect(x + 20, y, 20, 20);

    }
}
