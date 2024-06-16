package model;

public class ModelShotHitEvent {

    private int shipSize;
    private boolean water = false;
    private boolean hitBefore = false;
    private int previousHitCoordX;
    private int previousHitCoordY;
    private boolean sunk = false;

    protected ModelShotHitEvent(boolean hitBefore, int boardX, int boardY) {

        this.hitBefore = hitBefore;
        this.previousHitCoordX = boardX;
        this.previousHitCoordY = boardY;
    }

    protected ModelShotHitEvent(int size) {
        this.shipSize = size;
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

    public boolean isHitBefore() {
        return hitBefore;
    }

    public int getPreviousHitCoordX() {
        return previousHitCoordX;
    }

    public int getPreviousHitCoordY() {
        return previousHitCoordY;
    }

    protected void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public boolean isSunk() {
        return sunk;
    }

}
