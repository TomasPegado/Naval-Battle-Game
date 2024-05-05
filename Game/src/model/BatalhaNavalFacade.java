package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class BatalhaNavalFacade {
    private static BatalhaNavalFacade instance;
    private GameBoard tabuleiro;
    private List<Player> jogadores;

    private BatalhaNavalFacade() {
        startGame("Teste1", "Teste2");
        // Inicialização do tabuleiro e jogadores
    }

    public static synchronized BatalhaNavalFacade getInstance() {
        if (instance == null) {
            instance = new BatalhaNavalFacade();
        }
        return instance;
    }

    public void startGame(String player1, String player2) {
        // Criar os jogadores
        Player jogador1 = new Player(player1, 0);
        Player jogador2 = new Player(player2, 1);

        jogadores.add(jogador1);
        jogadores.add(jogador2);
    }

    public boolean PositionShip(Player player, int x, int y, ShipType shipType, boolean orientacao) {
        if (player == null) {
            return false;
        }

        Ship navio = ShipFactory.createShip(shipType);

        player.PositionPlayerShip(navio, x, y, orientacao);

        return true;
    }

    public boolean fire(Player playerAttacking, Player playerDefending, int coordinateX, int coordinateY) {
        // O jogador especificado realiza um ataque nas coordenadas fornecidas

        if (playerAttacking == null || playerDefending == null) {
            return false;
        }

        GameBoard defenseMap = playerDefending.GetTabuleiroNavios();
        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();

        boolean result = defenseMap.shotDefense(coordinateX, coordinateY);

        attackMap.shotAttack(coordinateX, coordinateY, result);

        return false;
    }

    public boolean endGame(List<Player> players) {
        if (players.stream().anyMatch(x -> x.GetTotalShipsLeft() == 0)) {
            return true;
        }
        return false;
    }

    public boolean validateAttack(Player playerAttacking, int coordinateX, int coordinateY) {
        // Se ja foi atacado não pode atacar novamente -> return false
        // Se não foi atacado ainda pode atacar -> return true

        GameBoard attackMap = playerAttacking.GetTabuleiroAtaques();
        int position = attackMap.getValue(coordinateX, coordinateY);
        if (position == 2 || position == 3) {
            return false;
        }
        return true;
    }

    public void saveGame(String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(jogadores.get(0).GetTabuleiroAtaques());
            oos.writeObject(jogadores.get(0).GetTabuleiroNavios());
            oos.writeObject(jogadores.get(1).GetTabuleiroAtaques());
            oos.writeObject(jogadores.get(1).GetTabuleiroNavios());

            System.out.println("Game saved successfully to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
        }
    }

    public void loadGame(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            GameBoard player1AttackMap = (GameBoard) ois.readObject();
            GameBoard player1DefenseMap = (GameBoard) ois.readObject();
            GameBoard player2AttackMap = (GameBoard) ois.readObject();
            GameBoard player2DefenseMap = (GameBoard) ois.readObject();

            jogadores.get(0).setTabuleiroAtaques(player1AttackMap);
            jogadores.get(0).setTabuleiroNavios(player1DefenseMap);
            jogadores.get(1).setTabuleiroAtaques(player2AttackMap);
            jogadores.get(1).setTabuleiroNavios(player2DefenseMap);

            System.out.println("Game loaded successfully from " + filePath);
        } catch (IOException e) {
            System.err.println("Error loading game: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error finding class during game load: " + e.getMessage());
        }
    }

}
