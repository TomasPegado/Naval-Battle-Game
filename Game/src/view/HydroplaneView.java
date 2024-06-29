package view;

import java.awt.*;
import java.util.List;

public class HydroplaneView extends ShipView {

    public HydroplaneView(int x, int y) {
        super(x, y);
        setShipSize(3);
        setColor("#326B00");
    }

    protected HydroplaneView(int size) {
        super(size);
        setColor("#326B00");
    }

    int x = this.panelPositionX;
    int y = this.panelPositionY;

    void paintShip(Graphics2D g2D) {

        if (this.selected) {
            g2D.setColor(Color.CYAN); // Alterar cor para indicar seleção
        } else {
            g2D.setColor(Color.decode(this.getColor()));
        }
        g2D.fillRect(x, y, 20, 20);

        g2D.fillRect(x + 20, y + 20, 20, 20);
        g2D.fillRect(x - 20, y + 20, 20, 20);

    }

    @Override
    protected boolean placeShip(BoardPanel gameBoard, int x, int y, int orientacao) {

        List<List<CoordinateView>> board = gameBoard.getBoard();
        this.addCoordinate(board, x, y);

        if (orientacao == 0) { // Posicionamento em 0° graus

            this.addCoordinate(board, x - 1, y + 1);
            this.addCoordinate(board, x + 1, y + 1);
            return true;

        } else if (orientacao == 1) {

            this.addCoordinate(board, x + 1, y + 1);
            this.addCoordinate(board, x + 1, y - 1);
            return true;

        } else if (orientacao == 2) {

            this.addCoordinate(board, x - 1, y - 1);
            this.addCoordinate(board, x + 1, y - 1);
            return true;

        } else if (orientacao == 3) {

            this.addCoordinate(board, x - 1, y + 1);
            this.addCoordinate(board, x - 1, y - 1);
            return true;

        }
        return false;
    }

    @Override
    protected void turnCoordinates(BoardPanel.Coordinates coord) {

        if (this.orientacao == 0) {
            coord.x = coord.x - 1;
            coord.y = coord.y + 1;
        } else if (this.orientacao == 1) {
            coord.x = coord.x + 1;
            coord.y = coord.y + 1;
        } else if (this.orientacao == 2) {
            coord.x = coord.x + 1;
            coord.y = coord.y - 1;
        } else if (this.orientacao == 3) {
            coord.x = coord.x - 1;
            coord.y = coord.y - 1;
        }

    }
}
