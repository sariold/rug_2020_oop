package nl.rug.oop.rpg;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName;
        System.out.println("Please choose a name for your Hero:");
        playerName = scanner.nextLine();
        Game game = new Game(playerName);

        int currentMove;

        while (true) {
            game.printOptions();
            currentMove = scanner.nextInt();
            switch (currentMove) {
                // inspect room
                case 0:
                    game.inspectRoom();
                    break;
                // inspect doors
                case 1:
                    game.inspectDoors();
                    currentMove = scanner.nextInt();
                    game.interactDoor(currentMove);
                    break;
                // inspect NPCs
                case 2:
                    game.inspectNPCs();
                    currentMove = scanner.nextInt();
                    game.interactNPC(currentMove);
                    break;
                // show Stats
                case 3:
                    game.displayStats();
                    break;
                    
            }
        }
    }
}
