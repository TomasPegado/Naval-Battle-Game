package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PositionPanel extends JPanel {

    PositionPanel() {

        this.setPreferredSize(new Dimension(800, 800));
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.blue);
        g2D.setStroke(new BasicStroke(5));
        // g2D.drawLine(0, 0, 500, 500);

        g2D.fillRect(10, 20, 100, 200);
        // g2D.drawRect(10, 20, 100, 200);
    }
}
