package model;

public class SingletonGame {
    // Instância estática privada
    private static SingletonGame instance;

    // Controller e Model do jogo
    private GameController gameController;

    // Construtor privado
    private SingletonGame() {
        gameController = GameController.getInstance();
    }

    // Método estático para obter a instância
    public static SingletonGame getInstance() {
        if (instance == null) {
            instance = new SingletonGame();
        }
        return instance;
    }

    // Métodos para delegar chamadas ao GameController
    public void startGame() {
        gameController.startGame();
    }

    public void loadGame(String filePath) {
        gameController.loadGame(filePath);
    }

    public void saveGame(String filePath) {
        gameController.saveGame(filePath);
    }

    public ShotResult placeShip(ShipType type, String start, Direction direction) {
        return gameController.placeShip(type, start, direction);
    }

    public ShotResult fire(String coordinate) {
        return gameController.fire(coordinate);
    }

    // Outros métodos relacionados ao estado e operações do jogo podem ser
    // adicionados aqui
}
