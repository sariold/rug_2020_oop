package nl.rug.oop.rpg;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Room redDoorRoom = new Room("Red Door Room");
        Room blackDoorRoom = new Room("Black Door Room");

        Knight knight = new Knight("Sir WhoopYourAss");
        Knight knight1 = new Knight(("Sir Henry III"));

        Door redDoor = new Door("A mysterious red door.", redDoorRoom);
        Door blackDoor = new Door("A black door.", blackDoorRoom);
        Room startingRoom = new Room("A rather dusty room full with computers and two doors.");
        startingRoom.addDoor(redDoor);
        startingRoom.addDoor(blackDoor);
        startingRoom.addNPC(knight);
        startingRoom.addNPC(knight1);
        Player player = new Player("P1", startingRoom, 10, 2);
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
                    System.out.println("You look for doors.");
                    System.out.println("You see:");
                    ArrayList<Door> doors = player.getCurrentRoom().getDoors();
                    for (int i = 0; i < doors.size(); i++) {
                        System.out.print("\t(" + i + ") ");
                        doors.get(i).inspect();
                    }
                    System.out.println("Which door will you take? (-1 to stay)");
                    currentMove = scanner.nextInt();
                    if (currentMove < doors.size() && currentMove > -2) {
                        if (currentMove == -1) {
                            System.out.println("You stayed in the same room.");
                            break;
                        }
                        doors.get(currentMove).interact(player);
                        player.getCurrentRoom().inspect();
                    }
                    break;
                case 2:
                    System.out.println("You look if there's somebody.");
                    System.out.println("You see:");
                    ArrayList<DungeonNpc> npcs = player.getCurrentRoom().getNPCs();
                    for (int i = 0; i < npcs.size(); i++) {
                        System.out.print("\t(" + i + ") " + npcs.get(i).toString() + ": ");
                        npcs.get(i).inspect();
                    }
                    System.out.println("Who will you approach? (-1 to stay by yourself)");
                    currentMove = scanner.nextInt();
                    if (currentMove < npcs.size() && currentMove > -2) {
                        if (currentMove == -1) {
                            System.out.println("You decided to leave them alone.");
                            break;
                        }
                        DungeonNpc currentNPC= npcs.get(currentMove);
                        if (currentNPC instanceof Enemy) {
                            System.out.println("You engaged a fight with " + ((Enemy) currentNPC).getName());
                            while (((Enemy) currentNPC).getHitPoints() > 0 && player.getHitPoints() > 0) {
                                System.out.println("You attack " + ((Enemy) currentNPC).getName());
                                player.attack((Enemy)currentNPC);
                                if (((Enemy) currentNPC).isDead()) {
                                    System.out.println("You have slain " + ((Enemy) currentNPC).getName() + "!");
                                    player.getCurrentRoom().removeDeadNPC();
                                    break;
                                }
                                ((Enemy) currentNPC).attack(player);
                                System.out.println("You are attacked by " + ((Enemy) currentNPC).getName() + " you are at " + player.getHitPoints() + " health!");
                                if (player.isDead()) {
                                    System.out.println("You have been slain by " + ((Enemy) currentNPC).getName() + "!");
                                    return;
                                }
                            }
                        }

                    }
                    break;
            }
        }
    }
}
