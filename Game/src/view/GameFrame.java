package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class GameFrame extends JFrame {
    private PositionPanel positioning;
    private WeaponsPanel weaponsPanel;

    public GameFrame(String title, WeaponsPanel weaponsPanel) {
        this.setTitle(title);
        this.weaponsPanel = weaponsPanel;
        positioning = new PositionPanel();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        this.add(positioning, BorderLayout.CENTER);
        this.add(weaponsPanel, BorderLayout.EAST);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addPositioningListener(ActionListener listener) {
        positioning.getPositionButton().addActionListener(listener);
    }

    public void addBoardClickListener(MouseAdapter listener) {
        positioning.getBoardPanel().addMouseListener(listener);
    }

    public void updateBoard() {
        positioning.getBoardPanel().repaint();
    }

    public int getSelectedShipIndex() {
        return positioning.getShipSelector().getSelectedIndex();
    }

    public void decrementSelectedShipCount() {
        int selectedIndex = getSelectedShipIndex();
        positioning.decrementShipCount(selectedIndex);
    }

    public int getCoordX() {
        try {
            return Integer.parseInt(positioning.getCoordXField().getText());
        } catch (NumberFormatException e) {
            return -1; // Indica erro
        }
    }
    
    public char getCoordY() {
        try {
            return positioning.getCoordYField().getText().toUpperCase().charAt(0);
        } catch (Exception e) {
            return ' '; // Valor inválido para indicar erro
        }
    }

    public BoardPanel getBoardPanel() {
        return positioning.getBoardPanel();
    }
}
