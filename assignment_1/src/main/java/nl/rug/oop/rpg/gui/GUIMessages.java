package nl.rug.oop.rpg.gui;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Attackable;

import java.util.ArrayList;

/**
 * Class that prints messages in the game
 */
public class GUIMessages {

    /**
     * Prints a message when using a combat item outside of combat
     */
    public static void onlyCombatItemMessage(){ System.out.println(TextColor.ANSI_YELLOW + "This item can only "
            + "be used in combat." + TextColor.ANSI_RESET);
    }

    /**
     * Prints a message when using a monster key item from inventory
     */
    public static void onlyMonsterDoorUsage(){ System.out.println(TextColor.ANSI_YELLOW + "This item can only "
            + "be used to unlock monster doors." + TextColor.ANSI_RESET);
    }

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
    public static void gameOverMessage() {
        System.out.println(TextColor.ANSI_WHITE + "GAME OVER!"
            + TextColor.ANSI_RESET);
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
        System.out.println("What do you want to do? (-1 : exit)");
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

    /**
     * prints a message that the player has been healed for a given amount
     * @param healValue Heal Value
     */
    public static void playerHealedMessage(int healValue) { System.out.println(TextColor.ANSI_GREEN
            + "You have been healed " + healValue + " hit points!" + TextColor.ANSI_RESET); }

    /**
     * prints a message that the attacked is attacked by the attacker
     * @param attacker Attacker
     * @param attacked Attacked
     */
    public static void attackedMessage(Attackable attacker, Attackable attacked) {
        System.out.println(TextColor.ANSI_RED + attacked.getName() + " is attacked by "
                + attacker.getName() + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when a critical hit has been landed
     */
    public static void criticalHitMessage() { System.out.println(TextColor.ANSI_RED + "Critical Hit!"
            + TextColor.ANSI_RESET); }

    /**
     * prints how much damage an attacked has taken
     * @param attacked Attacked
     * @param damage Damage taken
     */
    public static void takesDamageMessage(Attackable attacked, int damage) {
        System.out.println(TextColor.ANSI_YELLOW + attacked.getName() + " takes " + damage + " damage!"
                + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object is frozen
     * @param attacked Frozen attackable
     */
    public static void isFrozenMessage(Attackable attacked) { System.out.println(TextColor.ANSI_RED
            + attacked.getName() + " is frozen solid." + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object is no longer frozen
     * @param attacked No longer frozen attackable
     */
    public static void noLongerFrozenMessage(Attackable attacked) { System.out.println(TextColor.ANSI_BLUE
            + attacked.getName() + " is no longer frozen!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object took burn damage
     * @param attacked Attacked that takes burn damage
     */
    public static void burnDamageMessage(Attackable attacked) { System.out.println(TextColor.ANSI_RED
            + attacked.getName() + " is burned and takes " + DefaultStats.BURN_DAMAGE + " damage."
            + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object is no longer burned
     * @param attacked Attacked
     */
    public static void noLongerBurnedMessage(Attackable attacked) { System.out.println(TextColor.ANSI_BLUE
            + attacked.getName() + " does no longer burn!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object has been burned
     * @param attackable Attackable that was burned
     */
    public static void hasBeenBurnedMessage(Attackable attackable) { System.out.println(TextColor.ANSI_YELLOW
            + attackable.getName() + " has been burned!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message that an attackable object has been frozen
     * @param attackable Attackable that was frozen
     */
    public static void hasBeenFrozenMessage(Attackable attackable) { System.out.println(TextColor.ANSI_BLUE
            + attackable.getName() + " has been frozen!" + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when an attack potion is used
     * @param attackPower Bonus attack given
     */
    public static void attackPotionMessage(int attackPower) { System.out.println(TextColor.ANSI_YELLOW
            + "You consumed an attack potion. Your attack has increased by " + attackPower
            + " until after the next fight." + TextColor.ANSI_RESET);
    }
}
