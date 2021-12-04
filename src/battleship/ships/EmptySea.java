package battleship.ships;

import battleship.Ocean;

public class EmptySea extends Ship {

    public EmptySea() {
        length = 1;
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public String getShipType() {
        return "Empty";
    }

    @Override
    public boolean shootAt(int row, int column, Ocean ocean) {
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }
}
