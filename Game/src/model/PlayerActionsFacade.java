package model;

import java.util.List;

public class PlayerActionsFacade implements IPlayerActionsFacade {
    private static IPlayerActionsFacade instance;

    public static synchronized IPlayerActionsFacade getInstance() {
        if (instance == null) {
            instance = new PlayerActionsFacade();
        }
        return instance;
    }

    @Override
    public void updateShipPosition(Player player, int x, char y, int orientacao, int newX, char newY) {
        if (player != null) {

            for (Ship ship : player.getShips()) {
                List<PositionPair> positions = ship.getPositionsList();
                System.out.println(positions);
                System.out.println("Chegou aqui 1");
                for (PositionPair coord : positions) {
                    System.out.println("Model Facade: x = " + x + " y = " + y + " , coordX = " + coord.getCoordenadaX()
                            + " coordY = " + coord.getCoordenadaY());
                    if (coord.getCoordenadaX() == x && coord.getCoordenadaY() == y) {

                        System.out.println("Chegou aqui 2");
                        player.PositionPlayerShip(ship, newX, newY, orientacao);

                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean PositionShip(Player player, int x, char y, int shipSize, int orientacao) {
        if (player == null) {
            return false;
        }
        Ship navio = ShipFactory.createShipSize(shipSize);

        return player.PositionPlayerShip(navio, x, y, orientacao);
    }

    @Override
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
        playerAttacking.setShotsFired(playerAttacking.getShotsFired() + 1);
        playerDefending.calculateShipsLeft();

        return true;
    }

    @Override
    public boolean ValidateAttack(Player playerAttacking, int coordinateX, char coordinateY) {
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();
        PositionPair coordenada = attackMap.getCoordinate(coordinateX, coordinateY);

        return !coordenada.getHit();
    }

    @Override
    public List<Ship> getPlayerShips(Player player) {
        return player.getShips();
    }
}
