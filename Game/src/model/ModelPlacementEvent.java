package model;

public class ModelPlacementEvent {

    private boolean invalidPosition;
    private boolean placement;
    private int x;
    private char y;

    public ModelPlacementEvent(boolean invalidPosition, boolean placement, int x, char y) {
        this.invalidPosition = invalidPosition;
        this.placement = placement;
        this.x = x;
        this.y = y;
    }

    public boolean isInvalidPosition() {
        return invalidPosition;
    }

    public boolean isPlacement() {
        return placement;
    }

    public int getX() {
        return x;
    }

    public char getY() {
        return y;
    }

}
