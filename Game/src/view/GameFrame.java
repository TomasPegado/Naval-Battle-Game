package view;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private static GameFrame instance;
    private PositionPanel positioning;
    private AttackPanel attacking;

    public static synchronized GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame("Batalha Naval");
        }
        return instance;
    }

    public GameFrame(String title) {

        this.setTitle(title);
        positioning = new PositionPanel();
        attacking = new AttackPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(positioning);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    }

    public PositionPanel getPositionPanel() {
        return positioning;
    }

    public AttackPanel getAttackingPanel() {
        return attacking;
    }

    public void showGameOverDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para trocar o painel de posicionamento pelo painel de ataque
    public void switchToAttackPanel() {
        this.positioning.remove(positioning.getWeaponsPanel());
        this.positioning.remove(positioning.getBoardPanel());
        this.remove(positioning); // Remove o painel de posicionamento
        this.add(attacking); // Adiciona o painel de ataque
        this.revalidate(); // Atualiza o layout do contêiner
        this.repaint(); // Redesenha o contêiner
        this.pack(); // Ajusta o tamanho da janela para o novo layout
    }

}
