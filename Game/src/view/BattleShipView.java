package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.*;
import model.ShipType;

public class BattleShipView extends JPanel {

    private static final int GRID_SIZE = 15;
    private static final int CELL_SIZE = 30;

    private BattleShipController controller;
    private JButton[][] gridButtons;
    private JButton readyButton;
    private JLabel instructions;

    public BattleShipView() {
        setLayout(new BorderLayout());

        // Painel para as armas
        JPanel weaponsPanel = new JPanel();
        weaponsPanel.setLayout(new GridLayout(5, 1, 5, 5)); // Ajustar conforme o número de armas
        weaponsPanel.setPreferredSize(new Dimension(200, 300));

        // Adicionar armas (botões ou labels)
        for (ShipType type : ShipType.values()) {
            JButton weaponButton = new JButton(type.name());
            weaponButton.setPreferredSize(new Dimension(200, 30));
            weaponButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.selectWeapon(type);
                }
            });
            weaponsPanel.add(weaponButton);
        }

        add(weaponsPanel, BorderLayout.WEST);

        // Painel do tabuleiro
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                final int x = i;
                final int y = j;
                gridButtons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.placeWeapon(x, y);
                    }
                });
                boardPanel.add(gridButtons[i][j]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // Painel inferior para instruções e botão
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        instructions = new JLabel("J1, selecione uma arma na lista.");
        bottomPanel.add(instructions, BorderLayout.NORTH);

        readyButton = new JButton("Tabuleiro Pronto!");
        readyButton.setEnabled(false); // Inicialmente desabilitado
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.boardReady();
            }
        });
        bottomPanel.add(readyButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setController(BattleShipController controller) {
        this.controller = controller;
    }

    // Atualiza o tabuleiro de acordo com o modelo
    public void updateBoard() {
        // Implementar a lógica para atualizar o tabuleiro baseado no estado atual do
        // modelo
    }

    // Habilita o botão "Tabuleiro Pronto!"
    public void enableReadyButton(boolean enable) {
        readyButton.setEnabled(enable);
    }

    // Atualiza as instruções na tela
    public void updateInstructions(String text) {
        instructions.setText(text);
    }
}
