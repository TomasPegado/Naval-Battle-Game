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
            case HYDROPLANES:
                return new Hydroplanes();
            default:
                throw new IllegalArgumentException("Unknown ship type: " + type);
        }
    }

    // Método estático para criar navios baseado no tamanho.
    public static Ship createShipSize(int size) {
        switch (size) {
            case 5:
                return new Battleship();
            case 4:
                return new Cruiser();
            case 2:
                return new Destroyer();
            case 1:
                return new Submarine();
            case 3:
                return new Hydroplanes();
            default:
                throw new IllegalArgumentException("Unknown ship type: " + size);
        }
    }
}
