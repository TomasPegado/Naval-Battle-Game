package model;

import java.util.List;

public class Player {
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;
    private int ShipsLeft;

   public Player(
            String name,
            int playerOrderId) {
        this.Name = name;
        this.PlayerOrderId = playerOrderId;
        this.DefenseMap = new GameBoard();
        this.AttackMap = new GameBoard();
        this.ShipsLeft = 5;
    }

    boolean PositionPlayerShip(Ship ship, int coordinateX, int coordinateY, boolean orientation) 
    {
        DefenseMap.PositionShip(coordinateX, coordinateY, ship, orientation);
        return true;
    }

    GameBoard GetTabuleiroNavios() 
    {
        return DefenseMap;
    }

    public GameBoard GetTabuleiroAtaques() 
    {
        return AttackMap;
    }

    int GetTotalShipsLeft() 
    {
        return ShipsLeft;
    }

    void SetTabuleiroAtaques(GameBoard tabuleiroAtaques) 
    {
        this.AttackMap = tabuleiroAtaques;
    }

    void SetTabuleiroNavios(GameBoard tabuleiroNavios) 
    {
        this.DefenseMap = tabuleiroNavios;
    }
    
    public void SetShipsLeft(int value) {
    	
    	this.ShipsLeft = value;
    }
}
