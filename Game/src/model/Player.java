package model;

import java.util.List;

class Player {
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;
    private int ShipsLeft;

    Player(
            String name,
            int playerOrderId) {
        this.Name = name;
        this.PlayerOrderId = playerOrderId;
        this.DefenseMap = new GameBoard();
        this.AttackMap = new GameBoard();
        this.ShipsLeft = 5;
    }

    boolean PositionPlayerShip(Ship ship, int coordinateX, int coordinateY, boolean orientation) {
        DefenseMap.PositionShip(coordinateX, coordinateY, ship, orientation);
        return true;
    }

    GameBoard GetTabuleiroNavios() {
        return DefenseMap;
    }

    GameBoard GetTabuleiroAtaques() {
        return AttackMap;
    }

    int GetTotalShipsLeft() {
        return ShipsLeft;
    }

    public void setTabuleiroAtaques(GameBoard tabuleiroAtaques) {
        this.AttackMap = tabuleiroAtaques;
    }

    public void setTabuleiroNavios(GameBoard tabuleiroNavios) {
        this.DefenseMap = tabuleiroNavios;
    }
}
