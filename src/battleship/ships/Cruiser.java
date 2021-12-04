package battleship.ships;

public class Cruiser extends Ship {

    public Cruiser() {
        length = 3;
        health = length;
        hit = new boolean[length];
    }

    @Override
    public String toString() {
        return "C";
    }

    @Override
    public String getShipType() {
        return "Cruiser";
    }
}
