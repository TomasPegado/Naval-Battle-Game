package model;

public class GameBoard {
    private Ship[][] board;

    public GameBoard() {
        board = new Ship[15][15];
    }

    public boolean addShip(Ship ship, String start, Direction direction) {
        // Implementação: Verifique se o navio cabe, não sobreponha, etc.
        return true; // Retorne falso se não puder colocar.
    }

    public ShotResult shoot(String coordinate) {
        // Implementação: Verifica o que foi atingido e atualiza o tabuleiro.
        return new ShotResult(); // Objeto de resultado customizado.
    }
}
