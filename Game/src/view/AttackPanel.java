package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttackPanel extends JPanel {

    private BoardPanel attackBoard1;
    private BoardPanel attackBoard2;
    private JButton startGameButton;
    private JButton nextPlayerButton;
    private ObservableHelper observableHelper; // Instância de Observable
    private int currentPlayerIndex;

    AttackPanel() {

        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable

        this.setPreferredSize(new Dimension(1000, 600));
        this.setLayout(new BorderLayout());

        this.attackBoard1 = new BoardPanel();
        this.attackBoard2 = new BoardPanel();

        attackBoard1.setIs_PositionBoard(false);
        attackBoard2.setIs_PositionBoard(false);

        add(attackBoard1, BorderLayout.WEST);
        add(attackBoard2, BorderLayout.EAST);

        nextPlayerButton = new JButton("Next Player");
        nextPlayerButton.setVisible(false);
        nextPlayerButton.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {

                nextPlayerButton.setVisible(false);
                System.out.println("Next Player button clicked");
                observableHelper.setChanged();
                observableHelper.notifyObservers("Next Player Attacking");
            }
        });

        startGameButton = new JButton("Start Game");
        startGameButton.setVisible(true);
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

}
