package nl.rug.oop.rpg.io;
import nl.rug.oop.rpg.game.Game;

import java.io.*;

/**
 * Serializer class that allows saving and loading the game object
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Serializer {

    /**
     * Saves the game to a specific file, given by the user
     * @param game Game
     * @param fileName File name
     */
    public static void saveGame(Game game, String fileName) {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();

        try(FileOutputStream fileOutputStream = new FileOutputStream(saveDirectory + File.separator
                + fileName + ".ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(game);
            objectOutputStream.close();
            System.out.println("Save successful!");
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found!");
        } catch (IOException e) {
            System.out.println("Could not write to file!");
        }
    }

    /**
     * Loads the game from a specific file given by the user
     * @param fileName File Name
     * @return A game object
     * @throws IOException Wrong format
     * @throws ClassNotFoundException Cannot create a game object
     */
    public static Game loadGame(String fileName) throws IOException, ClassNotFoundException {
        File saveDirectory = new File("savedgames");
        saveDirectory.mkdir();
        try(FileInputStream fileInputStream = new FileInputStream(saveDirectory + File.separator
                + fileName + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Game game = (Game) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println("Game loaded successfully!");
            return game;
        }
    }
}
