package model.Entities;

import java.util.List;

import model.PositionPair;
import model.ShipType;

import java.util.ArrayList;

public class Ship {
    private ShipType type;
    private int size;
    private List<PositionPair> positionsList;
    private boolean active = true; // flag para saber se esta afundado ou não
    private int hits = 0; // contador de tiros recebidos

    protected Ship(ShipType type) {
        this.type = type;
        this.size = type.GetSize();
        this.positionsList = new ArrayList<>();
    }

    ShipType GetType() {
        return type;
    }

    protected List<PositionPair> getPositionsList() {
        return positionsList;
    }

    protected boolean is_Active() {
        return active;
    }

    protected int getHits() {
        return hits;
    }

    int GetSize() {
        return size;
    }

    void addPosition(PositionPair coordenada) {
        positionsList.add(coordenada);
    }

    protected void got_Hit() {

        hits += 1;
        if (hits == size) {
            this.active = false;

        }

    }
}
