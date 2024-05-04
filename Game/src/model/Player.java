package model;

import java.util.List;

class Player 
{
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;

    Player(
            String name,
            int playerOrderId) 
    {
        this.Name = name;
        this.PlayerOrderId = playerOrderId;
        this.DefenseMap = new GameBoard();
        this.AttackMap = new GameBoard();
    }

    boolean PositionPlayerShip(Ship ship, Coordinate coordinate, boolean orientation)
    {
        DefenseMap.PositionShip(coordinate.GetXCoordinate(), coordinate.GetYCoordinate(), ship,  orientation);
        return true;
    }

    GameBoard GetTabuleiroNavios() {
        return DefenseMap;
    }

    GameBoard GetTabuleiroAtaques() {
        return AttackMap;
    }
}
