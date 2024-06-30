package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;
import model.GameFacade;
import model.IGameFacade;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameFrame extends JFrame {
    private static GameFrame instance;
    private PositionPanel positioning;
    private AttackPanel attacking;
    private ObservableHelper observableHelper; // Instância de Observable
    private Controller controller;
    private IGameFacade gameFacade; // Add GameFacade reference
    private int currentPlayerIndex;

    public static synchronized GameFrame getInstance(Controller controller) {
        if (instance == null) {
            instance = new GameFrame("Batalha Naval", controller);
        }
        return instance;
    }
    

    public GameFrame(String title, Controller controller) {
        this.setTitle(title);
        this.controller = controller; // Inicializa o controlador
        positioning = new PositionPanel();
        attacking = new AttackPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        observableHelper = new ObservableHelper(); // Inicializa a instância de Observable
    
        this.add(positioning);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(false);
    
        setJMenuBar(createMenuBar());
    }
    
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save Game");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Save files (*.sav)", "sav"));
                int option = fileChooser.showSaveDialog(GameFrame.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".sav")) {
                        file = new File(file.getAbsolutePath() + ".sav");
                    }
                    controller.saveGame(file.getAbsolutePath());
                }
            }
        });

        JMenuItem loadItem = new JMenuItem("Load Game");
        loadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Save files (*.sav)", "sav"));
                int option = fileChooser.showOpenDialog(GameFrame.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    controller.loadGame(file.getAbsolutePath());
                }
            }
        });

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    public void loadGame(String filePath) {
        gameFacade = controller.GetGameFacade();
        currentPlayerIndex = controller.GetCurrentPlayerIndex();
    
        positioning.getBoardPanel().updateBoard(gameFacade.getJogadores().get(0).GetTabuleiroNavios());
        positioning.getBoardPanel().updateBoard(gameFacade.getJogadores().get(1).GetTabuleiroNavios());

        updatePlayerNames(gameFacade.getJogadores().get(0).getName(), gameFacade.getJogadores().get(1).getName());
    
        setCurrentPlayer(gameFacade.getJogadores().get(currentPlayerIndex).getName());
    
        if (gameFacade.getIsGameStarted()) {
            showAttackPanel();
        } else {
            showPositionPanel();
        }
    }

    public PositionPanel getPositionPanel() {
        return positioning;
    }

    public AttackPanel getAttackingPanel() {
        return attacking;
    }
    
    public Controller getController() {
        return controller;
    }
    
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void showGameOverDialog(String message) {
        final JOptionPane optionPane = new JOptionPane(
                message,
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.DEFAULT_OPTION);

        final JDialog dialog = optionPane.createDialog("Game Over");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        optionPane.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                String prop = e.getPropertyName();

                if (dialog.isVisible()
                        && (e.getSource() == optionPane)
                        && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                    // O usuário pressionou "OK"
                    dialog.setVisible(false);

                    // Exibir o showConfirmDialog
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println("User wants to play again.");
                        // Lógica para reiniciar o jogo
                        SwingUtilities.invokeLater(() -> resetGame());
                    } else {
                        System.out.println("User does not want to play again.");
                        // Lógica para sair ou encerrar o jogo
                        System.exit(0);
                    }
                }
            }
        });

        dialog.setVisible(true);
    }

    // Método para trocar o painel de posicionamento pelo painel de ataque
    public void switchToAttackPanel() {
        this.positioning.remove(positioning.getWeaponsPanel());
        this.positioning.remove(positioning.getBoardPanel());
        this.remove(positioning); // Remove o painel de posicionamento
        this.add(attacking); // Adiciona o painel de ataque
        this.revalidate(); // Atualiza o layout do contêiner
        this.repaint(); // Redesenha o contêiner
        this.pack(); // Ajusta o tamanho da janela para o novo layout
    }

    // Método para resetar o jogo
    @SuppressWarnings("deprecation")
    private void resetGame() {
        // Lógica para reiniciar o jogo
        // Pode incluir reinicializar painéis, variáveis de estado, etc.
        for (BoardPanel attack : attacking.getAttackBoards()) {
            attacking.remove(attack);
        }
        this.remove(attacking);
        positioning = new PositionPanel();
        attacking = new AttackPanel();

        this.add(positioning);
        this.revalidate();
        this.repaint();
        this.pack();

        observableHelper.setChanged();
        observableHelper.notifyObservers("Game Restarted");
    }

    // Implement the missing methods
    private void updatePlayerNames(String player1Name, String player2Name) {
        // Update the UI elements that display player names
        // For example, if you have labels named player1Label and player2Label:
        // player1Label.setText(player1Name);
        // player2Label.setText(player2Name);
    }

    private void setCurrentPlayer(String playerName) {
        // Update the UI element that displays the current player
        // For example, if you have a label named currentPlayerLabel:
        // currentPlayerLabel.setText("Current Player: " + playerName);
    }

    private void showAttackPanel() {
        // Make the attacking panel visible and hide the positioning panel
        attacking.setVisible(true);
        positioning.setVisible(false);
    }

    private void showPositionPanel() {
        // Make the positioning panel visible and hide the attacking panel
        positioning.setVisible(true);
        attacking.setVisible(false);
    }

    @SuppressWarnings("deprecation")
    public void addObserver(java.util.Observer observer) {
        observableHelper.addObserver(observer);
    }
}