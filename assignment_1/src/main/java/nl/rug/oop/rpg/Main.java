package nl.rug.oop.rpg;

import com.sun.javafx.font.PrismFontFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ArrayList<Room> totalRooms = new ArrayList<Room>();
//        ArrayList<Door> totalDoors = new ArrayList<Door>();
//        try {
//            JsonReader.parseRoomJSON(totalRooms);
//            JsonReader.parseDoorJSON(totalRooms, totalDoors);
//            JsonReader.parseConnectionJSON(totalRooms, totalDoors);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        totalRooms.forEach(room -> {
//            System.out.println(room.getDescription());
//            room.printDoors();
//        });


        Room redDoorRoom = new Room("Red Door Room");
        Room blackDoorRoom = new Room("Black Door Room");

        Knight knight = new Knight("Sir WhoopYourAss");
        Knight knight1 = new Knight(("Sir Henry III"));

        Priest priest = new Priest("Holy Priest");
        HighPriest highPriest = new HighPriest("High Priest");

        Door redDoor = new Door("A mysterious red door.", redDoorRoom, blackDoorRoom);
        Door blackDoor = new Door("A black door.", blackDoorRoom, redDoorRoom);
        Room startingRoom = new Room("A rather dusty room full with computers and two doors.");
        startingRoom.addDoor(redDoor);
        startingRoom.addDoor(blackDoor);
        startingRoom.addNPC(knight);
        startingRoom.addNPC(knight1);
        startingRoom.addNPC(priest);
        startingRoom.addNPC(highPriest);
        Player player = new Player("P1", startingRoom, 10, 2, 10);
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
                        System.out.print("\t(" + i + ") " + "[" + npcs.get(i).getType() + "]" + "(" + npcs.get(i).getSpecies() + ")" + npcs.get(i).toString() + ": ");
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
                                ((Enemy) currentNPC).interact(player);
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
