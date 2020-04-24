package nl.rug.oop.rpg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Room redDoorRoom = new Room("Red Door Room");
        Room blackDoorRoom = new Room("Black Door Room");

        Door redDoor = new Door("A mysterious red door.", redDoorRoom);
        Door blackDoor = new Door("A black door.", blackDoorRoom);
        Room startingRoom = new Room("A rather dusty room full with computers and two doors.");
        startingRoom.addDoor(redDoor);
        startingRoom.addDoor(blackDoor);
        Player player = new Player("P1", startingRoom);
        Scanner scanner = new Scanner(System.in);
        String[] possibleMoves;
        int currentMove;


        while (true) {
            System.out.println("What do you want to do?");
            possibleMoves = player.getPossibleMoves();
            for (int i = 0; i < possibleMoves.length; i++) {
                System.out.println("\t(" + i + ") " + possibleMoves[i]);
            }
            currentMove = scanner.nextInt();
            switch (currentMove) {
                // inspect room
                case 0:
                    System.out.print("You see: ");
                    player.getCurrentRoom().inspect();
                    break;
                // inspect doors
                case 1:
                    System.out.print("You see: \n");
                    ArrayList<Door> doors = player.getCurrentRoom().getDoors();
                    for (int i = 0; i < doors.size(); i++) {
                        System.out.print("\t(" + i + ") ");
                        doors.get(i).inspect();
                    }
                    currentMove = scanner.nextInt();
                    if (currentMove < doors.size() && currentMove > -1) {
                        doors.get(currentMove).interact(player);
                        player.getCurrentRoom().inspect();
                    }
                    break;
            }
        }
    }
}