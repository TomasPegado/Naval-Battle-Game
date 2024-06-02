package view;

import javax.swing.*;
import java.awt.*;

public class PositionPanel extends JPanel {
    private WeaponsPanel shipPanel;
    private BoardPanel boardPanel;

    public PositionPanel() {
        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        shipPanel = new WeaponsPanel();
        boardPanel = new BoardPanel();

        this.add(shipPanel, BorderLayout.WEST);
        this.add(boardPanel, BorderLayout.EAST);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JComboBox<String> getShipSelector() {
        return shipPanel.getShipSelector();
    }

    public JButton getPositionButton() {
        return shipPanel.getPositionButton();
    }

    public JTextField getCoordXField() {
        return shipPanel.getCoordXField();
    }

    public JTextField getCoordYField() {
        return shipPanel.getCoordYField();
    }

    public void decrementShipCount(int index) {
        shipPanel.decrementShipCount(index);
    }
}
