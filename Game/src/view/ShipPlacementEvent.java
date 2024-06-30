package view;

public class ShipPlacementEvent {

    private ShipView selectedShip;
    private int boardX;
    private int boardY;
    private int orientation;
    private boolean removeShip = false;

    public ShipPlacementEvent(ShipView selectedShip, int boardX, int boardY, int orientation) {
        this.selectedShip = selectedShip;
        this.boardX = boardX;
        this.boardY = boardY;
        this.orientation = orientation;
    }

    public ShipPlacementEvent(ShipView selectedShip, boolean remove) {
        this.selectedShip = selectedShip;
        this.removeShip = remove;
    }

    public ShipView getSelectedShip() {
        return selectedShip;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean isRemoveShip() {
        return removeShip;
    }

}
