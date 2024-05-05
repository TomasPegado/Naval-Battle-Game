// package model;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;

// // Implementa o padrão Façade.
// public class GameController {
// private static GameController instance;
// private GameBoard playerOneBoard;
// private GameBoard playerTwoBoard;

// private GameController() {
// playerOneBoard = new GameBoard();
// playerTwoBoard = new GameBoard();
// }

// public static GameController getInstance() {
// if (instance == null) {
// instance = new GameController();
// }
// return instance;
// }

// // Métodos de delegação para GameBoard, etc.
// public void startGame() {
// // Resetar ou inicializar os tabuleiros dos jogadores
// playerOneBoard = new GameBoard();
// playerTwoBoard = new GameBoard();
// // Adicional: configurar jogadores, turnos, etc.
// System.out.println("Game started. Boards are ready!");
// }

// public void loadGame(String filePath) {
// try (ObjectInputStream ois = new ObjectInputStream(new
// FileInputStream(filePath))) {
// // Assume que o estado do jogo foi serializado como objetos GameBoard
// playerOneBoard = (GameBoard) ois.readObject();
// playerTwoBoard = (GameBoard) ois.readObject();
// System.out.println("Game loaded successfully from " + filePath);
// } catch (IOException | ClassNotFoundException e) {
// System.err.println("Error loading game: " + e.getMessage());
// }

// }

// public void saveGame(String filePath) {
// try (ObjectOutputStream oos = new ObjectOutputStream(new
// FileOutputStream(filePath))) {
// oos.writeObject(playerOneBoard);
// oos.writeObject(playerTwoBoard);
// System.out.println("Game saved successfully to " + filePath);
// } catch (IOException e) {
// System.err.println("Error saving game: " + e.getMessage());
// }
// }

// public ShotResult placeShip(ShipType type, String start, Direction direction)
// {
// Ship ship = ShipFactory.createShip(type);
// boolean success = playerOneBoard.addShip(ship, start, direction);
// if (success) {
// System.out.println("Ship placed successfully at " + start + " heading " +
// direction);
// return new ShotResult(true, false, type);
// } else {
// System.out.println("Failed to place ship at " + start);
// return new ShotResult(false, false, type);
// }
// }

// public ShotResult fire(String coordinate) {
// ShotResult result = playerTwoBoard.shoot(coordinate);
// System.out.println("Shot fired at " + coordinate + ": " + result);
// return result;
// }

// }
