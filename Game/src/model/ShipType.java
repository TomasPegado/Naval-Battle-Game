package model;

public enum ShipType {
    BATTLESHIP(5),
    CRUISER(4),
    DESTROYER(2),
    SUBMARINE(1),
    HYDROPLANE(3); // Assume que o hidroavião ocupa um espaço especial no tabuleiro.

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }
}
