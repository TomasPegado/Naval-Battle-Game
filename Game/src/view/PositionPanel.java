package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PositionPanel extends JPanel {

    private WeaponsPanel weaponsPanel;
    private BoardPanel boardPanel;
    private JButton nextPlayerButton;
    private JButton startGameButton;
    private ObservableHelper observableHelper; // Instância de Observable

    PositionPanel() {

        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        this.weaponsPanel = new WeaponsPanel();
        this.boardPanel = new BoardPanel();
        boardPanel.setIs_PositionBoard(true);

        add(weaponsPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.EAST);

        // Adicionar listeners iniciais
        addListeners();

        // Crie o botão e adicione um listener
        nextPlayerButton = new JButton("Next Player");
        nextPlayerButton.setVisible(false);
        nextPlayerButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para passar para o próximo jogador
                reset();
                nextPlayerButton.setVisible(false);
                System.out.println("Next Player button clicked");
                observableHelper.setChanged();
                observableHelper.notifyObservers("Next Player Positioning");
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setVisible(false);
        startGameButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para passar para o próximo jogador
                startGameButton.setVisible(false);
                System.out.println("Start Game button clicked");
                observableHelper.setChanged();
                observableHelper.notifyObservers("Positioning Finished");
            }
        });

        // Adicionar o botão na parte inferior central
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextPlayerButton);
        buttonPanel.add(startGameButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Configura o Key Binding para a tecla ESC
        bindKeyToAction("ESCAPE", KeyEvent.VK_ESCAPE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weaponsPanel.getSelectedShip() != null) {
                    weaponsPanel.performEscAction();
                } else {
                    boardPanel.performEscAction();
                }

            }
        });

    }

    private void bindKeyToAction(String name, int keyCode, Action action) {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), name);
        actionMap.put(name, action);
    }

    private void addListeners() {
        // Adicionar listeners ao WeaponsPanel e BoardPanel
        weaponsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (boardPanel.getSelectedShip() == null) {
                    weaponsPanel.selectShip(e.getX(), e.getY());
                }
            }
        });

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (weaponsPanel.getSelectedShip() != null) {
                    int boardX = (e.getX() - BoardPanel.MARGIN + 1) / BoardPanel.GRID_SIZE;
                    int boardY = (e.getY() - BoardPanel.MARGIN + 1) / BoardPanel.GRID_SIZE;
                    boardPanel.setSelectedShip(weaponsPanel.getSelectedShip());
                    boardPanel.shipPlacementEventNotifier(boardX, boardY);
                }
            }
        });

        boardPanel.setShipPositionListener(new BoardPanel.ShipPositionListener() {
            @Override
            public void shipPositioned(ShipView ship) {
                weaponsPanel.deselectShip();
            }

            @Override
            public void shipReturned(ShipView ship) {
                weaponsPanel.returnShip(ship);
            }
        });
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

    public WeaponsPanel getWeaponsPanel() {
        return weaponsPanel;
    }

    public void reset() {
        // Remove os painéis atuais
        remove(weaponsPanel);
        remove(boardPanel);

        // Cria novos painéis para resetar o estado
        this.weaponsPanel = new WeaponsPanel();
        this.boardPanel = new BoardPanel();
        boardPanel.setIs_PositionBoard(true);

        add(weaponsPanel, BorderLayout.WEST);
        add(boardPanel, BorderLayout.EAST);

        // Adicionar novamente os listeners
        addListeners();

        // Configura o Key Binding para a tecla ESC novamente
        bindKeyToAction("ESCAPE", KeyEvent.VK_ESCAPE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (weaponsPanel.getSelectedShip() != null) {
                    weaponsPanel.performEscAction();
                } else {
                    boardPanel.performEscAction();
                }
            }
        });

        // Atualiza o painel para refletir as mudanças
        revalidate();
        repaint();
    }

    @SuppressWarnings("deprecation")
    public void addObserver(java.util.Observer observer) {
        observableHelper.addObserver(observer);
    }

}
