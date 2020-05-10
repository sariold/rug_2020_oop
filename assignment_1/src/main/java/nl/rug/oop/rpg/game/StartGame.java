package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.config.Config;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.GameOptions;
import nl.rug.oop.rpg.io.FileHandler;
import nl.rug.oop.rpg.io.Serializer;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class to create a new game object and start the game
 */
public class StartGame {

    /**
     * Present the player with starting game options
     */
    public static void startGameOption() {
        boolean start = true;
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        while(start) {
            GUIMessages.startGameMessage();
            try{
                currentMove = scanner.nextInt();
                if(currentMove == -1) start = false;
            } catch (Exception e) {
                GUIMessages.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                case 0:
                    GUIMessages.newGameMessage();
                    configOptions();
                    break;
                case 1:
                    if(FileHandler.fileLoader("saves", null)) break;
            }
        }
    }

    /**
     * Initialize from a saved game file, the game object becomes a loaded game object
     * @param fileName File name
     * @param type Type
     * @param oldFileName Old file name
     */
    public static void initOldGame(String fileName, String type, String oldFileName) {
        Game game;
        try {
            game = Serializer.loadGame(fileName);
            GameOptions.gameStart(game, oldFileName);
        } catch (IOException e) {
            System.out.println("Could not load from the file!");
            if(type.equals("saves")) startGameOption();
        } catch (ClassNotFoundException e) {
            System.out.println("The save file could not be used to load a game!");
            startGameOption();
        }
    }

    /**
     * Creates a new game object and a brand new game, checks if its a config loaded game, and which config file
     * @param fileName File name
     * @param fromConfig Boolean value
     * @param configName Config name
     */
    public static void initNewGame(String fileName, boolean fromConfig, String configName) {
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
        Game game = new Game(playerName);
        if (fromConfig) {
            Config.loadConfig(game, configName);
        }
        Serializer.saveGame(game, fileName);
        System.out.println(TextColor.ANSI_BLUE + "Slay both mini bosses and be forced to face the final boss!" +
                TextColor.ANSI_RESET);
        GameOptions.gameStart(game, fileName);
    }

    /**
     * Saves the current game to a specific fileName string
     * @param game Game
     * @param fileName File name
     */
    public static void gameSave(Game game, String fileName) {
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        String newFileName;
        while (true) {
            GUIMessages.saveGameMessage(fileName);
            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                GUIMessages.invalidInputMessage();
                scanner.nextLine();
                continue;
            }
            switch (currentMove) {
                case 0:
                    Serializer.saveGame(game, fileName);
                    return;
                case 1:
                    newFileName = FileHandler.fileNamer("saves");
                    Serializer.saveGame(game, newFileName);
                    return;
            }
        }
    }

    public static void configOptions() {
        Scanner scanner = new Scanner(System.in);
        int currentMove = 0;
        String fileName;
        try{
            currentMove = scanner.nextInt();
        } catch (Exception e) {
            GUIMessages.invalidInputMessage();
            scanner.nextLine();
        }
        switch (currentMove) {
            // normal game
            case 0:
                fileName = FileHandler.fileNamer("saves");
                initNewGame(fileName, false, null);
                break;
            // from config
            case 1:
                fileName = FileHandler.fileNamer("saves");
                if(FileHandler.fileLoader("config", fileName)) break;
                break;
            // set config
            case 2:
                fileName = FileHandler.fileNamer("config");
                Config.setConfig(fileName);
                break;
        }
    }

}