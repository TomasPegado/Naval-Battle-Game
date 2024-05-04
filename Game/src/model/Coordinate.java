package model;

class Coordinate 
{
    private int x;
    private int y;

    Coordinate(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }

    int GetXCoordinate()
    {
        return x;
    }

    int GetYCoordinate()
    {
        return y;
    }
}
