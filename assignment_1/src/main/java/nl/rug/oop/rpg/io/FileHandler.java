package nl.rug.oop.rpg.io;

import nl.rug.oop.rpg.StartGame;
import nl.rug.oop.rpg.game.GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandler {

    /**
     * Calls a function to list all of the saved games files that the user can load from depending on string type
     * @param type
     * @param newFileName
     * @return If the file was loaded successfully
     */
    public static boolean fileLoader(String type, String newFileName) {
        boolean minusOne = true;
        if(type.equals("saves")) System.out.println("Which save file would you like to load from? (-1 : none)");
        else System.out.println("Which config file would you like to load from? (-1 : none)");
        String fileName = getAllFiles(type);
        if(fileName.equals("noFilesException")) {
            System.out.println("No files available!");
            minusOne = false;
        }
        else if(!fileName.equals("-1fileException")) {
            if(type.equals("saves")) StartGame.initOldGame(fileName, "saves");
            else StartGame.initNewGame(newFileName, true, fileName);
            minusOne = false;
        }
        return minusOne;
    }

    /**
     * Asks the user what they would like to save the file as, depending on if its a config or save file
     * @param type
     * @return A filename string that the user inputted
     */
    public static String fileNamer(String type) {
        if(type.equals("saves")) System.out.println("What would you like to name this new save game file?");
        else System.out.println("What would you like to name this new config game file?");
        String fileName;
        Scanner scanner = new Scanner(System.in);
        fileName = scanner.nextLine();
        return fileName;
    }

    /**
     * Lists all of the currently saved game files under the correct file, config or saves
     * @param type
     * @return The filename chosen by the user from the list
     */
    public static String getAllFiles(String type) {
        Scanner scanner = new Scanner(System.in);
        File lsFiles;
        if(type.equals("saves")) lsFiles = new File("savedgames");
        else lsFiles = new File("config");
        String[] files = lsFiles.list();
        if(files == null || files.length == 0) return "noFilesException";
        ArrayList<String> allFiles = new ArrayList<String>(Arrays.asList(lsFiles.list()));
        StartGame.printOptions(allFiles);
        int currentMove;
        while(true) {
            try{
                currentMove = scanner.nextInt();
                if(currentMove == -1) return "-1fileException";
                String fileName = allFiles.get(currentMove).replace(".ser", "")
                        .replace(".ini", "");
                return fileName;
            } catch (Exception e) {
                GUI.invalidInputMessage();
                scanner.nextLine();
            }
        }
    }
}
