package view;

public class ShotEvent {

    private int boardX;
    private int boardY;

    protected ShotEvent(int boardX, int boardY) {

        this.boardX = boardX;
        this.boardY = boardY;
    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

}
