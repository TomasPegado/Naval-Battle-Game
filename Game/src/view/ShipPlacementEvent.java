package view;

public class ShipPlacementEvent {

    private ShipView selectedShip;
    private int boardX;
    private int boardY;

    public ShipPlacementEvent(ShipView selectedShip, int boardX, int boardY) {
        this.selectedShip = selectedShip;
        this.boardX = boardX;
        this.boardY = boardY;
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

}
