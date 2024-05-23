package view;

import javax.swing.*;
import java.awt.*;

public class WeaponsPanel extends JPanel {

    public WeaponsPanel() {

        setPreferredSize(new Dimension(400, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        ShipView ship = new BattleShipView(50, 400);
        ship.paintShip(g2d);

        ship = new CruiserView(50, 320);
        ship.paintShip(g2d);
        ship = new CruiserView(150, 320);
        ship.paintShip(g2d);

        ship = new DestroyerView(50, 240);
        ship.paintShip(g2d);
        ship = new DestroyerView(100, 240);
        ship.paintShip(g2d);
        ship = new DestroyerView(150, 240);
        ship.paintShip(g2d);

        ship = new SubmarineView(50, 160);
        ship.paintShip(g2d);
        ship = new SubmarineView(90, 160);
        ship.paintShip(g2d);
        ship = new SubmarineView(130, 160);
        ship.paintShip(g2d);
        ship = new SubmarineView(170, 160);
        ship.paintShip(g2d);

        ship = new HydroplaneView(50, 60);
        ship.paintShip(g2d);
        ship = new HydroplaneView(120, 60);
        ship.paintShip(g2d);
        ship = new HydroplaneView(190, 60);
        ship.paintShip(g2d);
        ship = new HydroplaneView(260, 60);
        ship.paintShip(g2d);
        ship = new HydroplaneView(330, 60);
        ship.paintShip(g2d);
    }
}
