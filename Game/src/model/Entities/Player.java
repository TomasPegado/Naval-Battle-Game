package model.Entities;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;
    private int BattleshipsToPosition = 1;
    private int CruisersToPosition = 2;
    private int DestroyerToPosition = 3;
    private int HydroplanesToPosition = 5;
    private int SubmarineToPosition = 4;
    private int ShipsLeft = 15;
    private List<Ship> shipsList;

    public Player(
            String name,
            int playerOrderId) {
        this.Name = name;
        this.PlayerOrderId = playerOrderId;
        this.DefenseMap = new GameBoard();
        this.AttackMap = new GameBoard();
        this.ShipsLeft = 15;
        this.shipsList = new ArrayList<>();

    }

    // Method to get the list of ships
    public List<Ship> getShips() {
        return shipsList;
    }

    public boolean PositionPlayerShip(Ship ship, int coordinateX, char coordinateY, boolean orientation) 
    {
        shipsList.add(ship);
        return DefenseMap.PositionShip(coordinateX, coordinateY, ship, orientation);
    }

    public GameBoard GetTabuleiroNavios() {
        return DefenseMap;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPlayerOrderId(int playerOrderId) {
        PlayerOrderId = playerOrderId;
    }

    public GameBoard GetTabuleiroAtaques() {
        return AttackMap;
    }

    public int GetTotalShipsLeft() {
        return ShipsLeft;
    }

    void SetTabuleiroAtaques(GameBoard tabuleiroAtaques) {
        this.AttackMap = tabuleiroAtaques;
    }

    void SetTabuleiroNavios(GameBoard tabuleiroNavios) {
        this.DefenseMap = tabuleiroNavios;
    }

    public void SetShipsLeft(int value) {

        this.ShipsLeft = value;
    }

    public String getName() {
        return Name;
    }

    public int getPlayerOrderId() {
        return PlayerOrderId;
    }
}