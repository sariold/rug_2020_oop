package nl.rug.oop.rpg;

import nl.rug.oop.rpg.config.Config;
import nl.rug.oop.rpg.io.Serializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to create a new game object and start the game
 */
public class StartGame {

    public static void main(String[] args) {
    }


    /**
     * Print an arraylist as chooseable options
     * @param arrayList
     */
    public void printOptions(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t(" + i + ") " + arrayList.get(i));
        }
    }

    /**
     * Present the player with starting game options
     */
    public void startGameOption() {
        ArrayList<String> possibleMoves = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        int currentMove;
        String fileName = "";
        while(true) {
            possibleMoves.clear();
            possibleMoves.add("New Game");
            possibleMoves.add("Load Game");
            System.out.println("What do you want to do?");
            printOptions(possibleMoves);
            try{
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                GUI.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                case 0:
                    possibleMoves.clear();
                    possibleMoves.add("Play the game normally");
                    possibleMoves.add("Initialise from config");
                    possibleMoves.add("Set default config");
                    System.out.println("You are about to start a new game, what do you want to do?");
                    printOptions(possibleMoves);
                    try{
                        currentMove = scanner.nextInt();
                    } catch (Exception e) {
                        GUI.invalidInputMessage();
                        scanner.nextLine();
                        continue;
                    }
                    switch (currentMove) {
                        // normal game
                        case 0:
                            fileName = fileNamer();
                            initNewGame(fileName, false);
                            break;
                        // from config
                        case 1:
                            fileName = fileNamer();
                            initNewGame(fileName, true);
                            break;
                        // set config
                        case 2:
                            Config.setConfig();
                            break;
                    }

                    break;
                case 1:
                    if(fileLoader()) break;
            }
        }
    }

    /**
     * Calls a function to list all of the saved games files that the user can load from
     * @return
     */
    public boolean fileLoader() {
        boolean minusOne = true;
        System.out.println("Which file would you like to load from? (-1 : none)");
        String fileName = getAllFiles();
        if(fileName == "noFilesException") {
            System.out.println("No save files available!");
            minusOne = false;
        }
        else if(fileName != "-1fileException") {
            initOldGame(fileName);
            minusOne = false;
        }
        return minusOne;
    }

    /**
     * Asks the user what they would like to save the file as
     * @return
     */
    public String fileNamer() {
        System.out.println("What would you like to name this new game file?");
        String fileName = "";
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        return fileName;
    }

    /**
     * Lists all of the currently saved game files
     * @return
     */
    public String getAllFiles() {
        Scanner scanner = new Scanner(System.in);
        File lsFiles = new File("savedgames");
        String[] files = lsFiles.list();
        if(files == null || files.length == 0) return "noFilesException";
        ArrayList<String> allFiles = new ArrayList<String>(Arrays.asList(lsFiles.list()));
        printOptions(allFiles);
        int currentMove;
        while(true) {
            try{
                currentMove = scanner.nextInt();
                if(currentMove == -1) return "-1fileException";
                return allFiles.get(currentMove).toString().replace(".ser", "");
            } catch (Exception e) {
                GUI.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
        }
    }

    /**
     * Initialize from a saved game file, the game object becomes a loaded game object
     * @param fileName
     */
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

    /**
     * Creates a new game object and a brand new game
     * @param fileName
     * @param fromConfig
     */
    public void initNewGame(String fileName, boolean fromConfig) {
        Scanner scanner = new Scanner(System.in);
        String playerName = "";
        if (!fromConfig) {
            System.out.println("Please choose a name for your Hero:");
            playerName = scanner.nextLine();
            while (playerName.length() > 15) {
                System.out.println("Your name can only have 15 characters.");
                System.out.println("Please choose a name for your Hero:");
                playerName = scanner.nextLine();
            }
        }
        System.out.println(TextColor.ANSI_BLUE + "Slay both mini bosses and be forced to face the final boss!" +
                TextColor.ANSI_RESET);

        Game game = new Game(playerName);
        if (fromConfig) {
            Config.loadConfig(game);
        }
        Serializer.saveGame(game, fileName);
        gameStart(game, fileName);
    }

    /**
     * Saves the current game to a specific fileName string
     * @param game
     * @param fileName
     */
    public void gameSave(Game game, String fileName) {
        ArrayList<String> possibleOptions = new ArrayList<>();
        possibleOptions.add("Save to current file: " + fileName);
        possibleOptions.add("Save to a new file");
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        String newFileName = "";
        while (true) {
            System.out.println("What do you want to do?");
            printOptions(possibleOptions);
            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                GUI.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                case 0:
                    Serializer.saveGame(game, fileName);
                    return;
                case 1:
                    newFileName = fileNamer();
                    Serializer.saveGame(game, newFileName);
                    return;
            }
        }
    }

    /**
     * Starts the game and presents the user with their possible move options to navigate the rpg
     * @param game
     * @param fileName
     */
    public void gameStart(Game game, String fileName) {
        Scanner scanner = new Scanner(System.in);
        int currentMove;

        while (true) {
            System.out.println("What do you want to do?");
            printOptions(game.getPossibleMoves());
            try {
                currentMove = scanner.nextInt();
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                // inspect room
                case 0:
                    GUI.inspectRoom(game.getPlayer());
                    break;
                // inspect doors
                case 1:
                    GUI.inspectDoors(game.getPlayer());
                    try {
                        currentMove = scanner.nextInt();
                        game.interactDoor(currentMove);
                    } catch (InputMismatchException e) {
                        GUI.invalidInputMessage();
                        scanner.nextLine();
                    }
                    break;
                // inspect NPCs
                case 2:
                    if(GUI.inspectNPCs(game.getPlayer())) {
                        try {
                            currentMove = scanner.nextInt();
                            game.interactNPC(currentMove);
                        } catch (InputMismatchException e) {
                            GUI.invalidInputMessage();
                            scanner.nextLine();
                        }
                    }
                    break;
                // show items in the room
                case 3:
                    if(GUI.inspectItems(game.getPlayer())) {
                        try {
                            currentMove = scanner.nextInt();
                            game.interactItem(currentMove);
                        } catch (InputMismatchException e) {
                            GUI.invalidInputMessage();
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
                    GUI.displayStats(game.getPlayer());
                    break;
                case 6:
                    Serializer.saveGame(game, "quicksave");
                    break;
                case 7:
                    initOldGame("quicksave");
                    break;
                case 8:
                    gameSave(game, fileName);
                    break;
                case 9:
                    if(fileLoader()) break;
            }
        }
    }

}
