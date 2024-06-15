package controller;

import javax.swing.*;
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
    private GameFrame frame;
    private BoardPanel boardPanel;
    private PositionPanel positionPanel;
    private int currentPlayerIndex = 0;
    private Player currentPlayer;
    private int boardX;
    private int boardY;
    private boolean shipOrientation;

    public Controller(GameFacade gameFacade, PlayerActionsFacade playerActionsFacade, String player1, String player2,
            ViewActionsFacade viewActionsFacade) {
        this.gameFacade = gameFacade;
        this.playerActionsFacade = playerActionsFacade;
        this.viewActionsFacade = viewActionsFacade;

        gameFacade.startGame(player1, player2);
        this.currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);

        // Observe the game boards of the players
        for (Player player : gameFacade.getJogadores()) {
            player.addObserver(this);
            player.GetTabuleiroNavios().addObserver(this);
        }
    }

    public void setFrame(GameFrame frame) {
        this.frame = frame;
    }

    public void setBoardPanel(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    public void setPositionPanel(PositionPanel positionPanel) {
        this.positionPanel = positionPanel;
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
            shipOrientation = event.isOrientation();
            System.out.println("Orientacao: " + shipOrientation);

            if (viewActionsFacade.getBoardShips(boardPanel).contains(event.getSelectedShip())) {
                int currentPositionX = viewActionsFacade.getCurrentPositionX(boardPanel);
                char currentPositionY = (char) (viewActionsFacade.getCurrentPositionY(boardPanel) + 65);

                playerActionsFacade.updateShipPosition(currentPlayer, currentPositionX,
                        currentPositionY, shipOrientation,
                        event.getBoardX(), (char) (event.getBoardY() + 65));

            } else {

                playerActionsFacade.PositionShip(currentPlayer, event.getBoardX(), (char) (event.getBoardY() + 65),
                        viewActionsFacade.getShipSize(event.getSelectedShip()), shipOrientation);
            }

        } else if (o instanceof GameBoard) {
            if (arg instanceof String) {
                String eventDescription = (String) arg;
                System.out.println("Controller observed event from GameBoard: " + eventDescription);

                // Handle specific events from GameBoard if needed
                if (eventDescription.startsWith("Ship positioned at")) {
                    viewActionsFacade.placeShip(boardPanel, getBoardX(), getBoardY(), shipOrientation);
                    System.out.println(playerActionsFacade.getPlayerShips(currentPlayer));
                } else if (eventDescription.startsWith("Shot at")) {
                    // Handle shot event
                } else if (eventDescription.startsWith("Shot hit at")) {
                    // Handle shot hit event
                }
            }
        } else if (o instanceof Player) {
            if (arg instanceof String) {
                String playerEventDescription = (String) arg;
                System.out.println("Controller observed event from Player: " + playerEventDescription);

                if (playerEventDescription.startsWith("All Ships Positioned: ")) {
                    if (currentPlayerIndex == 0) {
                        viewActionsFacade.setVisibleNextPlayerButton(positionPanel);

                    } else {
                        viewActionsFacade.setVisibleStartGameButton(positionPanel);
                    }
                }
            }
        } else if (o instanceof ObservableHelper) {

            if (arg instanceof String) {
                String eventDescription = (String) arg;

                if (eventDescription.startsWith("Next Player Positioning")) {
                    currentPlayerIndex += 1;
                    currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);
                    this.setBoardPanel(frame.getPositionPanel().getBoardPanel());
                    this.boardPanel.addObserver(this);
                } else if (eventDescription.startsWith("Positioning Finished")) {
                    currentPlayerIndex = 0;
                    currentPlayer = gameFacade.getJogadores().get(currentPlayerIndex);
                    frame.switchToAttackPanel();
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
            controller.setFrame(frame);
            PositionPanel positionPanel = frame.getPositionPanel();
            positionPanel.addObserver(controller);
            BoardPanel boardPanel = frame.getPositionPanel().getBoardPanel();
            boardPanel.addObserver(controller); // Register observer after the frame creation
            controller.setPositionPanel(positionPanel);
            controller.setBoardPanel(boardPanel);
            frame.setVisible(true);
        });
    }
}
