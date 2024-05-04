package model;

import java.util.List;

public class BatalhaNavalFacade 
{
    private static BatalhaNavalFacade instance;
    private GameBoard tabuleiro;
    private List<Player> jogadores;

    private BatalhaNavalFacade() 
    {
        StartGame("Teste1", "Teste2");
        // Inicialização do tabuleiro e jogadores
    }

    public static synchronized BatalhaNavalFacade getInstance() 
    {
        if (instance == null)
        {
            instance = new BatalhaNavalFacade();
        }
        return instance;
    }

    public void StartGame(String player1, String player2) 
    {
        // Criar os jogadores
        Player jogador1 = new Player(player1, 0);
        Player jogador2 = new Player(player2, 1);
    } 

    public boolean PositionShip(Player player, int x, int y, ShipType shipType, boolean orientacao) 
    {
        if (player == null) 
        {
            return false;
        }

        Ship navio = new Ship(shipType);

        player.PositionPlayerShip(navio, x, y, orientacao);

        return true;
    }

    public boolean Atack(Player playerAttacking, Player playerDefending, int coordinateX, int coordinateY) 
    {
        // O jogador especificado realiza um ataque nas coordenadas fornecidas
        return false;
    }

    public boolean EndGame(List<Player> players) 
    {
        if(players.stream().anyMatch(x -> x.GetTotalShipsLeft() == 0)){
            return true;
        }
        return false;
    }

    public boolean ValidateAttack(Player playerAttacking, Player playerDefending, int coordinateX, int coordinateY) 
    {
        //Se ja foi atacado não pode atacar novamente -> return false
        //Se não foi atacado ainda pode atacar -> return true
        return false;
    }
}
