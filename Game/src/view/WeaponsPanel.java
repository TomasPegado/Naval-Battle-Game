package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class WeaponsPanel extends JPanel {

    private List<ShipView> ships;
    private ShipView selectedShip;

    public WeaponsPanel() {

        setPreferredSize(new Dimension(400, 600));
        ships = new ArrayList<>();
        initializeShips();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Mouse clicked at: " + e.getX() + ", " + e.getY());
                selectShip(e.getX(), e.getY());
            }
        });
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

    private void selectShip(int x, int y) {
        for (ShipView ship : ships) {
            if (ship.contains(x, y)) {
                selectedShip = ship;
                repaint();
                return;
            }
        }
        selectedShip = null; // Deseleciona se clicar fora de qualquer navio
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (ShipView ship : ships) {
            if (ship == selectedShip) {
                g2d.setColor(Color.RED); // Alterar cor para indicar seleção
            } else {
                g2d.setColor(Color.decode(ship.getColor())); // Cor normal do navio
            }
            ship.paintShip(g2d);
        }
    }

    public ShipView getSelectedShip() {
        return selectedShip;
    }
}
