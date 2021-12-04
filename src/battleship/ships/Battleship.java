package battleship.ships;

public class Battleship extends Ship {

    public Battleship() {
        length = 4;
        health = length;
        hit = new boolean[length];
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public String getShipType() {
        return "Battleship";
    }
}
