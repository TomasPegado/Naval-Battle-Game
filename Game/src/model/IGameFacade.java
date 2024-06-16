package model;

import java.util.List;

import controller.Controller;

public interface IGameFacade {

    void startGame(String player1, String player2);

    void restartGame(Controller controller);

    boolean endGame(List<Player> players);

    void saveGame(String filePath);

    void loadGame(String filePath);

    List<Player> getJogadores();

}