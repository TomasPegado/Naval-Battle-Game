package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PositionPanel extends JPanel {

    private WeaponsPanel weaponsPanel;
    private BoardPanel boardPanel;
    private JButton nextPlayerButton;
    private JButton startGameButton;

    PositionPanel() {

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        this.weaponsPanel = new WeaponsPanel();
        this.boardPanel = new BoardPanel();

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

        // Adicionar um listener ao BoardPanel para deselecionar o navio no WeaponsPanel
        boardPanel.setShipPositionListener(new BoardPanel.ShipPositionListener() {
            @Override
            public void shipPositioned(ShipView ship) {
                weaponsPanel.deselectShip();
            }
        });

        // Crie o botão e adicione um listener
        nextPlayerButton = new JButton("Next Player");
        nextPlayerButton.setVisible(false);
        nextPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para passar para o próximo jogador
                nextPlayerButton.setVisible(false);
                System.out.println("Next Player button clicked");
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setVisible(false);
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para passar para o próximo jogador
                startGameButton.setVisible(false);
                System.out.println("Start Game button clicked");
            }
        });

        // Adicionar o botão na parte inferior central
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextPlayerButton);
        buttonPanel.add(startGameButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getNextPlayerButton() {
        return nextPlayerButton;
    }

    public JButton getStartGameButton() {
        return startGameButton;
    }

}
