package nl.rug.oop.rpg;

import com.sun.javafx.font.PrismFontFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String playerName;
        System.out.println("Please choose a name for your Hero:");
        playerName = scanner.nextLine();
        Game game = new Game(playerName);
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
                case 2:
                    game.inspectNPCs();
                    currentMove = scanner.nextInt();
                    game.interactNPC(currentMove);
                    break;
            }
        }
    }
}
