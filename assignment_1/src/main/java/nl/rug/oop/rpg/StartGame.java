package nl.rug.oop.rpg;

import nl.rug.oop.rpg.io.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartGame {

    public static void main(String[] args) {
    }


    public void printOptions(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t(" + i + ") " + arrayList.get(i));
        }
    }

    public void startGameOption() {
        ArrayList<String> possibleMoves = new ArrayList<String>();
        possibleMoves.add("New Game");
        possibleMoves.add("Load Game");
        printOptions(possibleMoves);
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        String fileName = "";
        while(true) {
            try{
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("That is not a valid input!");
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                case 0:
                    System.out.println("What would you like to name this new game file?");
                    fileName = fileNamer();
                    initNewGame(fileName);
                    break;
                case 1:
                    System.out.println("Which file would you like to load from?");
                    fileName = getAllFiles();
                    initOldGame(fileName);
                    break;
            }
        }
    }

    public String fileNamer() {
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        return fileName;
    }

    public String getAllFiles() {
        Scanner scanner = new Scanner(System.in);
        File lsFiles = new File("assignment_1" + File.separator + "saves");
        ArrayList<String> allFiles = new ArrayList<String>(Arrays.asList(lsFiles.list()));
        printOptions(allFiles);
        int currentMove;
        while(true) {
            try{
                currentMove = scanner.nextInt();
                return allFiles.get(currentMove).toString().replace(".ser", "");
            } catch (Exception e) {
                System.out.println("That is not a valid input!");
                scanner.nextLine();
                continue;
            }
        }
    }

    public void initOldGame(String fileName) {
        Game game;
        try {
            game = Serializer.loadGame(fileName);
            gameStart(game, fileName);
        } catch (IOException e) {
            System.out.println("Could not load from the file!");
            startGameOption();
        } catch (ClassNotFoundException e) {
            System.out.println("The savefile could not be used to load a game!");
            startGameOption();
        }
    }

    public void initNewGame(String fileName) {
        Scanner scanner = new Scanner(System.in);
        String playerName = "";
        System.out.println("Please choose a name for your Hero:");
        playerName = scanner.nextLine();
        while (playerName.length() > 15) {
            System.out.println("Your name can only have 15 characters.");
            System.out.println("Please choose a name for your Hero:");
            playerName = scanner.nextLine();
        }
        System.out.println(TextColor.ANSI_BLUE + "Slay both mini bosses and be forced to face the final boss!" +
                TextColor.ANSI_RESET);

        Game game = new Game(playerName);
        Serializer.saveGame(game, fileName);
        gameStart(game, fileName);
    }

    public void gameStart(Game game, String fileName) {
        Scanner scanner = new Scanner(System.in);
        int currentMove;

        while (true) {
            System.out.println("What do you want to do?");
            printOptions(game.getPossibleMoves());
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
                case 6:
                    Serializer.saveGame(game, fileName);
                    break;
                case 7:
                    initOldGame(fileName);
                    break;
            }
        }
    }

}
