package model;

import java.util.List;
import java.util.ArrayList;

public class Player {
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;
    private int ShipsLeft;
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
        addShip(ShipType.BATTLESHIP);
        addShip(ShipType.CRUISER);
        addShip(ShipType.CRUISER);
        addShip(ShipType.DESTROYER);
        addShip(ShipType.DESTROYER);
        addShip(ShipType.DESTROYER);
        addShip(ShipType.SUBMARINE);
        addShip(ShipType.SUBMARINE);
        addShip(ShipType.SUBMARINE);
        addShip(ShipType.SUBMARINE);
        addShip(ShipType.HYDROPLANES);
        addShip(ShipType.HYDROPLANES);
        addShip(ShipType.HYDROPLANES);
        addShip(ShipType.HYDROPLANES);
        addShip(ShipType.HYDROPLANES);

    }

    // Method to add a ship to the player's list
    private void addShip(ShipType type) {
        Ship ship = ShipFactory.createShip(type);
        shipsList.add(ship);
    }

    // Method to get the list of ships
    protected List<Ship> getShips() {
        return shipsList;
    }

    boolean PositionPlayerShip(Ship ship, int coordinateX, char coordinateY, boolean orientation) {
        DefenseMap.PositionShip(coordinateX, coordinateY, ship, orientation);
        return true;
    }

    GameBoard GetTabuleiroNavios() {
        return DefenseMap;
    }

    protected void setName(String name) {
        Name = name;
    }

    protected void setPlayerOrderId(int playerOrderId) {
        PlayerOrderId = playerOrderId;
    }

    protected GameBoard GetTabuleiroAtaques() {
        return AttackMap;
    }

    int GetTotalShipsLeft() {
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
