package model;

public class Hydroplanes extends Ship {
    public Hydroplanes() {
        super(ShipType.HYDROPLANES);
    }

    @Override
    protected boolean positionShip(GameBoard gameBoard, int BoardX, char BoardY, int orientacao) {

        PositionPair[][] board = gameBoard.board;
        gameBoard.is_ValidPosition(this, BoardX, BoardY);
        if (this.getInvalidPosition(1)) {

            return false;
        }

        if (BoardX < board.length && BoardX >= 0 && BoardY - 65 >= 0 && BoardY - 65 < board.length) {

            if (orientacao == 0) { // Posicionamento em 0° graus

                gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY + 1));
                if (this.getInvalidPosition(1)) {
                    return false;
                } else {
                    gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY + 1));
                    if (this.getInvalidPosition(1)) {
                        return false;
                    }
                }

                this.clearPositions();

                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY + 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY + 1));

            } else if (orientacao == 1) { // Posicionamento em 90° graus

                gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY - 1));
                if (this.getInvalidPosition(1)) {
                    return false;
                } else {
                    gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY + 1));
                    if (this.getInvalidPosition(1)) {
                        return false;
                    }
                }

                this.clearPositions();

                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY - 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY + 1));

            } else if (orientacao == 2) { // Posicionamento em 180° graus

                gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY - 1));
                if (this.getInvalidPosition(1)) {

                    return false;
                } else {
                    gameBoard.is_ValidPosition(this, BoardX + 1, (char) (BoardY - 1));
                    if (this.getInvalidPosition(1)) {

                        return false;
                    }
                }

                this.clearPositions();
                this.addShipPosition(gameBoard, BoardX, BoardY);
                this.addShipPosition(gameBoard, BoardX - 1, (char) (BoardY - 1));
                this.addShipPosition(gameBoard, BoardX + 1, (char) (BoardY - 1));

            } else if (orientacao == 3) { // Posicionamento em 270° graus

                gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY - 1));
                if (this.getInvalidPosition(1)) {

                    return false;
                } else {
                    gameBoard.is_ValidPosition(this, BoardX - 1, (char) (BoardY + 1));
                    if (this.getInvalidPosition(1)) {
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
