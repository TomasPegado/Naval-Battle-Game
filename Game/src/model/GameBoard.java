package model;

public class GameBoard {

    private PositionPair[][] board;

    public GameBoard() {
        // Inicializar o tabuleiro com água
        board = new PositionPair[15][15];
        for (int i = 0; i < 15; i++) {
            for (char c = 'A'; c <= 'O'; c++) {
                board[i][c - 65].setCoordenadaX(i);
                board[i][c - 65].setCoordenadaY(c);
            }
        }
    }

    boolean PositionShip(int x, char y, Ship ship, boolean orientacao) {

        int size = ship.GetSize();
        if (orientacao == true && x + size <= board.length) {
            // Horizontalmente
            for (int i = x; i < x + size; i++) {
                board[i][y - 65].setShip(ship);
                board[i][y - 65].setWater(false);
                ship.addPosition(board[i][y - 65]);
            }
            return true;

        } else if (orientacao == false && y + ship.GetSize() - 65 <= board[0].length) {
            // Verticalmente
            for (int j = y - 65; j < y + ship.GetSize() - 65; j++) {
                board[x][j].setShip(ship);
                board[x][y - 65].setWater(false);
                ship.addPosition(board[x][j]);
            }
            return true;
        }
        return false;
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

    public void shotHit(PositionPair coordenada, Ship ship) {

        coordenada.setShip(ship);
        coordenada.setWater(false);
        coordenada.got_Hit();

    }

    protected PositionPair getCoordinate(int coordinateX, char coordinateY) {

        return board[coordinateX][coordinateY - 65];
    }

}
