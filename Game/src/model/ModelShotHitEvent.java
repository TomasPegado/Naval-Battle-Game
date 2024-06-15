package model;

public class ModelShotHitEvent {

    private int shipSize;
    private boolean water = false;
    private boolean sunk = false;

    protected ModelShotHitEvent(int size, boolean sunk) {
        this.shipSize = size;
        this.sunk = sunk;
    }

    protected ModelShotHitEvent(boolean is_Water) {
        this.water = is_Water;
    }

    public int getShipSize() {

        return shipSize;
    }

    public boolean isWater() {
        return water;
    }

    public boolean isSunk() {
        return sunk;
    }

}
