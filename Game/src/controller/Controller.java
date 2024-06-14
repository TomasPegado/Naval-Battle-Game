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
    private BoardPanel boardPanel;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;
    private int boardX;
    private int boardY;

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

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public void setBoardX(int boardX) {
        this.boardX = boardX;
    }

    public void setBoardY(int boardY) {
        this.boardY = boardY;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ShipPlacementEvent) {
            ShipPlacementEvent event = (ShipPlacementEvent) arg;
            setBoardX(event.getBoardX());
            setBoardY(event.getBoardY());

            if (viewActionsFacade.getBoardShips(boardPanel).contains(event.getSelectedShip())) {
                int currentPositionX = viewActionsFacade.getCurrentPositionX(boardPanel);
                char currentPositionY = (char) (viewActionsFacade.getCurrentPositionY(boardPanel) + 65);

                playerActionsFacade.updateShipPosition(currentPlayer, currentPositionX,
                        currentPositionY, true,
                        event.getBoardX(), (char) (event.getBoardY() + 65));

            } else {
                playerActionsFacade.PositionShip(currentPlayer, event.getBoardX(), (char) (event.getBoardY() + 65),
                        viewActionsFacade.getShipSize(event.getSelectedShip()), true);
            }
            // playerActionsFacade.PositionShip(currentPlayer, event.getBoardX(), (char)
            // (event.getBoardY() + 65),
            // viewActionsFacade.getShipSize(event.getSelectedShip()), true);

        } else if (o instanceof GameBoard) {
            if (arg instanceof String) {
                String eventDescription = (String) arg;
                System.out.println("Controller observed event from GameBoard: " + eventDescription);

                // Handle specific events from GameBoard if needed
                if (eventDescription.startsWith("Ship positioned at")) {
                    viewActionsFacade.placeShip(boardPanel, getBoardX(), getBoardY());
                    System.out.println(playerActionsFacade.getPlayerShips(currentPlayer));
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
            controller.setBoardPanel(boardPanel);
            frame.setVisible(true);
        });
    }
}
