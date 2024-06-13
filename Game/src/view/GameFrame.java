package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private static GameFrame instance;
    private PositionPanel positioning;

    public static synchronized GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame("Batalha Naval");
        }
        return instance;
    }

    public GameFrame(String title) {

        this.setTitle(title);
        positioning = new PositionPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(positioning);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    public PositionPanel getPositionPanel() {
        return positioning;
    }

}
