package model;

class Ship 
{
    private ShipType type;
    private int size;

    Ship(ShipType type) 
    {
        this.type = type;
        this.size = type.getSize();
    }

    ShipType getType() 
    {
        return type;
    }

    int getSize() 
    {
        return size;
    }
}
