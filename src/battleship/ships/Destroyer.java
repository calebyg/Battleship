package battleship.ships;

public class Destroyer extends Ship {

    public Destroyer() {
        length = 2;
        health = length;
        hit = new boolean[length];
    }

    @Override
    public String toString() {
        return "D";
    }

    @Override
    public String getShipType() {
        return "Destroyer";
    }
}
