package view;

import java.awt.*;

public class SubmarineView extends ShipView {

    public SubmarineView(int x, int y) {
        super(x, y);
        setShipSize(1);
        setColor("#5eed4e");
    }

    protected SubmarineView(int size) {
        super(size);
        setColor("#5eed4e");
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

    }
}
