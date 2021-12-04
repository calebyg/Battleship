package battleship.ships;

public class Sink extends Ship {

    public Sink() {
        length = 1;
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public String getShipType() {
        return "Sunken Ship";
    }
}
