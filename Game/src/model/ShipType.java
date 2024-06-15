package model;

public enum ShipType {
    BATTLESHIP(5),
    CRUISER(4),
    DESTROYER(2),
    SUBMARINE(1),
    HYDROPLANES(3);

    private final int size;

    ShipType(int size) {
        this.size = size;
    }

    public int GetSize() {
        return this.size;
    }
}
