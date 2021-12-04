package battleship.ships;

import battleship.MyPoint;
import battleship.Ocean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ship {
    int bowRow;
    int bowColumn;
    int length;
    int health;
    boolean horizontal;

    ArrayList<MyPoint> shipPlacement = new ArrayList<>();

    Map<MyPoint, Boolean> healthPoints = new HashMap<>();

    boolean[] hit = new boolean[4];

    // Getters
    public int getLength() {
        return length;
    }

    public int getHealth() {
        return health;
    }

    public String getShipType() {
        return "";
    }

    public int getBowRow() {
        return bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    // Setters
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        setHorizontal(horizontal);

        // Preliminary check

        if (horizontal) {
            setBowColumn(column);
            // Out of boundaries
            if (getBowColumn() + getLength() > 10) {
                return false;
            }
        } else {
            setBowRow(row);
            // Out of boundaries
            if (getBowRow() + getLength() > 10) {
                return false;
            }
        }

        // Check if adjacent to any other ships

        int topRowBound = (row == 0) ? 0 : 1;
        int bottomRowBound = (row == 9 || (!isHorizontal() && row + getLength() == 10)) ? 0 : 1;
        int leftColumnBound = (column == 0) ? 0 : 1;
        int rightColumnBound = (column == 9 || (isHorizontal() && column + getLength() == 10)) ? 0 : 1;

        int col_start = column - leftColumnBound;
        int col_end = isHorizontal() ? (col_start + getLength() + rightColumnBound) : col_start + rightColumnBound + 1;
        int row_start = row - topRowBound;
        int row_end = isHorizontal() ? (row + bottomRowBound) : (row_start + getLength() + bottomRowBound);

        for (int r = row_start; r <= row_end; r++) {
            for (int c = col_start; c <= col_end; c++) {
                if (ocean.isOccupied(r, c)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

        if (horizontal) {
            for (int c = column; c < column + getLength(); c++) {
                ocean.getShipArray()[row][c] = this;
                getShipPlacement().add(new MyPoint(row, c));
            }
        } else {
            for (int r = row; r < row + getLength(); r++) {
                ocean.getShipArray()[r][column] = this;
                getShipPlacement().add(new MyPoint(r, column));
            }
        }

        for (MyPoint p : getShipPlacement()) {
            healthPoints.put(p, false);
        }
    }

    public ArrayList<MyPoint> getShipPlacement() {
        return shipPlacement;
    }

    public boolean shootAt(int row, int column, Ocean ocean) {
        MyPoint p = new MyPoint(row, column);
        // Located a ship and hit it
        if (getShipPlacement().contains(p) && !isSunk() && getHealthPoints().get(p) == false) {
            health--;
            healthPoints.put(p, true);
            if (health == 0) {
                System.out.println("You have sunken a ship!");
                ocean.setShipsSunk(ocean.getShipsSunk() + 1);
            } else {
                System.out.println("The ship at [" + row + ", " + column + "] has been hit!");
                System.out.println("Health: " + getHealth());
            }
            return true;
        } else {
            System.out.println("Cannot shoot!");
            return false;
        }
    }

    public Map<MyPoint, Boolean> getHealthPoints() {
        return healthPoints;
    }


    public boolean isSunk() {
        return health == 0;
    }
}
