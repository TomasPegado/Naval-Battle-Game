package model;

public abstract class Ship {
    protected ShipType type;
    protected int size;

    public Ship(ShipType type) {
        this.type = type;
        this.size = type.getSize();
    }

    public ShipType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    // Métodos abstratos ou comuns adicionais aqui
}
