package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PositionPanel extends JPanel {

    private WeaponsPanel weaponsPanel;
    private BoardPanel boardPanel;

    PositionPanel() {

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        this.weaponsPanel = new WeaponsPanel();
        boardPanel = new BoardPanel();

        add(weaponsPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.EAST);

        // Adicionar um listener ao WeaponsPanel para notificar BoardPanel sobre o navio
        // selecionado
        weaponsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ShipView selectedShip = weaponsPanel.getSelectedShip();
                boardPanel.setSelectedShip(selectedShip);
            }
        });
    }
}
