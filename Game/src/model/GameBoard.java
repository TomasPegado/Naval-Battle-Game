package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class GameBoard extends Observable {

    private PositionPair[][] board;

    public GameBoard() {
        // Inicializar o tabuleiro com água
        board = new PositionPair[15][15];
        for (int x = 0; x < 15; x++) {
            for (char y = 'A'; y <= 'O'; y++) {
                board[x][y - 65] = new PositionPair(x, y);
            }
        }
    }

    protected boolean is_ValidPosition(Ship ship, int coordinateX, char coordinateY) {
        int y = coordinateY - 65;
        int x = coordinateX;

        PositionPair position = board[x][y];
        if (position.is_Water() || position.getShip() == ship) { // se for agua checa as fronteiras

            if (x + 1 < board.length) {
                if (!board[x + 1][y].is_Water() && board[x + 1][y].getShip() != ship)
                    return false; // fronteira direita

                if (y + 1 < board.length) {
                    if (!board[x + 1][y + 1].is_Water() && board[x + 1][y + 1].getShip() != ship)
                        return false; // fronteira direita embaixo
                }

                if (y - 1 >= 0) {
                    if (!board[x + 1][y - 1].is_Water() && board[x + 1][y - 1].getShip() != ship)
                        return false; // fronteira direita em cima
                }
            }

            if (x - 1 >= 0) {
                if (!board[x - 1][y].is_Water() && board[x - 1][y].getShip() != ship)
                    return false; // fronteira esquerda

                if (y + 1 < board.length) {
                    if (!board[x - 1][y + 1].is_Water() && board[x - 1][y + 1].getShip() != ship)
                        return false; // fronteira esquerda embaixo
                }
                if (y - 1 >= 0) {
                    if (!board[x - 1][y - 1].is_Water() && board[x - 1][y - 1].getShip() != ship)
                        return false; // fronteira esquerda em cima
                }
            }

            if (y + 1 < board.length) {
                if (!board[x][y + 1].is_Water() && board[x][y + 1].getShip() != ship)
                    return false; // fronteira embaixo
            }
            if (y - 1 >= 0) {
                if (!board[x][y - 1].is_Water() && board[x][y - 1].getShip() != ship)
                    return false; // fronteira em cima
            }

            return true;

        }

        return false;

    }

    protected boolean PositionShip(int x, char y, Ship ship, boolean orientacao) {

        int size = ship.GetSize();
        boolean success = false;

        if (ship.GetType() == ShipType.HYDROPLANES) { // Posicionamento do Hidroavião

            if (orientacao == true && x + 1 < board.length && x - 1 >= 0 && y - 65 + 1 < board.length) {
                // horizontalmente hidroavião

                if (!is_ValidPosition(ship, x, y)) {
                    return false;
                } else {
                    if (!is_ValidPosition(ship, x - 1, (char) (y + 1))) {
                        return false;
                    } else {
                        if (!is_ValidPosition(ship, x + 1, (char) (y + 1))) {
                            return false;
                        }
                    }
                }

                if (!ship.getPositionsList().isEmpty()) {

                    for (PositionPair coord : ship.getPositionsList()) {
                        coord.setShip(null);
                        coord.setWater(true);
                    }
                    ship.getPositionsList().clear();
                }

                board[x][y - 65].setShip(ship);
                board[x][y - 65].setWater(false);
                ship.addPosition(board[x][y - 65]);

                board[x - 1][y - 65 + 1].setShip(ship);
                board[x - 1][y - 65 + 1].setWater(false);
                ship.addPosition(board[x - 1][y - 65 + 1]);

                board[x + 1][y - 65 + 1].setShip(ship);
                board[x + 1][y - 65 + 1].setWater(false);
                ship.addPosition(board[x + 1][y - 65 + 1]);

                success = true;

            } else if (orientacao == false && x + 1 < board.length && y - 65 + 1 < board.length && y - 65 - 1 >= 0) {
                // verticalmente hidroavião
                if (!is_ValidPosition(ship, x, y)) {
                    return false;
                } else {
                    if (!is_ValidPosition(ship, x + 1, (char) (y - 1))) {
                        return false;
                    } else {
                        if (!is_ValidPosition(ship, x + 1, (char) (y + 1))) {
                            return false;
                        }
                    }
                }

                if (!ship.getPositionsList().isEmpty()) {

                    for (PositionPair coord : ship.getPositionsList()) {
                        coord.setShip(null);
                        coord.setWater(true);
                    }
                    ship.getPositionsList().clear();
                }

                board[x][y - 65].setShip(ship);
                board[x][y - 65].setWater(false);
                ship.addPosition(board[x][y - 65]);

                board[x + 1][y - 65 + 1].setShip(ship);
                board[x + 1][y - 65 + 1].setWater(false);
                ship.addPosition(board[x + 1][y - 65 + 1]);

                board[x + 1][y - 65 - 1].setShip(ship);
                board[x + 1][y - 65 - 1].setWater(false);
                ship.addPosition(board[x + 1][y - 65 - 1]);

                success = true;
            }
        } else {
            if (orientacao == true && x + size - 1 < board.length) {
                // Horizontalmente

                for (int i = x; i < x + size; i++) {
                    if (!is_ValidPosition(ship, i, y)) {
                        return false;
                    }
                }

                if (!ship.getPositionsList().isEmpty()) {

                    for (PositionPair coord : ship.getPositionsList()) {
                        coord.setShip(null);
                        coord.setWater(true);
                    }
                    ship.getPositionsList().clear();
                }

                for (int i = x; i < x + size; i++) {
                    board[i][y - 65].setShip(ship);
                    board[i][y - 65].setWater(false);
                    ship.addPosition(board[i][y - 65]);
                }
                success = true;

            } else if (orientacao == false && y + size - 65 - 1 < board[0].length) {
                // Verticalmente
                for (int j = y - 65; j < y + size - 65; j++) {
                    System.out.println(j);
                    if (!is_ValidPosition(ship, x, (char) (j + 65))) {
                        System.out.println("Falso aqui j = " + j);
                        return false;
                    }

                }
                if (!ship.getPositionsList().isEmpty()) {

                    for (PositionPair coord : ship.getPositionsList()) {
                        coord.setShip(null);
                        coord.setWater(true);
                    }
                    ship.getPositionsList().clear();
                }
                for (int j = y - 65; j < y + size - 65; j++) {
                    board[x][j].setShip(ship);
                    board[x][y - 65].setWater(false);
                    ship.addPosition(board[x][j]);
                }
                success = true;
            }
        }

        if (success) {
            setChanged();
            notifyObservers("Ship positioned at (" + (x + 1) + ", " + y + ")");
        } else {
            setChanged();
            notifyObservers("Ship NOT positioned at (" + (x + 1) + ", " + y + ")");
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
        return false;

    }

    protected void shotHit(PositionPair coordenada, Ship ship) {

        coordenada.setShip(ship);
        coordenada.setWater(false);

    }

    protected PositionPair getCoordinate(int coordinateX, char coordinateY) {

        return board[coordinateX][coordinateY - 65];
    }

}