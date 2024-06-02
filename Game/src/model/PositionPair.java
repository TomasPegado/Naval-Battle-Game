package model;

import model.Entities.Ship;

public class PositionPair {

    private int coordenadaX;
    private char coordenadaY;
    private boolean water = true;
    private Ship ship;
    private boolean hit = false;

    public PositionPair() {
        this.coordenadaX = 1;
        this.coordenadaY = 'a';
    }

    public PositionPair(int coordenadaX, char coordenadaY, Ship ship) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ship = ship;

    }

    public PositionPair(int coordenadaX, char coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;

    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public char getCoordenadaY() {
        return coordenadaY;
    }

    public Ship getShip() {
        return ship;
    }

    public boolean is_Water() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public void setCoordenadaY(char coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public boolean getHit() {
        return hit;
    }

    public void got_Hit() {
        this.hit = true;
    }

}
