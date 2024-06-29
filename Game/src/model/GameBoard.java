package model;

import java.io.Serializable;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class GameBoard extends Observable implements Serializable {

    private static final long serialVersionUID = 1L;
    protected PositionPair[][] board;

    public GameBoard() {
        // Inicializar o tabuleiro com água
        board = new PositionPair[15][15];
        for (int x = 0; x < 15; x++) {
            for (char y = 'A'; y <= 'O'; y++) {
                board[x][y - 65] = new PositionPair(x, y);
            }
        }
    }

    public PositionPair[][] GetGameBoard(){
        return board;
    }

    protected void is_ValidPosition(Ship ship, int coordinateX, char coordinateY) {
        int y = coordinateY - 65;
        int x = coordinateX;

        PositionPair position = board[x][y];
        if (position.is_Water() || position.getShip() == ship) { // se for agua ou se for o mesmo navio checa as
                                                                 // fronteiras
            ship.setInvalidPosition(1, false); // Marca que não está sobrepondo nenhum navio
            if (x + 1 < board.length) {
                if (!board[x + 1][y].is_Water() && board[x + 1][y].getShip() != ship) {
                    ship.setInvalidPosition(0, true);
                    return; // fronteira direita
                }

                if (y + 1 < board.length) {
                    if (!board[x + 1][y + 1].is_Water() && board[x + 1][y + 1].getShip() != ship) {
                        ship.setInvalidPosition(0, true);
                        return; // fronteira direita embaixo
                    }

                }

                if (y - 1 >= 0) {
                    if (!board[x + 1][y - 1].is_Water() && board[x + 1][y - 1].getShip() != ship) {
                        ship.setInvalidPosition(0, true);
                        return; // fronteira direita em cima
                    }

                }
            }

            if (x - 1 >= 0) {
                if (!board[x - 1][y].is_Water() && board[x - 1][y].getShip() != ship) {
                    ship.setInvalidPosition(0, true);
                    return; // fronteira esquerda
                }

                if (y + 1 < board.length) {
                    if (!board[x - 1][y + 1].is_Water() && board[x - 1][y + 1].getShip() != ship) {
                        ship.setInvalidPosition(0, true);
                        return; // fronteira esquerda embaixo
                    }

                }
                if (y - 1 >= 0) {
                    if (!board[x - 1][y - 1].is_Water() && board[x - 1][y - 1].getShip() != ship) {
                        ship.setInvalidPosition(0, true);
                        return; // fronteira esquerda em cima
                    }

                }
            }

            if (y + 1 < board.length) {
                if (!board[x][y + 1].is_Water() && board[x][y + 1].getShip() != ship) {
                    ship.setInvalidPosition(0, true);
                    return; // fronteira embaixo
                }

            }
            if (y - 1 >= 0) {
                if (!board[x][y - 1].is_Water() && board[x][y - 1].getShip() != ship) {
                    ship.setInvalidPosition(0, true);
                    return; // fronteira em cima
                }

            }

            ship.setInvalidPosition(0, false);
            return;

        }

        ship.setInvalidPosition(1, true);

    }

    protected boolean PositionShip(int x, char y, Ship ship, int orientacao) {

        boolean success = false;

        success = ship.positionShip(this, x, y, orientacao);

        if (success) {
            setChanged();
            ModelPlacementEvent event = new ModelPlacementEvent(ship.getInvalidPosition(0), success, x, y);
            notifyObservers(event);
        } else {
            setChanged();
            ModelPlacementEvent event = new ModelPlacementEvent(ship.getInvalidPosition(0), success, x, y);
            notifyObservers(event);
        }

        return success;
    }

    protected boolean shotDefense(PositionPair coordenada) {

        // Marcar a posição como atingida e avisa se foi um navio
        coordenada.got_Hit();
        if (!coordenada.is_Water()) {

            Ship ship = coordenada.getShip();
            ship.got_Hit();
            return true;
        }
        ModelShotHitEvent event = new ModelShotHitEvent(true);
        setChanged();
        notifyObservers(event);
        return false;

    }

    protected void shotHit(PositionPair coordenada, Ship ship) {

        coordenada.setShip(ship);
        coordenada.setWater(false);

        for (PositionPair coord : ship.getPositionsList()) {
            if (!compareCoords(coord, coordenada) && coord.getHit()) {
                ModelShotHitEvent event = new ModelShotHitEvent(true, coord.getCoordenadaX(),
                        (int) (coord.getCoordenadaY() - 65));

                if (!ship.is_Active()) {
                    event.setSunk(true);
                }
                setChanged();
                notifyObservers(event);
                return;
            }
        }
        ModelShotHitEvent event = new ModelShotHitEvent(ship.GetSize());
        setChanged();
        notifyObservers(event);

    }

    protected PositionPair getCoordinate(int coordinateX, char coordinateY) {

        return board[coordinateX][coordinateY - 65];
    }

    private boolean compareCoords(PositionPair coord1, PositionPair coord2) {

        if (coord1.getCoordenadaX() == coord2.getCoordenadaX() && coord1.getCoordenadaY() == coord2.getCoordenadaY()) {
            return true;
        }
        return false;
    }

}