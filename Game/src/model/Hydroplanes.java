package model;

import java.io.Serializable;

public class Hydroplanes extends Ship  implements Serializable{
    public Hydroplanes() {
        super(ShipType.HYDROPLANES);
    }
}
