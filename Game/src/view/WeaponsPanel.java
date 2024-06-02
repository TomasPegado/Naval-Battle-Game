package view;

import javax.swing.*;
import java.awt.*;
import model.Interfaces.IGameListener;

public class WeaponsPanel extends JPanel implements IGameListener {
    private JComboBox<String> shipSelector;
    private JButton positionButton;
    private JLabel[] shipLabels;
    private int[] shipCounts = {2, 4, 1, 3, 5}; // Quantidades de exemplo
    private String[] shipNames = {"Cruiser", "Submarine", "Battleship", "Destroyer", "Hidroplane"};

    private JTextField coordXField;
    private JTextField coordYField;

    public WeaponsPanel() {
        this.setLayout(new GridLayout(10, 1, 10, 10));
        
        shipLabels = new JLabel[shipNames.length];

        shipSelector = new JComboBox<>(shipNames);
        this.add(new JLabel("Select Ship:"));
        this.add(shipSelector);

        for (int i = 0; i < shipNames.length; i++) {
            shipLabels[i] = new JLabel(shipCounts[i] + " " + shipNames[i] + " restantes.");
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
        if (shipCounts[index] > 0) {
            shipLabels[index].setText(shipCounts[index] + " " + shipNames[index] + " restantes.");
        } else {
            shipLabels[index].setText("Todos os " + shipNames[index] + " já foram posicionados.");
            shipSelector.removeItemAt(index);
        }
    }

    public void decrementShipCount(int index) {
        if (shipCounts[index] > 0) {
            shipCounts[index]--;
            updateShipCount(index);
        }
    }

    @Override
    public void OnGameStateChanged() {
        // Atualize a interface gráfica aqui conforme necessário
        System.out.println("O estado do jogo mudou!");
    }
}
