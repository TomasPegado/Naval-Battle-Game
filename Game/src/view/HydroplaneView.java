package view;

import java.awt.*;

public class HydroplaneView extends ShipView {

    public HydroplaneView(int x, int y) {
        super(x, y);
        setShipSize(3);
        setColor("#326B00");
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        if (this.selected) {
            g2D.setColor(Color.RED); // Alterar cor para indicar seleção
        } else {
            g2D.setColor(Color.decode(this.getColor()));
        }
        g2D.fillRect(x, y, 20, 20);

        g2D.fillRect(x + 20, y + 20, 20, 20);
        g2D.fillRect(x - 20, y + 20, 20, 20);

    }
}
