package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PositionPanel extends JPanel {

    PositionPanel() {

        this.setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        ShipView ship = new BattleShipView(100, 600);
        ship.paintShip(g2d);

        ship = new CruiserView(100, 500);
        ship.paintShip(g2d);
        ship = new CruiserView(200, 500);
        ship.paintShip(g2d);

    }
    
}
