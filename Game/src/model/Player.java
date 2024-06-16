package model;

import java.util.List;
import java.util.Observable;
import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Player extends Observable {
    private String Name;
    private int PlayerOrderId;
    private GameBoard DefenseMap;
    private GameBoard AttackMap;
    private int ShipsLeft;
    private List<Ship> shipsList;
    private int shotsFired = 0;

    public Player(
            String name,
            int playerOrderId) {
        this.Name = name;
        this.PlayerOrderId = playerOrderId;
        this.DefenseMap = new GameBoard();
        this.AttackMap = new GameBoard();
        this.shipsList = new ArrayList<>();
        this.ShipsLeft = shipsList.size();

    }

    // Method to get the list of ships
    protected List<Ship> getShips() {
        return shipsList;
    }

    protected boolean PositionPlayerShip(Ship ship, int coordinateX, char coordinateY, boolean orientation) {
        if (DefenseMap.PositionShip(coordinateX, coordinateY, ship, orientation)) {
            if (!shipsList.contains(ship)) {
                shipsList.add(ship);
                System.out.println(shipsList.size());
            }
            if (shipsList.size() == 15) {
                setChanged();
                notifyObservers("All Ships Positioned: " + this.getName());
            }

            return true;
        }

        return false;
    }

    public GameBoard GetTabuleiroNavios() {
        return DefenseMap;
    }

    protected void setName(String name) {
        Name = name;
    }

    protected void setPlayerOrderId(int playerOrderId) {
        PlayerOrderId = playerOrderId;
    }

    public GameBoard GetTabuleiroAtaques() {
        return AttackMap;
    }

    protected int GetTotalShipsLeft() {
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

    protected int getShotsFired() {
        return shotsFired;
    }

    protected void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
        if (shotsFired == 3) {
            setChanged();
            notifyObservers("3 Shots Given");
            this.shotsFired = 0;
        }
    }

    protected void calculateShipsLeft() {

        int count = 0;
        for (Ship ship : shipsList) {
            if (ship.is_Active()) {
                count += 1;
            }
        }
        if (count == 0) {
            setChanged();
            notifyObservers("Game Over");
        }
    }

}