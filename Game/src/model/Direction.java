package model;

public enum Direction {
    EAST(1, 0), // Move horizontalmente para a direita
    WEST(-1, 0), // Move horizontalmente para a esquerda
    NORTH(0, -1), // Move verticalmente para cima
    SOUTH(0, 1); // Move verticalmente para baixo

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
