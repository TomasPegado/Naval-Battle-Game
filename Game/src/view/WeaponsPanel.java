package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class WeaponsPanel extends JPanel {

    private List<ShipView> ships;
    private ShipView selectedShip;

    public WeaponsPanel() {

        setPreferredSize(new Dimension(400, 600));
        ships = new ArrayList<>();
        initializeShips();

        // bindKeyToAction("ESCAPE", KeyEvent.VK_ESCAPE, new AbstractAction() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // Código a ser executado quando a tecla ESC for pressionada
        // System.out.println("Wepaons Panel recebu tecla esc");
        // if (selectedShip != null) {
        // selectedShip.setSelected(false);
        // selectedShip = null;
        // repaint();
        // }
        // }
        // });
    }

    // private void bindKeyToAction(String name, int keyCode, Action action) {
    // InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    // ActionMap actionMap = getActionMap();

    // inputMap.put(KeyStroke.getKeyStroke(keyCode, 0), name);
    // actionMap.put(name, action);
    // }

    protected void performEscAction() {
        // Código específico para o WeaponsPanel quando ESC é pressionado
        System.out.println("ESC pressed in WeaponsPanel");
        if (selectedShip != null) {
            selectedShip.setSelected(false);
            selectedShip = null;
            repaint();
        }
    }

    private void initializeShips() {
        ships.add(new BattleShipView(50, 400));
        ships.add(new CruiserView(50, 320));
        ships.add(new CruiserView(150, 320));
        ships.add(new DestroyerView(50, 240));
        ships.add(new DestroyerView(100, 240));
        ships.add(new DestroyerView(150, 240));
        ships.add(new SubmarineView(50, 160));
        ships.add(new SubmarineView(90, 160));
        ships.add(new SubmarineView(130, 160));
        ships.add(new SubmarineView(170, 160));
        ships.add(new HydroplaneView(50, 60));
        ships.add(new HydroplaneView(120, 60));
        ships.add(new HydroplaneView(190, 60));
        ships.add(new HydroplaneView(260, 60));
        ships.add(new HydroplaneView(330, 60));
        System.out.println("Navios inicializados");
    }

    protected void selectShip(int x, int y) {

        if (selectedShip == null) {
            boolean found = false;
            for (ShipView ship : ships) {

                if (ship.contains(x, y)) {
                    selectedShip = ship;
                    found = true;
                    ship.setSelected(found);
                    System.out.println("Selected ship at: " + x + ", " + y);
                    break;
                }
            }
            if (!found) {
                // selectedShip = null; // Deseleciona se clicar fora de qualquer navio
                System.out.println("No ship selected");
            }
        }

        repaint();
    }

    protected void deselectShip() {
        selectedShip.setSelected(false);
        ships.remove(selectedShip);
        selectedShip = null;
        System.out.println("Ship removed from Weapons Panel");
        repaint();
    }

    protected void returnShip(ShipView ship) {
        ships.add(ship);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (ShipView ship : ships) {

            ship.paintShip(g2d);
        }
    }

    public ShipView getSelectedShip() {
        return selectedShip;
    }
}
