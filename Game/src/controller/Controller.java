package controller;

import javax.swing.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import model.GameBoard;
import model.GameFacade;
import model.PlayerActionsFacade;
import view.*;
import model.Player;

@SuppressWarnings("deprecation")
public class Controller implements Observer {
    private final GameFacade gameFacade;
    private final PlayerActionsFacade playerActionsFacade;
    private final ViewActionsFacade viewActionsFacade;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;

    public Controller(GameFacade gameFacade, PlayerActionsFacade playerActionsFacade, String player1, String player2,
            ViewActionsFacade viewActionsFacade) {
        this.gameFacade = gameFacade;
        this.playerActionsFacade = playerActionsFacade;
        this.viewActionsFacade = viewActionsFacade;

        gameFacade.startGame(player1, player2);
        this.currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);

        // Observe the game boards of the players
        for (Player player : gameFacade.getJogadores()) {
            player.GetTabuleiroNavios().addObserver(this);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ShipPlacementEvent) {
            ShipPlacementEvent event = (ShipPlacementEvent) arg;
            System.out.println("Controller observed event: Ship " + event.getSelectedShip() + " placed at ("
                    + (event.getBoardX() + 1) + ", " + (char) (event.getBoardY() + 65) + ")");

            playerActionsFacade.PositionShip(currentPlayer, event.getBoardX(), (char) (event.getBoardY() + 65),
                    event.getSelectedShip().getShipSize(), true);
        } else if (o instanceof GameBoard) {
            if (arg instanceof String) {
                String eventDescription = (String) arg;
                System.out.println("Controller observed event from GameBoard: " + eventDescription);

                // Handle specific events from GameBoard if needed
                if (eventDescription.startsWith("Ship positioned at")) {
                    // Handle ship positioning event
                } else if (eventDescription.startsWith("Shot at")) {
                    // Handle shot event
                } else if (eventDescription.startsWith("Shot hit at")) {
                    // Handle shot hit event
                }
            }
        }
    }

    public static void main(String[] args) {

        GameFacade gameFacade = GameFacade.getInstance();
        PlayerActionsFacade playerActionsFacade = PlayerActionsFacade.getInstance();
        ViewActionsFacade viewActionsFacade = ViewActionsFacade.getInstance();

        Controller controller = new Controller(gameFacade, playerActionsFacade, "Player1", "Player2",
                viewActionsFacade);

        SwingUtilities.invokeLater(() -> {
            GameFrame frame = GameFrame.getInstance();
            BoardPanel boardPanel = frame.getPositionPanel().getBoardPanel();
            boardPanel.addObserver(controller); // Register observer after the frame creation
            frame.setVisible(true);
        });
    }
}
