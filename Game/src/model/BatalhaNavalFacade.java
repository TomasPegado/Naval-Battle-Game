package model;

import java.util.List;

public class BatalhaNavalFacade 
{
    private static BatalhaNavalFacade instance;
    private GameBoard tabuleiro;
    private List<Player> jogadores;

    private BatalhaNavalFacade() 
    {
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

    public void StartGame(int tamanhoTabuleiro, List<String> nomesJogadores) 
    {
        // Criar os jogadores
        Player jogador1 = new Player(nomesJogadores.get(0), 0);
        Player jogador2 = new Player(nomesJogadores.get(1), 1);
    } 

    public boolean PositionShip(Player player, int x, int y, ShipType shipType, boolean orientacao) 
    {
        if (player == null) {
            return false; // Jogador não encontrado
        }

        Ship navio = new Ship(shipType);

        // Posicionar o navio no tabuleiro de navios do jogador
        player.getTabuleiroNavios().inserirNavio(x, y, navio, orientacao);

        return true;
    }

    public boolean Atack(String nomeJogador, int x, int y) 
    {
        // O jogador especificado realiza um ataque nas coordenadas fornecidas
        return false;
    }

    public boolean EndGame() 
    {
        // Verifica se o jogo acabou
        return false;
    }

    public boolean ValidateAttack(Player playerAttackins, Player playerDefending, Coordinate coordinate) 
    {
        //Se ja foi atacado não pode atacar novamente -> return false
        //Se não foi atacado ainda pode atacar -> return true
        return false;
    }
}
