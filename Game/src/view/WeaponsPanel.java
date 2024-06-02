package view;

import javax.swing.*;
import java.awt.*;

public class WeaponsPanel extends JPanel {
    private JComboBox<String> shipSelector;
    private JButton positionButton;
    private JLabel[] shipLabels;
    private int[] shipCounts = {1, 1, 1, 1, 1, 1}; // Quantidades de exemplo

    private JTextField coordXField;
    private JTextField coordYField;

    public WeaponsPanel() {
        this.setLayout(new GridLayout(10, 1, 10, 10));

        String[] shipNames = {"Cruiser", "Submarine", "Battleship", "Destroyer", "Hidroplane"};
        shipLabels = new JLabel[shipNames.length];

        shipSelector = new JComboBox<>(shipNames);
        this.add(new JLabel("Select Ship:"));
        this.add(shipSelector);

        for (int i = 0; i < shipNames.length; i++) {
            shipLabels[i] = new JLabel(shipNames[i] + " (1 restantes)"); // Inicialmente, 1 de cada tipo
            this.add(shipLabels[i]);
        }

        this.add(new JLabel("Coordinate X:"));
        coordXField = new JTextField();
        this.add(coordXField);

        this.add(new JLabel("Coordinate Y:"));
        coordYField = new JTextField();
        this.add(coordYField);

        positionButton = new JButton("Position Ship");
        this.add(positionButton);
    }

    public JComboBox<String> getShipSelector() {
        return shipSelector;
    }

    public JButton getPositionButton() {
        return positionButton;
    }

    public JTextField getCoordXField() {
        return coordXField;
    }

    public JTextField getCoordYField() {
        return coordYField;
    }

    public void updateShipCount(int index) {
        String[] shipNames = {"Cruiser", "Submarine", "Battleship", "Destroyer", "Hydroplane"};
        shipLabels[index].setText(shipNames[index] + " (" + shipCounts[index] + " restantes)");
    }

    public void decrementShipCount(int index) {
        if (shipCounts[index] > 0) {
            shipCounts[index]--;
            updateShipCount(index);
        }
    }
}
