package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.enemies.Enemy;
import nl.rug.oop.rpg.npcs.healer.Healer;
import nl.rug.oop.rpg.npcs.trader.Trader;

import java.util.ArrayList;

/**
 * Presents the user with a text based user interface
 */
public class GUI {

    /**
     * Prints health and healthbar of the player and the current enemy during combat
     * Prints all combat options the player has
     * @param player
     * @param enemy
     * @param game
     */
    public static void displayCombatInterface(Player player, Enemy enemy, Game game) {
        String playerHealthBar = "";
        String enemyHealthBar = "";
        String indentStringName = player.getName() + ": " + player.getHitPoints()
                + "/" + player.getMaxHitPoints();
        int indentName = 30 - indentStringName.length();
        int indentBar = 19;
        double playerPercent = (double)player.getHitPoints()/player.getMaxHitPoints();
        double enemyPercent = (double)enemy.getHitPoints()/enemy.getMaxHitPoints();
        for (double i = playerPercent; i > 0; i -= 0.1) {playerHealthBar = playerHealthBar.concat("=");}
        for (double i = enemyPercent; i > 0; i -= 0.1) {enemyHealthBar = enemyHealthBar.concat("=");}
        String playerColor = playerPercent > 0.64 ? TextColor.ANSI_GREEN : (playerPercent > 0.32
                ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        String enemyColor = enemyPercent > 0.64 ? TextColor.ANSI_GREEN : (enemyPercent > 0.32
                ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        System.out.printf("%s%s: %d/%d %s", TextColor.ANSI_YELLOW, player.getName(), player.getHitPoints(),
                player.getMaxHitPoints(),TextColor.ANSI_RESET);
        for (int i = 0; i < indentName; i++){ System.out.print(" ");}
        System.out.printf("%s%s%s\n", TextColor.ANSI_RED, enemy.getName(),TextColor.ANSI_RESET);
        System.out.printf("[%s%-10s%s]", playerColor, playerHealthBar, TextColor.ANSI_RESET);
        for (int i = 0; i < indentBar; i++){ System.out.print(" ");}
        System.out.printf("[%s%-10s%s]\n", enemyColor, enemyHealthBar, TextColor.ANSI_RESET);
        System.out.println("What will you do?");
        for(int i = 0; i < game.getFightMoves().size(); i++) {
            System.out.println("\t (" + i + ") " + game.getFightMoves().get(i));
        }
    }

    /**
     * Prints all items in the players inventory
     * @param player
     */
    public static void displayInventory(Player player) {
        if (player.getInventory().size() == 0) {
            System.out.println("You currently have no items.");
            return;
        }
        System.out.println("Your inventory contains:");
        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.println("\t(" + i + ") " + player.getInventory().get(i).toString());
        }
        System.out.println("What item will you use (-1 to not use an item)");
    }

    /**
     * Prints all items in the players inventory that can be used in combat
     * @param player
     */
    public static void displayCombatInventory(Player player) {
        if (player.getCombatInventory().size() == 0) {
            System.out.println("You currently have no items for combat.");
            return;
        }
        System.out.println("Your inventory contains:");
        for (int i = 0; i < player.getCombatInventory().size(); i++) {
            System.out.println("\t(" + i + ") " + player.getCombatInventory().get(i).toString());
        }
        System.out.println("What item will you use (-1 to not use an item)");
    }

    /**
     * Print an ArrayList of Collectables as an option menu
     * @param arrayList
     */
    public static void displayItems(ArrayList<Collectable> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t(" + i + ") " + arrayList.get(i).toString());
        }
    }

    /**
     * Prints the players current stats
     * @param player
     */
    public static void displayStats(Player player) {
        System.out.println("Your Character: " + player.getName());
        System.out.println(TextColor.ANSI_GREEN + "\tHealth: " + TextColor.ANSI_RESET + player.getHitPoints()
                + "/" + player.getMaxHitPoints());
        System.out.println(TextColor.ANSI_RED + "\tAttack: " + TextColor.ANSI_RESET + player.getAttackPoints());
        System.out.println(TextColor.ANSI_YELLOW + "\tGold: " + TextColor.ANSI_RESET + player.getGold());
    }


    /**
     * Prints the dialog when approaching a trader
     * @param trader
     */
    public static void tradeDialog(Trader trader) {
        System.out.println(trader.getName() + ": " + TextColor.ANSI_PURPLE + trader.tradeDialog()
                + "\n Are you interested?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) I think that is too expensive!\n\t(1) Let's trade!");
    }

    /**
     * Prints the dialog when approaching a healer
     * @param healer
     */
    public static void healDialog(Healer healer) {
        System.out.println(healer.getName() + ":" + TextColor.ANSI_PURPLE + "I can only heal you once, and then "
                + "I will leave!\n Are you sure you want me to heal you?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) No better do it later!\n\t(1) Heal me!");
    }

    /**
     * prints a message when using a combat item outside of combat
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
            + TextColor.ANSI_RESET); }

}
