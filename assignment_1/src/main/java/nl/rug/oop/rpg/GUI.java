package nl.rug.oop.rpg;

import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.DungeonNpc;
import nl.rug.oop.rpg.npcs.enemies.Enemy;
import nl.rug.oop.rpg.npcs.healer.Healer;
import nl.rug.oop.rpg.npcs.trader.Trader;
import nl.rug.oop.rpg.objects.doors.Door;

import java.util.ArrayList;

public class GUI {

    /**
     * prints health and healthbar of the player and the current enemy during combat
     * prints all combat options the player has
     * @param player
     * @param enemy
     * @param game
     */
    public static void displayCombatInterface(Player player, Enemy enemy, Game game) {
        String playerHealthBar = "";
        String enemyHealthBar = "";
        String indentStringName = player.getName() + ": " + Integer.toString(player.getHitPoints()) + "/" + Integer.toString(player.getMaxHitPoints());
        int indentName = 30 - indentStringName.length();
        int indentBar = 19;
        double playerPercent = (double)player.getHitPoints()/player.getMaxHitPoints();
        double enemyPercent = (double)enemy.getHitPoints()/enemy.getMaxHitPoints();
        for (double i = playerPercent; i > 0; i -= 0.1) {playerHealthBar = playerHealthBar.concat("=");}
        for (double i = enemyPercent; i > 0; i -= 0.1) {enemyHealthBar = enemyHealthBar.concat("=");}
        String playerColor = playerPercent > 0.64 ? TextColor.ANSI_GREEN : (playerPercent > 0.32 ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        String enemyColor = enemyPercent > 0.64 ? TextColor.ANSI_GREEN : (enemyPercent > 0.32 ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        System.out.printf("%s%s: %d/%d %s", TextColor.ANSI_YELLOW, player.getName(), player.getHitPoints(), player.getMaxHitPoints(),TextColor.ANSI_RESET);
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
     * prints all items in the players inventory
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
     * prints all items in the players inventory that can be used in combat
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
     * print an ArrayList of Collectables as an option menu
     * @param arrayList
     */
    public static void displayItems(ArrayList<Collectable> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t(" + i + ") " + arrayList.get(i).toString());
        }
    }

    /**
     * prints the players current stats
     * @param player
     */
    public static void displayStats(Player player) {
        System.out.println("Your Character: " + player.getName());
        System.out.println(TextColor.ANSI_GREEN + "\tHealth: " + TextColor.ANSI_RESET + player.getHitPoints() + "/" + player.getMaxHitPoints());
        System.out.println(TextColor.ANSI_RED + "\tAttack: " + TextColor.ANSI_RESET + player.getAttackPoints());
        System.out.println(TextColor.ANSI_YELLOW + "\tGold: " + TextColor.ANSI_RESET + player.getGold());
    }

    /**
     * prints the description of the current room
     * @param player
     */
    public static void inspectRoom(Player player) {
        System.out.print("You see: ");
        player.getCurrentRoom().inspect();
    }

    /**
     * prints all doors in the current room of the player
     * @param player
     */
    public static void inspectDoors(Player player) {
        System.out.println("You look for doors.");
        System.out.println("You see:");
        ArrayList<Door> doors = player.getCurrentRoom().getDoors();
        for (int i = 0; i < doors.size(); i++) {
            System.out.print("\t(" + i + ") ");
            doors.get(i).inspect();
        }
        System.out.println("Which door will you take? (-1 to stay)");
    }

    /**
     * prints all items in the current room of the player
     * returns if there are any items in the current room
     * @param player
     * @return
     */
    public static boolean inspectItems(Player player) {
        boolean itemExists = false;
        System.out.println("You look for items.");
        System.out.println("You see:");
        ArrayList<Collectable> items = player.getCurrentRoom().getItems();
        if(items.size() > 0) {
            itemExists = true;
            for (int i = 0; i < items.size(); i++) {
                System.out.print("\t(" + i + ") ");
                System.out.println(items.get(i).toString());
            }
            System.out.println("Which item will you take? (-1 to not collect any items)");
        } else System.out.println("Nothing in sight");
        return itemExists;
    }

    /**
     * prints all npcs in the current room of the player
     * returns if there are any npcs in the current room
     * @param player
     * @return
     */
    public static boolean inspectNPCs(Player player) {
        boolean npcExists = false;
        String color = TextColor.ANSI_RESET;
        System.out.println("You look if there's somebody.");
        System.out.println("You see:");
        ArrayList<DungeonNpc> npcs = player.getCurrentRoom().getNPCs();
        if(npcs.size() > 0) {
            for (int i = 0; i < npcs.size(); i++) {
                if(npcs.get(i) instanceof Enemy) color = TextColor.ANSI_RED;
                if(npcs.get(i) instanceof Healer) color = TextColor.ANSI_GREEN;
                if(npcs.get(i) instanceof Trader) color = TextColor.ANSI_BLUE;
                System.out.print("\t(" + i + ") "+ "[" + color + npcs.get(i).getType() + TextColor.ANSI_RESET + "]" + "(" + npcs.get(i).getSpecies() + ") " + npcs.get(i).toString() + ": ");
                npcs.get(i).inspect();
            }
            npcExists = true;
            System.out.println("Who will you approach? (-1 to stay by yourself)");
        } else System.out.println("\tNobody.");
        return npcExists;
    }

    /**
     * prints the dialog when approaching a trader
     * @param trader
     */
    public static void tradeDialog(Trader trader) {
        System.out.println(trader.getName() + ": " + TextColor.ANSI_PURPLE + trader.tradeDialog() + "\n Are you interested?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) I think that is too expensive!\n\t(1) Let's trade!");
    }

    /**
     * prints the dialog when approaching a healer
     * @param healer
     */
    public static void healDialog(Healer healer) {
        System.out.println(healer.getName() + ":" + TextColor.ANSI_PURPLE + "I can only heal you once, and then I will leave!\n Are you sure you want me to heal you?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) No better do it later!\n\t(1) Heal me!");
    }

    /**
     * prints a message when using a combat item outside of combat
     */
    public static void onlyCombatItemMessage(){ System.out.println(TextColor.ANSI_YELLOW + "This item can only be used in combat." + TextColor.ANSI_RESET); }

    /**
     * prints a message when invalid input is given
     */
    public static void invalidInputMessage() { System.out.println("That is not a valid input!"); }

    /**
     * prints a message when invalid item is chosen from the inventory
     */
    public static void invalidItemMessage() { System.out.println("That is not a valid item!"); }

    /**
     * prints a message when the game is won
     */
    public static void winGameMessage(){
        System.out.println(TextColor.ANSI_YELLOW + "Congratulations you have won the game! You are a real dungeon master!" + TextColor.ANSI_RESET);
        System.out.println(TextColor.ANSI_BLUE + "This game has been brought to you by Diego and Felix." + TextColor.ANSI_RESET);
    }

    /**
     * prints a message when the player died
     */
    public static void gameOverMessage() { System.out.println(TextColor.ANSI_WHITE + "GAME OVER!" + TextColor.ANSI_RESET); }

}
