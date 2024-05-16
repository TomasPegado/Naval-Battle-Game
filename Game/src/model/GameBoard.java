package model;

public class GameBoard {
    private int[][] board;

   public GameBoard() {
        // Inicializar o tabuleiro com água
        board = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0; // Água
            }
        }
    }

    boolean PositionShip(int x, int y, Ship ship, boolean orientacao) {
        if (orientacao == true && x + ship.GetSize() <= board.length) {
            // Horizontalmente
            for (int i = x; i < x + ship.GetSize(); i++) {
                board[i][y] = 1; // Parte do navio
            }
            return true;

        } else if (orientacao == false && y + ship.GetSize() <= board[0].length) {
            // Verticalmente
            for (int j = y; j < y + ship.GetSize(); j++) {
                board[x][j] = 1;
            }
            return true;
        }
        return false;
    }

    protected boolean shotDefense(int coordinateX, int coordinateY) {

        int position = board[coordinateX][coordinateY];
        if (position == 1) {
            board[coordinateX][coordinateY] = 2; // para marcar essa posição como acertada
            return true;

        } else {
            // para marcar essa posicao como
            return false;
        }

    }

    public void shotAttack(int coordinateX, int coordinateY, boolean hit) {

        if (hit) {
            board[coordinateX][coordinateY] = 2;
        } else {
            board[coordinateX][coordinateY] = 3; // marcar posicao como feito um disparo e errado
        }

    }

    protected int getValue(int coordinateX, int coordinateY) {

        return board[coordinateX][coordinateY];
    }

}
