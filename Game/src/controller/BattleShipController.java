package controller;

import javax.swing.*;
import java.util.List;
import model.GameFacade;
import model.PlayerActionsFacade;
import view.*;
import model.Player;
import model.ShipType;

public class BattleShipController {
    private final GameFacade gameFacade;
    private final PlayerActionsFacade playerActionsFacade;
    private final BattleShipView view;
    private ShipType selectedWeapon;
    private Player currentPlayer;
    private int currentPlayerIndex;

    public BattleShipController(GameFacade gameFacade, PlayerActionsFacade playerActionsFacade, BattleShipView view,
            String player1, String player2) {
        this.gameFacade = gameFacade;
        this.playerActionsFacade = playerActionsFacade;
        this.view = view;
        this.view.setController(this);
        this.currentPlayerIndex = 0; // Inicia com o jogador 1
        startGame(player1, player2);
        this.currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);
        this.view.updateInstructions("J1, selecione uma arma na lista.");
    }

    public void selectWeapon(ShipType weapon) {
        this.selectedWeapon = weapon;
        view.updateInstructions("Posicione sua " + weapon.name() + " no tabuleiro.");
    }

    public void placeWeapon(int x, int y) {
        if (selectedWeapon == null) {
            view.updateInstructions("Selecione uma arma primeiro.");
            return;
        }

        boolean success = playerActionsFacade.PositionShip(currentPlayer, x, (char) (y + 'A'), selectedWeapon, true);
        if (success) {
            view.updateBoard(); // Atualiza a view para mostrar a nova posição do navio
            selectedWeapon = null;
            view.updateInstructions("Arma posicionada. Selecione a próxima arma.");
        } else {
            view.updateInstructions("Posicionamento inválido. Tente novamente.");
        }
    }

    public void boardReady() {
        currentPlayerIndex++;
        if (currentPlayerIndex < gameFacade.getJogadores().size()) {
            currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);
            view.updateInstructions("J" + (currentPlayerIndex + 1) + ", selecione uma arma na lista.");
            view.enableReadyButton(false);
        } else {
            // Todos os jogadores posicionaram suas armas, iniciar o jogo
            view.updateInstructions("Todos os jogadores posicionaram suas armas. Inicie o jogo!");
        }
    }

    // Exemplo de método para iniciar o jogo
    public void startGame(String player1Name, String player2Name) {
        gameFacade.startGame(player1Name, player2Name);
        view.updateBoard(); // Método fictício para atualizar a view com o novo estado do jogo
    }

    // Exemplo de método para posicionar um navio
    public boolean positionShip(Player player, int x, char y, ShipType shipType, boolean orientation) {
        boolean result = playerActionsFacade.PositionShip(player, x, y, shipType, orientation);
        view.updateBoard(); // Atualiza a view após posicionar o navio
        return result;
    }

    // Exemplo de método para realizar um ataque
    public boolean attack(Player attackingPlayer, Player defendingPlayer, int x, char y) {
        boolean result = playerActionsFacade.Attack(attackingPlayer, defendingPlayer, x, y);
        view.updateBoard(); // Atualiza a view após o ataque
        return result;
    }

    // Exemplo de método para salvar o jogo
    public void saveGame(String filePath) {
        gameFacade.saveGame(filePath);
    }

    // Exemplo de método para carregar o jogo
    public void loadGame(String filePath) {
        gameFacade.loadGame(filePath);
        view.updateBoard(); // Atualiza a view após carregar o jogo
    }

    public List<Player> getPlayers() {
        return gameFacade.getJogadores();
    }

    public static void main(String[] args) {

        // Inicializa o modelo
        GameFacade gameFacade = GameFacade.getInstance();
        PlayerActionsFacade playerActionsFacade = PlayerActionsFacade.getInstance();

        // Cria a view
        BattleShipView view = new BattleShipView();

        // Inicializa o controlador
        BattleShipController controller = new BattleShipController(gameFacade, playerActionsFacade, view, "Player1",
                "Player2");

        // Cria o Frame
        GameFrame frame = new GameFrame();
    }
}
