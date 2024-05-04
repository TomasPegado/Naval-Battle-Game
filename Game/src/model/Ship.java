package model;

class Ship 
{
    private ShipType type;
    private int size;

    Ship(ShipType type) 
    {
        this.type = type;
        this.size = type.GetSize();
    }

    ShipType GetType() 
    {
        return type;
    }

    int GetSize() 
    {
        return size;
    }
}
