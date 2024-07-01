package view;

import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AttackPanel extends JPanel {

    private BoardPanel attackBoard1;
    private BoardPanel attackBoard2;
    private JButton startGameButton;
    private JButton nextPlayerButton;
    private JButton saveGameButton;
    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel shotResultLabel; // JLabel para mostrar os resultados dos tiros
    private ObservableHelper observableHelper; // Instância de Observable
    private int currentPlayerIndex = 0;
    private List<BoardPanel> attackBoards;

    AttackPanel() {

        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        this.attackBoards = new ArrayList<>();

        this.attackBoard1 = new BoardPanel();
        this.attackBoard2 = new BoardPanel();
        attackBoards.add(attackBoard1);
        attackBoards.add(attackBoard2);

        attackBoard1.setIs_PositionBoard(false);
        attackBoard2.setIs_PositionBoard(false);

        player1Label = new JLabel("Player 1");
        player1Label.setLayout(new FlowLayout(FlowLayout.LEFT));
        player2Label = new JLabel("Player 2");
        player1Label.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Painel para os tabuleiros e seus respectivos JLabels
        JPanel boardsPanel = new JPanel(new GridLayout(1, 2));
        JPanel player1Panel = new JPanel(new BorderLayout());
        JPanel player2Panel = new JPanel(new BorderLayout());

        player1Panel.add(player1Label, BorderLayout.NORTH);
        player1Panel.add(attackBoard1, BorderLayout.WEST);

        player2Panel.add(player2Label, BorderLayout.NORTH);
        player2Panel.add(attackBoard2, BorderLayout.EAST);

        boardsPanel.add(player1Panel);
        boardsPanel.add(player2Panel);

        // Painel externo para adicionar margem ao topo
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBorder(new EmptyBorder(50, 0, 0, 0)); // Adiciona uma margem superior de 20 pixels
        outerPanel.add(boardsPanel, BorderLayout.CENTER);

        // Adiciona o JLabel para mostrar os resultados dos tiros
        shotResultLabel = new JLabel("Shot results will be displayed here");
        shotResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outerPanel.add(shotResultLabel, BorderLayout.NORTH);

        add(outerPanel, BorderLayout.CENTER);

        nextPlayerButton = new JButton("Next Player");
        nextPlayerButton.setVisible(false);
        nextPlayerButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {

                currentPlayerIndex = (currentPlayerIndex + 1) % 2;
                System.out.println("Next Player button clicked");
                nextPlayerButton.setVisible(false);
                saveGameButton.setVisible(false);
                observableHelper.setChanged();
                observableHelper.notifyObservers("Next Player Attacking");
            }
        });

        saveGameButton = new JButton("Save Game");
        saveGameButton.setVisible(false);
        saveGameButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Save files (*.txt)", "txt"));
                int option = fileChooser.showSaveDialog(AttackPanel.this);
                saveGameButton.setVisible(false);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".txt")) {
                        file = new File(file.getAbsolutePath() + ".txt");
                    }
                    SaveGameEvent saveGameEvent = new SaveGameEvent(file);
                    observableHelper.setChanged();
                    observableHelper.notifyObservers(saveGameEvent);
                }
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setVisible(true);
        startGameButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aqui você pode adicionar a lógica para passar para o próximo jogador
                startGameButton.setVisible(false);
                System.out.println("Start Game button clicked");
                observableHelper.setChanged();
                observableHelper.notifyObservers("Start Game button clicked");
            }
        });

        // Adicionar o botão na parte inferior central
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(nextPlayerButton);
        buttonPanel.add(saveGameButton);
        buttonPanel.add(startGameButton);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    protected void setShotResultMessage(String message) {
        shotResultLabel.setText(message);
    }

    protected JButton getStartGameButton() {
        return startGameButton;
    }

    protected JButton getNextPlayerButton() {
        return nextPlayerButton;
    }

    protected JButton getSaveGameButton() {
        return saveGameButton;
    }

    public List<BoardPanel> getAttackBoards() {
        return attackBoards;
    }

    public void setStartGameButtonVisibility(boolean isVisible){
        startGameButton.setVisible(isVisible);
    }

    public void setNextPlayerButtonVisibility(boolean isVisible){
        nextPlayerButton.setVisible(isVisible);
    }

    public void setSaveGameButtonVisibility(boolean isVisible){
        saveGameButton.setVisible(isVisible);
    }

    @SuppressWarnings("deprecation")
    public void addObserver(java.util.Observer observer) {
        observableHelper.addObserver(observer);
    }

}
