package view;

import java.awt.*;

public class CoordinateView {
    private int x;
    private int y;
    private ShipView ship;
    private boolean isWater;

    public CoordinateView(int x, int y) {
        this.x = x;
        this.y = y;
        this.isWater = true; // Inicialmente, todas as coordenadas são água
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
        this.isWater = (ship == null); // Se não há navio, é água
    }

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean isWater) {
        this.isWater = isWater;
        if (isWater) {
            this.ship = null; // Se for definido como água, remove qualquer navio
        }
    }

    public void draw(Graphics2D g2d, int size) {
        if (isWater) {
            g2d.setColor(Color.BLUE);
        } else if (ship != null) {
            g2d.setColor(Color.decode(ship.getColor())); // Cor do navio
        }
        g2d.fillRect(x * size, y * size, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x * size, y * size, size, size); // Desenha borda
    }
}

