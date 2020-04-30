package nl.rug.oop.rpg;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName = "";
        System.out.println("Please choose a name for your Hero:");
        playerName = scanner.nextLine();
        while (playerName.length() > 15) {
            System.out.println("Your name can only have 15 characters.");
            System.out.println("Please choose a name for your Hero:");
            playerName = scanner.nextLine();
        }
        Game game = new Game(playerName);

        int currentMove;

        while (true) {
            game.printOptions();
            try {
                currentMove = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid input!");
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                // inspect room
                case 0:
                    game.inspectRoom();
                    break;
                // inspect doors
                case 1:
                    game.inspectDoors();
                    try {
                        currentMove = scanner.nextInt();
                        game.interactDoor(currentMove);
                    } catch (InputMismatchException e) {
                        System.out.println("That is not a valid input!");
                        scanner.nextLine();
                    }
                    break;
                // inspect NPCs
                case 2:
                    if(game.inspectNPCs()) {
                        try {
                            currentMove = scanner.nextInt();
                            game.interactNPC(currentMove);
                        } catch (InputMismatchException e) {
                            System.out.println("That is not a valid input!");
                            scanner.nextLine();
                        }
                    }
                    break;
                // show items in the room
                case 3:
                    if(game.inspectItems()) {
                        try {
                            currentMove = scanner.nextInt();
                            game.interactItem(currentMove);
                        } catch (InputMismatchException e) {
                            System.out.println("That is not a valid input!");
                            scanner.nextLine();
                        }
                    }
                    break;
                // show inventory
                case 4:
                    game.useInventory();
                    break;
                // show Stats
                case 5:
                    game.displayStats();
                    break;
            }
        }
    }
}
