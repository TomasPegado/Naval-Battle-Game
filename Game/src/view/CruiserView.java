package view;
import java.awt.*;
public class CruiserView extends ShipView {

    
    public CruiserView(int x, int y) {
        super(x, y);
        setShipSize(5);
    } 

   int x = this.panelPositionX;
   int y = this.panelPositionY;

   void paintShip(Graphics2D g2D){

        g2D.setColor(Color.ORANGE);
        g2D.fillRect(x,y, 20, 20);

        g2D.fillRect(x+20, y, 20, 20);
        g2D.fillRect(x+40, y, 20, 20);
        g2D.fillRect(x+60, y, 20, 20);

   }
}
