package view;

import java.util.List;

import javax.swing.JButton;

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

    public int getShipSize(ShipView ship) {
        return ship.getShipSize();
    }

    public int getCurrentPositionX(BoardPanel board) {
        return board.getCurrentPositionX();
    }

    public int getCurrentPositionY(BoardPanel board) {
        return board.getCurrentPositionY();
    }

    public void setVisibleNextPlayerButton(PositionPanel panel) {
        JButton next = panel.getNextPlayerButton();
        next.setVisible(true);
    }

    public void setVisibleStartGameButton(PositionPanel panel) {
        JButton next = panel.getStartGameButton();
        next.setVisible(true);
    }

}
