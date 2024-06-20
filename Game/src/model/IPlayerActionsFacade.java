package model;

import java.util.List;

public interface IPlayerActionsFacade {

    void updateShipPosition(Player player, int x, char y, int orientacao, int newX, char newY);

    boolean PositionShip(Player player, int x, char y, int shipSize, int orientacao);

    boolean Attack(Player playerAttacking, Player playerDefending, int coordinateX, char coordinateY);

    boolean ValidateAttack(Player playerAttacking, int coordinateX, char coordinateY);

    List<Ship> getPlayerShips(Player player);

}