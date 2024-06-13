package view;

public class ViewActionsFacade {
    private static ViewActionsFacade instance;

    public static synchronized ViewActionsFacade getInstance() {
        if (instance == null) {
            instance = new ViewActionsFacade();
        }
        return instance;
    }

    public boolean placeShip(BoardPanel board, int x, int y) {

        if (board == null) {
            return false;
        }

        board.placeShip(x, y);
        return true;
    }

}
