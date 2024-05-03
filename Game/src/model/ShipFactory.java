package model;

public class ShipFactory {

    // Método estático para criar navios baseado no tipo.
    public static Ship createShip(ShipType type) {
        switch (type) {
            case BATTLESHIP:
                return new Battleship();
            case CRUISER:
                return new Cruiser();
            case DESTROYER:
                return new Destroyer();
            case SUBMARINE:
                return new Submarine();
            case HYDROPLANE:
                return new Hydroplane();
            default:
                throw new IllegalArgumentException("Unknown ship type: " + type);
        }
    }
}
