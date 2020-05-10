package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.game.StartGame;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class to handle file loading and naming files with custom user input names
 */
public class FileHandler {

    /**
     * Calls a function to list all of the saved games files that the user can load from depending on string type
     * @param type Type
     * @param newFileName New file name
     * @return If the file was loaded successfully
     */
    public static boolean fileLoader(String type, String newFileName) {
        boolean minusOne = true;
        if(type.equals("saves")) System.out.println("Which save file would you like to load from?");
        else System.out.println("Which config file would you like to load from?");
        String fileName = getAllFiles(type);
        if(fileName.equals("noFilesException")) {
            System.out.println("No files available!");
            minusOne = false;
        }
        else if(!fileName.equals("-1fileException")) {
            if(type.equals("saves")) StartGame.initOldGame(fileName, "saves", fileName);
            else StartGame.initNewGame(newFileName, true, fileName);
            minusOne = false;
        }
        return minusOne;
    }

    /**
     * Asks the user what they would like to save the file as, depending on if its a config or save file
     * @param type Type
     * @return A filename string that the user inputted
     */
    public static String fileNamer(String type) {
        String typePrint;
        if(type.equals("saves")) typePrint = "What would you like to name this new save game file?";
        else typePrint = "What would you like to name this new config game file?";
        System.out.println(typePrint);
        String fileName;
        Scanner scanner = new Scanner(System.in);
        while(true) {
            fileName = scanner.nextLine();
            if(fileName != null && fileName.matches("^[a-zA-Z0-9]*$") && !fileName.equals("")) break;
            System.out.println("Only use alphabetic characters and numbers!");
            System.out.println(typePrint);
        }
        return fileName;
    }

    /**
     * Lists all of the currently saved game files under the correct file, config or saves
     * @param type Type
     * @return The filename chosen by the user from the list
     */
    public static String getAllFiles(String type) {
        Scanner scanner = new Scanner(System.in);
        File lsFiles;
        if(type.equals("saves")) lsFiles = new File("savedgames");
        else lsFiles = new File("config");
        String[] files = lsFiles.list();
        if(files == null || files.length == 0) return "noFilesException";
        ArrayList<String> allFiles = new ArrayList<>(Arrays.asList(Objects.requireNonNull(lsFiles.list())));
        GUIMessages.printOptions(allFiles);
        int currentMove;
        while(true) {
            try{
                currentMove = scanner.nextInt();
                if(currentMove == -1) return "-1fileException";
                return allFiles.get(currentMove).replace(".ser", "")
                        .replace(".ini", "");
            } catch (Exception e) {
                GUIMessages.invalidInputMessage();
                scanner.nextLine();
            }
        }
    }
}
