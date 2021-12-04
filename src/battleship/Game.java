package battleship;

import java.util.Scanner;

public class Game {

    private Player player_one;
    private Player player_two;

    public Game() {
        player_one = new Player();
        player_two = new Player();
        startGame();
    }

    public void startGame() {

        Scanner input = new Scanner(System.in);

        System.out.println("* * * * * * * * * * * * * * * *");
        System.out.println("Welcome to Caleb's Battleship");
        System.out.println("Choose one of the following:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs AI");
        System.out.println("* * * * * * * * * * * * * * * *");

        int choice = input.nextInt();

        if (choice == 1) {
            System.out.println("Player one, enter your name:");

            String player_one_name = input.next();

            player_one.setName(player_one_name);

            System.out.println("Player two, enter your name:");

            String player_two_name = input.next();

            player_two.setName(player_two_name);
        }

        while (!isGameOver()) {
            if (choice == 1) {


                System.out.println("Player One [" + player_one.getName() + "], please set up your grid");
                player_one.setUpGrid();
                System.out.println("Player Two [" + player_two.getName() + "], please set up your grid");
                player_two.setUpGrid();

                System.out.println("* * * * * * Player 1's turn * * * * * *");

                System.out.println("Player One [" + player_one.getName() + "]'s sight grid:");
                player_one.getOceans()[1].print();

                System.out.println("Player One [" + player_one.getName() + "], choose your coordinates to attack the opponent's fleet:");

                int x = input.nextInt();
                int y = input.nextInt();

                player_one.shoot();

                if (player_two.getOceans()[0].shootAt(x, y)) {
                    player_one.getOceans()[1].hit(x, y);
                }

                System.out.println("# of Sunken ships: " + player_two.getOceans()[0].getShipsSunk());
                System.out.println("# of Shots fired: " + player_one.getShotsFired());


                System.out.println("* * * * * * Player 2's turn * * * * * *");

                System.out.println("Player Two [" + player_two.getName() + "]'s sight grid:");
                player_two.getOceans()[1].print();

                System.out.println("Player Two [" + player_two.getName() + "], choose your coordinates to attack the opponent's fleet:");

                x = input.nextInt();
                y = input.nextInt();

                player_two.shoot();

                if (player_one.getOceans()[0].shootAt(x, y)) {
                    player_two.getOceans()[1].hit(x, y);
                }

                System.out.println("# of Sunken ships: " + player_two.getOceans()[0].getShipsSunk());
                System.out.println("# of Shots fired: " + player_one.getShotsFired());


            } else if (choice == 2) {

                System.out.println("Player one, enter your name:");

                String player_one_name = input.next();

                player_one.setName(player_one_name);
                player_two.setName("AI Computer");

                // PvE so one grid each -- memory clean up
                player_one.getOceans()[1] = null;
                player_two.getOceans()[1] = null;

                // Set up AI board
                player_two.getOceans()[0].placeAllShipsRandomly();

                System.out.println(player_one.getName() + "'s sight board:");

                player_one.getOceans()[0].print();

                System.out.println("Choose your coordinates to attack the opponent's fleet:");

                int x = input.nextInt();
                int y = input.nextInt();

                player_one.setShotsFired(player_one.getShotsFired() + 1);

                if (player_two.getOceans()[0].shootAt(x, y)) {
                    player_one.getOceans()[0].hit(x, y);
                }

                System.out.println("# of Sunken ships: " + player_two.getOceans()[0].getShipsSunk());
                System.out.println("# of Shots fired: " + player_one.getShotsFired());
            } else {
                System.out.println("Invalid response. Returning...");
            }
        }

    }

    public void initializeGame(int choice) {

    }


    public boolean isGameOver() {
        if (player_two.getOceans()[0].allIsSunk()) {
            System.out.println("Game over! Player One [" + player_one.getName() + "] has beaten Player Two [" + player_two.getName() + "]!");
            return true;
        } else if (player_one.getOceans()[0].allIsSunk()) {
            System.out.println("Game over! Player One [" + player_one.getName() + "] has beaten Player Two [" + player_two.getName() + "]!");
            return true;
        } else {
            return false;
        }
    }
}
