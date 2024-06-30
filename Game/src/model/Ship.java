package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Ship implements Serializable {
    private static final long serialVersionUID = 1L;
    private ShipType type;
    private int size;
    private List<PositionPair> positionsList;
    private boolean active = true; // flag para saber se esta afundado ou não
    private int hits = 0; // contador de tiros recebidos
    private int orientacao = 0;
    private List<Boolean> invalidPosition = new ArrayList<>();

    Ship(ShipType type) {
        this.type = type;
        this.size = type.GetSize();
        this.positionsList = new ArrayList<>();
        this.invalidPosition.add(false);
        this.invalidPosition.add(false);
    }

    ShipType GetType() {
        return type;
    }

    protected List<PositionPair> getPositionsList() {
        return positionsList;
    }

    protected boolean is_Active() {
        return active;
    }

    protected int getHits() {
        return hits;
    }

    public int GetSize() {
        return size;
    }

    public void setInvalidPosition(int index, boolean value) {
        this.invalidPosition.set(index, value);
    }

    public boolean getInvalidPosition(int index) {
        return invalidPosition.get(index);
    }

    public boolean isInvalidPosition() {
        for (PositionPair coord : positionsList) {
            if (coord.isInvalidPosition()) {
                this.setInvalidPosition(0, true);
                return this.getInvalidPosition(0);
            }
        }
        this.setInvalidPosition(0, false);
        return this.getInvalidPosition(0);
    }

    private void addPosition(PositionPair coordenada) {
        positionsList.add(coordenada);
    }

    protected void got_Hit() {

        hits += 1;
        if (hits == size) {
            this.active = false;

        }

    }

    protected void addShipPosition(GameBoard gameBoard, int x, char y) {

        PositionPair[][] board = gameBoard.board;
        board[x][y - 65].setShip(this);
        board[x][y - 65].setWater(false);
        this.addPosition(board[x][y - 65]);
    }

    protected void clearPositions() {
        if (!this.getPositionsList().isEmpty()) { // Atualiza coordenadas se navio ja tinha sido posicionado

            for (PositionPair coord : this.getPositionsList()) {
                coord.setShip(null);
                coord.setWater(true);
            }
            this.getPositionsList().clear();
        }
    }

    protected boolean positionShip(GameBoard gameBoard, int BoardX, char BoardY, int orientacao) {

        PositionPair[][] board = gameBoard.board;
        gameBoard.is_ValidPosition(this, BoardX, BoardY);
        if (this.getInvalidPosition(1)) { // Se sobrepõe um outro navio não posiciona
            return false;
        }

        if (orientacao == 0 && BoardX + this.GetSize() - 1 < board.length) { // Posicionamento em 0°

            for (int i = BoardX; i < BoardX + this.GetSize(); i++) {
                gameBoard.is_ValidPosition(this, i, BoardY);
                if (this.getInvalidPosition(1)) {
                    return false;
                }
            }

            this.clearPositions();
            for (int i = BoardX; i < BoardX + this.GetSize(); i++) {
                this.addShipPosition(gameBoard, i, BoardY);
            }
            this.orientacao = orientacao;
            return true;

        } else if (orientacao == 1 && BoardY - 65 - this.GetSize() + 1 >= 0) { // Posicionamento em 90°
                                                                               // graus

            for (int i = BoardY - 65; i > BoardY - 65 - this.GetSize(); i--) {
                gameBoard.is_ValidPosition(this, BoardX, (char) (i + 65));
                if (this.getInvalidPosition(1)) {
                    return false;
                }
            }

            this.clearPositions();
            for (int i = BoardY - 65; i > BoardY - 65 - this.GetSize(); i--) {
                this.addShipPosition(gameBoard, BoardX, (char) (i + 65));
            }
            this.orientacao = orientacao;
            return true;

        } else if (orientacao == 2 && BoardX - this.GetSize() + 1 >= 0) { // Posicionamento em 180° graus

            for (int i = BoardX; i > BoardX - this.GetSize(); i--) {
                gameBoard.is_ValidPosition(this, i, BoardY);
                if (this.getInvalidPosition(1)) {
                    return false;
                }
            }

            this.clearPositions();
            for (int i = BoardX; i > BoardX - this.GetSize(); i--) {
                this.addShipPosition(gameBoard, i, BoardY);
            }
            this.orientacao = orientacao;
            return true;

        } else if (orientacao == 3 && BoardY - 65 + this.GetSize() - 1 < board.length) { // Posicion em 270° graus

            for (int i = BoardY - 65; i < BoardY - 65 + this.GetSize(); i++) {
                gameBoard.is_ValidPosition(this, BoardX, (char) (i + 65));
                if (this.getInvalidPosition(1)) {
                    return false;
                }
            }

            this.clearPositions();
            for (int i = BoardY - 65; i < BoardY - 65 + this.GetSize(); i++) {
                this.addShipPosition(gameBoard, BoardX, (char) (i + 65));
            }
            this.orientacao = orientacao;
            return true;
        }

        return false;
    }
}
