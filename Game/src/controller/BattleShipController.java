package controller;

import javax.swing.*;
import model.Facades.GameFacade;
import model.Facades.PlayerActionsFacade;
import view.*;
import model.Entities.Player;
import model.ShipType;
import model.Interfaces.IGameListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleShipController implements IGameListener {
    private final GameFacade gameFacade;
    private final PlayerActionsFacade playerActionsFacade;
    private ShipType selectedWeapon;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private GameFrame gameFrame;
    private WeaponsPanel weaponsPanel;

    public BattleShipController(
            GameFacade gameFacade,
            PlayerActionsFacade playerActionsFacade,
            String player1,
            String player2) {
        this.gameFacade = gameFacade;
        this.playerActionsFacade = playerActionsFacade;
        this.currentPlayerIndex = 0;

        gameFacade.startGame(player1, player2);
        this.currentPlayer = gameFacade.GetPlayers().get(currentPlayerIndex);

        this.weaponsPanel = new WeaponsPanel(); // Inicializa WeaponsPanel
        this.gameFrame = new GameFrame("Batalha Naval", weaponsPanel); // Passa WeaponsPanel para o GameFrame

        gameFacade.AddGameListener(this);
        gameFacade.AddGameListener(weaponsPanel); // Registra WeaponsPanel como ouvinte

        registerListeners();
    }

    private void registerListeners() {
        gameFrame.addPositioningListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandlePositioning();
            }
        });

        weaponsPanel.getPositionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HandleWeaponPositioning();
            }
        });

        // Registra listener para capturar cliques no tabuleiro
        gameFrame.addBoardClickListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                HandleBoardClick(e);
            }
        });
    }

    private void HandlePositioning() {
        int x = gameFrame.getCoordX();
        char yChar = gameFrame.getCoordY();
        int y = yChar - 'A';  // Converte 'A' para 0, 'B' para 1, etc.
        int shipIndex = gameFrame.getSelectedShipIndex();
        ShipType shipType;

        // Validar coordenadas
        if (x < 0 || x >= 15 || y < 0 || y >= 15) {
            System.out.println("Coordenadas inválidas. Tente novamente.");
            return;
        }

        shipType = mapShipIndexToShipType(shipIndex);

        boolean orientacao = true; // Pode ser ajustado conforme a orientação selecionada

        boolean success = playerActionsFacade.PositionShip(currentPlayer, x, yChar, shipType, orientacao);
        if (success) {
            System.out.println("Navio posicionado com sucesso!");
            gameFrame.decrementSelectedShipCount();
            gameFrame.updateBoard();
            gameFrame.getBoardPanel().addShip(x, y, shipType);
        } else {
            System.out.println("Falha ao posicionar o navio. Posição já está ocupada.");
        }
    }

    private void HandleWeaponPositioning() {
        int x = Integer.parseInt(weaponsPanel.getCoordXField().getText());
        char yChar = weaponsPanel.getCoordYField().getText().charAt(0);
        int shipIndex = weaponsPanel.getShipSelector().getSelectedIndex();
        ShipType shipType = mapShipIndexToShipType(shipIndex);

        boolean orientacao = true; // Pode ser ajustado conforme a orientação selecionada

        boolean success = playerActionsFacade.PositionShip(currentPlayer, x, yChar, shipType, orientacao);
        if (success) {
            System.out.println("Navio posicionado com sucesso!");
            weaponsPanel.decrementShipCount(shipIndex);
            gameFrame.updateBoard();
            gameFrame.getBoardPanel().addShip(x, yChar - 'A', shipType);
        } else {
            System.out.println("Falha ao posicionar o navio. Posição já está ocupada.");
        }
    }

    private void HandleBoardClick(MouseEvent e) {
        BoardPanel boardPanel = (BoardPanel) e.getSource();
        int cellSize = boardPanel.getGridSize();
        int margin = boardPanel.getMargin();

        int x = (e.getX() - margin) / cellSize;
        int y = (e.getY() - margin) / cellSize;

        if (x >= 0 && x < boardPanel.getBoardWidth() && y >= 0 && y < boardPanel.getBoardHeight()) {
            char column = (char) ('A' + y);
            int row = x + 1;

            System.out.println("Clicked at: " + column + row);

            boolean orientacao = true;
            ShipType shipType = ShipType.BATTLESHIP;

            boolean success = playerActionsFacade.PositionShip(currentPlayer, row, column, shipType, orientacao);
            if (success) {
                System.out.println("Navio posicionado com sucesso!");
                gameFrame.updateBoard();
            } else {
                System.out.println("Falha ao posicionar o navio.");
            }
        }
    }

    private ShipType mapShipIndexToShipType(int shipIndex) {
        switch (shipIndex) {
            case 0:
                return ShipType.CRUISER;
            case 1:
                return ShipType.SUBMARINE;
            case 2:
                return ShipType.BATTLESHIP;
            case 3:
                return ShipType.DESTROYER;
            case 4:
                return ShipType.HYDROPLANES;
            default:
                return ShipType.SUBMARINE;
        }
    }

    @Override
    public void OnGameStateChanged() {
        currentPlayer = gameFacade.GetCurrentPlayer(currentPlayerIndex);
        gameFrame.updateBoard();
    }

    public static void main(String[] args) {
        // Cria as facades e o controlador
        GameFacade gameFacade = GameFacade.getInstance();
        PlayerActionsFacade playerActionsFacade = PlayerActionsFacade.getInstance();
        BattleShipController controller = new BattleShipController(gameFacade, playerActionsFacade, "Player 1", "Player 2");
    }
}
