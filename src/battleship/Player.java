package battleship;


import battleship.ships.Ship;

import java.util.Scanner;

public class Player {
    private String name;
    private Ocean[] oceans;
    private int shotsFired;

    public Player() {
        this.shotsFired = 0;
        this.oceans = new Ocean[2];
        this.oceans[0] = new Ocean();
        this.oceans[1] = new Ocean();
    }

    public void setUpGrid() {
        Scanner console = new Scanner(System.in);

        System.out.println("1. Randomize ship placement");
        System.out.println("2. Manually place ships");

        int choice = console.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.println("1. Randomize ship placement");
            System.out.println("2. Manually place ships");
            choice = console.nextInt();
        }

        if (choice == 1) {
            getOceans()[0].placeAllShipsRandomly();
        } else {
            placeShipsManually();
        }
    }

    public void placeShipsManually() {
        Scanner console = new Scanner(System.in);
        for (Ship ship : oceans[0].getShipArrayList()) {
            System.out.println("Choose coordinates to place your " + ship.getShipType() + ".");
            int row = console.nextInt();
            int column = console.nextInt();
            System.out.println("0 for horizontal, else vertical");
            boolean horizontal = console.nextInt() == 0;
            boolean canPlace = ship.okToPlaceShipAt(row, column, horizontal, oceans[0]);
            while (!canPlace) {
                System.out.println("Try again...");
                System.out.println("Choose coordinates to place your " + ship.getShipType() + ".");
                row = console.nextInt();
                column = console.nextInt();
                System.out.println("0 for horizontal, else vertical");
                horizontal = console.nextInt() == 0;
                canPlace = ship.okToPlaceShipAt(row, column, horizontal, oceans[1]);
            }

            // Ship placed, re-render grid
            ship.placeShipAt(row, column, horizontal, oceans[0]);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            oceans[0].print();
        }
    }

    public void shoot() {
        shotsFired++;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public Ocean[] getOceans() {
        return oceans;
    }

    public void setOceans(Ocean[] oceans) {
        this.oceans = oceans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }


}
