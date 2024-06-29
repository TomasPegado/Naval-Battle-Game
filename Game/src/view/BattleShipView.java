package view;

import java.awt.*;

public class BattleShipView extends ShipView {

    public BattleShipView(int x, int y) {
        super(x, y);
        setShipSize(5);
        setColor("#663A00");
    }

    protected BattleShipView(int size) {
        super(size);
        setColor("#663A00");
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        if (this.selected) {
            g2D.setColor(Color.CYAN); // Alterar cor para indicar seleção
        } else {
            g2D.setColor(Color.decode(this.getColor()));
        }
        g2D.fillRect(x, y, 20 * this.getShipSize(), 20);

    }
}
