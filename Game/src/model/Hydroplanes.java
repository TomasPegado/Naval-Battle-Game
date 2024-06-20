package model;

public class Hydroplanes extends Ship {
    public Hydroplanes() {
        super(ShipType.HYDROPLANES);
    }

    @Override
    protected boolean positionShip(GameBoard gameBoard, int BoardX, char BoardY, int orientacao) {

        PositionPair[][] board = gameBoard.board;
        if (!gameBoard.is_ValidPosition(this, BoardX, BoardY)) {
            return false;
        }

        if (BoardX + 1 < board.length && BoardX - 1 >= 0 && BoardY - 65 + 1 < board.length) {

            if (orientacao == 0) { // Posicionamento em 0° graus

                if (!gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY + 1))) {
                    return false;
                } else {
                    if (!gameBoard.is_ValidPosition(this, orientacao, (char) (BoardY + 1))) {
                        return false;
                    }
                }

                this.clearPositions();

                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY + 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY + 1));

            } else if (orientacao == 1) { // Posicionamento em 90° graus

                if (!gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY - 1))) {
                    return false;
                } else {
                    if (!gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY + 1))) {
                        return false;
                    }
                }

                this.clearPositions();

                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY - 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY + 1));

            } else if (orientacao == 2) { // Posicionamento em 180° graus

                if (!gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY - 1))) {
                    return false;
                } else {
                    if (!gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY - 1))) {
                        return false;
                    }
                }

                this.clearPositions();
                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY - 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY - 1));

            } else if (orientacao == 3) { // Posicionamento em 270° graus

                if (!gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY - 1))) {
                    return false;
                } else {
                    if (!gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY + 1))) {
                        return false;
                    }
                }

                this.clearPositions();
                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY - 1));
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY + 1));
            }

            return true;
        }

        return false;
    }

}
