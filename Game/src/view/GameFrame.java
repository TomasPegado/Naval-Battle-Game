package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {

    private PositionPanel positioning;

    public GameFrame(String title) {

        this.setTitle(title);
        positioning = new PositionPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(positioning);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public PositionPanel getPositionPanel() {
        return positioning;
    }

}
