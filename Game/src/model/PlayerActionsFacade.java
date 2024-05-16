package model;

public class PlayerActionsFacade {
    private static PlayerActionsFacade instance;

    public static synchronized PlayerActionsFacade getInstance() {
        if (instance == null) {
            instance = new PlayerActionsFacade();
        }
        return instance;
    }

    public boolean PositionShip(Player player, int x, int y, ShipType shipType, boolean orientacao) {
        if (player == null) {
            return false;
        }

        Ship navio = ShipFactory.createShip(shipType);

        player.PositionPlayerShip(navio, x, y, orientacao);

        return true;
    }

    public boolean Attack(Player playerAttacking, Player playerDefending, int coordinateX, int coordinateY) 
    {
        if (playerAttacking == null || playerDefending == null) 
        {
            return false;
        }

        GameBoard defenseMap = playerDefending.GetTabuleiroNavios();
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();

        boolean result = defenseMap.shotDefense(coordinateX, coordinateY);

        attackMap.shotAttack(coordinateX, coordinateY, result);

        return true;
    }

    public boolean ValidateAttack(Player playerAttacking, int coordinateX, int coordinateY) 
    {
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();
        int position = attackMap.getValue(coordinateX, coordinateY);
        if (position == 2 || position == 3) 
        {
            return false;
        }
        return true;
    }
}
