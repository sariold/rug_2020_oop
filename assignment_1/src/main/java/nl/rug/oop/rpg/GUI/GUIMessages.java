package nl.rug.oop.rpg.GUI;

import nl.rug.oop.rpg.extra.TextColor;

import java.util.ArrayList;

/**
 * Class that prints messages in the game
 */
public class GUIMessages {
    /**
     * Prints a message when using a combat item outside of combat
     */
    public static void onlyCombatItemMessage(){ System.out.println(TextColor.ANSI_YELLOW + "This item can only "
            + "be used in combat." + TextColor.ANSI_RESET); }

    /**
     * Prints a message when invalid input is given
     */
    public static void invalidInputMessage() { System.out.println("That is not a valid input!"); }

    /**
     * Prints a message when invalid item is chosen from the inventory
     */
    public static void invalidItemMessage() { System.out.println("That is not a valid item!"); }

    /**
     * Prints a message when the game is won
     */
    public static void winGameMessage(){
        System.out.println(TextColor.ANSI_YELLOW + "Congratulations you have won the game! You are a real "
                + "dungeon master!" + TextColor.ANSI_RESET);
        System.out.println(TextColor.ANSI_BLUE + "This game has been brought to you by Diego and Felix."
                + TextColor.ANSI_RESET);
    }

    /**
     * Prints a message when the player died
     */
    public static void gameOverMessage() { System.out.println(TextColor.ANSI_WHITE + "GAME OVER!"
            + TextColor.ANSI_RESET);
    }

    /**
     * Prints a message when the player has been burned
     */
    public static void burnedMessage() {
        System.out.println(TextColor.ANSI_RED + "You have been burned!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when the player has been frozen
     */
    public static void frozenMessage() {
        System.out.println(TextColor.ANSI_BLUE + "You have been frozen!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when running from a fight
     * @param name Name of enemy
     */
    public static void runFromFightMessage(String name) {
        System.out.println(TextColor.ANSI_YELLOW + "You ran from the fight. " + TextColor.ANSI_RED
                + name + TextColor.ANSI_YELLOW + " recovered to full health!"
                + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when starting a new game
     */
    public static void newGameMessage() {
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("Play the game normally");
        possibleMoves.add("Initialize from config");
        possibleMoves.add("Set default config");
        System.out.println("You are about to start a new game, what do you want to do?");
        printOptions(possibleMoves);
    }

    /**
     * Print an arraylist as chooseable options
     * @param arrayList Arraylist
     */
    public static void printOptions(ArrayList<String> arrayList) {
        System.out.println("What do you want to do?");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t(" + i + ") " + arrayList.get(i));
        }
    }

    /**
     * prints a message when starting the game
     */
    public static void startGameMessage() {
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("New Game");
        possibleMoves.add("Load Game");
        printOptions(possibleMoves);
    }

    /**
     * prints a message when saving the game
     * @param fileName Current save file
     */
    public static void saveGameMessage(String fileName) {
        ArrayList<String> possibleOptions = new ArrayList<>();
        possibleOptions.add("Save to current file: " + fileName);
        possibleOptions.add("Save to a new file");
        printOptions(possibleOptions);
    }
}
