package battleship;

import battleship.ships.*;

import java.util.ArrayList;
import java.util.Random;

public class Ocean {
    private final int GRID_DIMENSIONS = 10;
    private final Ship[][] SHIP_GRID = new Ship[GRID_DIMENSIONS][GRID_DIMENSIONS];
    private final ArrayList<Ship> shipArrayList = new ArrayList<>();
    private int shipsSunk;

    public Ocean() {
        this.shipsSunk = 0;

        shipArrayList.add(new Battleship());

        shipArrayList.add(new Cruiser());
        shipArrayList.add(new Cruiser());

        shipArrayList.add(new Destroyer());
        shipArrayList.add(new Destroyer());
        shipArrayList.add(new Destroyer());

        shipArrayList.add(new Submarine());
        shipArrayList.add(new Submarine());
        shipArrayList.add(new Submarine());
        shipArrayList.add(new Submarine());

        for (int row = 0; row < GRID_DIMENSIONS; row++) {
            for (int col = 0; col < GRID_DIMENSIONS; col++) {
                SHIP_GRID[row][col] = new EmptySea();
            }
        }
    }

    public Ship[][] getShipArray() {
        return SHIP_GRID;
    }

    /**
     * Places all ten ships randomly on the initially empty ocean.
     */
    public void placeAllShipsRandomly() {

        Random random = new Random();

        for (Ship ship : shipArrayList) {

            int randomRow = random.nextInt(10);
            int randomColumn = random.nextInt(10);

            int randomHorizontal = random.nextInt(2);
            boolean horizontal = randomHorizontal == 0;

            boolean canPlace = ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this);

            while (!canPlace) {
                randomRow = random.nextInt(10);
                randomColumn = random.nextInt(10);
                randomHorizontal = random.nextInt(2);
                horizontal = randomHorizontal == 0;
                canPlace = ship.okToPlaceShipAt(randomRow, randomColumn, horizontal, this);
            }
            ship.placeShipAt(randomRow, randomColumn, horizontal, this);
        }

    }

    public void hit(int x, int y) {
        SHIP_GRID[x][y] = new Sink();
    }


    public void print() {
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row < GRID_DIMENSIONS; row++) {
            for (int col = 0; col < GRID_DIMENSIONS; col++) {
                System.out.print(SHIP_GRID[row][col] + " ");
            }
            System.out.print(" " + row);
            System.out.println();
        }
        System.out.println();
    }


    public boolean isOccupied(int row, int column) {
        return !(SHIP_GRID[row][column].getShipType().equals("Empty"));
    }

    public ArrayList<Ship> getShipArrayList() {
        return shipArrayList;
    }

    public boolean shootAt(int row, int column) {

        // Ship still afloat
        if (!SHIP_GRID[row][column].getShipType().equals("Empty")) {

            Ship ship = SHIP_GRID[row][column];
            if (!ship.isSunk()) {
                ship.shootAt(row, column, this);
                return true;
            } else {
                System.out.println(ship.getShipType() + " has already been sunk!");
                return false;
            }
        } else {
            return false;
        }
    }

    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }

    public boolean allIsSunk() {
        return shipsSunk == 10;
    }

}
