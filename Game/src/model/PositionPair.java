package model;

import java.io.Serializable;

public class PositionPair implements Serializable{

    private int coordenadaX;
    private char coordenadaY;
    private boolean water = true;
    private Ship ship;
    private boolean hit = false;

    protected PositionPair() {
        this.coordenadaX = 1;
        this.coordenadaY = 'A';
    }

    protected PositionPair(int coordenadaX, char coordenadaY, Ship ship) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.ship = ship;

    }

    protected PositionPair(int coordenadaX, char coordenadaY) {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;

    }

    protected int getCoordenadaX() {
        return coordenadaX;
    }

    protected char getCoordenadaY() {
        return coordenadaY;
    }

    public Ship getShip() {
        return ship;
    }

    protected boolean is_Water() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    protected void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    protected void setCoordenadaY(char coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    protected void setShip(Ship ship) {
        this.ship = ship;
    }

    protected boolean getHit() {
        return hit;
    }

    protected void got_Hit() {
        this.hit = true;
    }

}
