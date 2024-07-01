package view;

import java.util.List;

import javax.swing.JButton;

public class ViewActionsFacade {
    private static ViewActionsFacade instance;

    public static synchronized ViewActionsFacade getInstance() {
        if (instance == null) {
            instance = new ViewActionsFacade();
        }
        return instance;
    }

    public boolean placeShip(BoardPanel board, int x, int y, int orientation, boolean invalidPosition) {

        if (board == null) {
            return false;
        }

        board.placeShip(x, y, orientation, invalidPosition);
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

    public BoardPanel getCurrentAttackerBoard(AttackPanel attackPanel, int index) {

        return attackPanel.getAttackBoards().get(index);
    }

    public void firstShotHit(BoardPanel attackBoard, int boardX, int boardY, int size, AttackPanel attackPanel) {

        attackBoard.firstShotHit(boardX, boardY, size);

        if (size == 5) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - BattleShip Hit");
        } else if (size == 4) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - Cruiser Hit");
        } else if (size == 3) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - HydroPlane Hit");
        } else if (size == 2) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - Destroyer Hit");
        } else if (size == 1) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - Submarine Hit");
        } else {
            throw new IllegalArgumentException("Unknown ship type: " + size);
        }
    }

    public void shotHitAgain(BoardPanel attackBoard, int boardX, int boardY, int previousHitCoordX,
            int previousHitCoordY, boolean sunk, AttackPanel attackPanel) {

        ShipView ship = attackBoard.shotHitAgain(boardX, boardY, previousHitCoordX, previousHitCoordY, sunk);

        int shipSize = ship.getShipSize();

        if (shipSize == 5) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - BattleShip Hit - Sunk: "
                            + sunk);
        } else if (shipSize == 4) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - Cruiser Hit - Sunk: " + sunk);
        } else if (shipSize == 3) {
            attackPanel.setShotResultMessage(
                    "Coordinates: (" + (char) (boardY + 65) + ", " + (boardX + 1) + ") - HydroPlane Hit - Sunk: "
                            + sunk);
        }
    }

    public void shotWater(BoardPanel attackBoard, int boardX, int boardY, AttackPanel attackPanel) {

        attackBoard.shotWater(boardX, boardY);
        attackPanel.setShotResultMessage("Shot hitted Water");
    }

    public void setVisibleNextPlayerButton(AttackPanel attackPanel, String name) {

        JButton save = attackPanel.getSaveGameButton();
        save.setText("Save Game");
        save.setVisible(true);

        JButton next = attackPanel.getNextPlayerButton();
        next.setText(name + "'s turn");
        next.setVisible(true);
    }

}
