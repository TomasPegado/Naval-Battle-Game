package view;

import java.awt.*;

public class CoordinateView {
    private int x;
    private int y;
    private ShipView ship;
    private boolean isWater;
    private boolean selected = false; // quando é selecionada com um navio

    public CoordinateView(int x, int y) {
        this.x = x;
        this.y = y;
        this.isWater = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ShipView getShip() {
        return ship;
    }

    public void setShip(ShipView ship) {
        this.ship = ship;

    }

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean isWater) {
        this.isWater = isWater;

    }

    public void draw(Graphics2D g2d, int size) {
        if (isWater) {
            g2d.setColor(Color.BLUE);
        } else if (ship != null) {
            g2d.setColor(Color.decode(ship.getColor())); // Cor do navio
            if (selected) {
                g2d.setColor(Color.RED);
            } else if (ship.isSunk()) {
                g2d.setColor(Color.RED);
            }
        } else {
            g2d.setColor(Color.decode("#F2FDFB"));
        }
        g2d.fillRect(x * size, y * size, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x * size, y * size, size, size); // Desenha borda
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
