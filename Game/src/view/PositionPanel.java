package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PositionPanel extends JPanel {

    private ShipView selectedShip;

    PositionPanel() {

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        // Painel dos navios
        JPanel shipPanel = new WeaponsPanel();

        // Adiciona o painel dos navios à esquerda
        this.add(shipPanel, BorderLayout.WEST);

        // Adiciona o BoardPanel à direita
        BoardPanel boardPanel = new BoardPanel();
        this.add(boardPanel, BorderLayout.EAST);
    }
}
