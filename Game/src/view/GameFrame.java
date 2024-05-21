package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    PositionPanel positioning;

    public GameFrame() {

        positioning = new PositionPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(positioning);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
