package view;

import java.util.List;
import java.util.ArrayList;

public class ViewActionsFacade {
    private static ViewActionsFacade instance;

    public static synchronized ViewActionsFacade getInstance() {
        if (instance == null) {
            instance = new ViewActionsFacade();
        }
        return instance;
    }

    public boolean placeShip(BoardPanel board, int x, int y) {

        if (board == null) {
            return false;
        }

        board.placeShip(x, y);
        return true;
    }

    public List<ShipView> getBoardShips(BoardPanel board) {
        return board.getShipsList();
    }

    public int getShipBoardX(ShipView ship) {
        return ship.getPanelPositionX();
    }

    public int getShipBoardY(ShipView ship) {
        return ship.getPanelPositionY();
    }

    public int getShipSize(ShipView ship) {
        return ship.getShipSize();
    }

}
