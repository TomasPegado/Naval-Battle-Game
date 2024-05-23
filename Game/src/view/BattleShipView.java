package view;

import java.awt.*;

public class BattleShipView extends ShipView {

    public BattleShipView(int x, int y) {
        super(x, y);
        setShipSize(5);
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        g2D.setColor(Color.decode("#663A00"));
        g2D.fillRect(x, y, 20, 20);

        g2D.fillRect(x + 20, y, 20, 20);
        g2D.fillRect(x + 40, y, 20, 20);
        g2D.fillRect(x + 60, y, 20, 20);
        g2D.fillRect(x + 80, y, 20, 20);

    }
}
