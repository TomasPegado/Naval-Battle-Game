package controller;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.GameFacade;
import model.PlayerActionsFacade;
import view.*;
import model.Player;
import model.ShipType;

@SuppressWarnings("deprecation")
public class Controller implements Observer {
    private final GameFacade gameFacade;
    private final PlayerActionsFacade playerActionsFacade;
    private ShipType selectedWeapon;
    private Player currentPlayer;
    private int currentPlayerIndex;

    public Controller(GameFacade gameFacade, PlayerActionsFacade playerActionsFacade, String player1, String player2) {
        this.gameFacade = gameFacade;
        this.playerActionsFacade = playerActionsFacade;

        gameFacade.startGame(player1, player2);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            System.out.println("Controller observed event: " + arg);
        }
    }

    public static void main(String[] args) {

        GameFacade gameFacade = GameFacade.getInstance();
        PlayerActionsFacade playerActionsFacade = PlayerActionsFacade.getInstance();

        Controller controller = new Controller(gameFacade, playerActionsFacade, "Player1", "Player2");

        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame("Batalha Naval");
            BoardPanel boardPanel = frame.getPositionPanel().getBoardPanel();
            boardPanel.addObserver(controller); // Register observer after the frame creation
            frame.setVisible(true);
        });
    }
}
