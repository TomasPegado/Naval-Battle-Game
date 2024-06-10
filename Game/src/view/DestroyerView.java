package view;

import java.awt.*;

public class DestroyerView extends ShipView {

    public DestroyerView(int x, int y) {
        super(x, y);
        setShipSize(2);
        setColor("#f0f00e");
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        if (this.selected) {
            g2D.setColor(Color.RED); // Alterar cor para indicar seleção
        } else {
            g2D.setColor(Color.decode(this.getColor()));
        }
        g2D.fillRect(x, y, 20 * this.getShipSize(), 20);

    }
}
