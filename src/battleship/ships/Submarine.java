package battleship.ships;

public class Submarine extends Ship {

    public Submarine() {
        length = 1;
        health = length;
        hit = new boolean[length];
    }

    @Override
    public String toString() {
        return "S";
    }

    @Override
    public String getShipType() {
        return "Submarine";
    }
}
