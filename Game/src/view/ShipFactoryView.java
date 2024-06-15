package view;

public class ShipFactoryView {

    protected static ShipView creatShipView(int size) {

        switch (size) {
            case 5:
                return new BattleShipView(size);
            case 4:
                return new CruiserView(size);
            case 3:
                return new HydroplaneView(size);
            case 2:
                return new DestroyerView(size);
            case 1:
                return new SubmarineView(size);
            default:
                throw new IllegalArgumentException("Unknown ship type: " + size);
        }
    }
}
