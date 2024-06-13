package model;

public class PlayerActionsFacade {
    private static PlayerActionsFacade instance;

    public static synchronized PlayerActionsFacade getInstance() {
        if (instance == null) {
            instance = new PlayerActionsFacade();
        }
        return instance;
    }

    public boolean PositionShip(Player player, int x, char y, int shipSize, boolean orientacao) {
        if (player == null) {
            return false;
        }
        Ship navio = ShipFactory.createShipSize(shipSize);

        return player.PositionPlayerShip(navio, x, y, orientacao);
    }

    public boolean Attack(Player playerAttacking, Player playerDefending, int coordinateX, char coordinateY) {
        if (playerAttacking == null || playerDefending == null) {
            return false;
        }

        GameBoard defenseMap = playerDefending.GetTabuleiroNavios();
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();

        PositionPair coordenadaDefesa = defenseMap.getCoordinate(coordinateX, coordinateY);
        PositionPair coordenadaAtaque = attackMap.getCoordinate(coordinateX, coordinateY);

        boolean result = defenseMap.shotDefense(coordenadaDefesa);

        if (result) {

            attackMap.shotHit(coordenadaAtaque, coordenadaDefesa.getShip());
        }

        coordenadaAtaque.got_Hit();

        return true;
    }

    public boolean ValidateAttack(Player playerAttacking, int coordinateX, char coordinateY) {
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();
        PositionPair coordenada = attackMap.getCoordinate(coordinateX, coordinateY);

        return coordenada.getHit();
    }
}
