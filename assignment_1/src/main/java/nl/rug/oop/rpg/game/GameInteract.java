package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.gui.GUI;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.DungeonNpc;
import nl.rug.oop.rpg.npcs.healer.Healer;
import nl.rug.oop.rpg.npcs.trader.Trader;
import nl.rug.oop.rpg.objects.doors.Door;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to store Game object interaction methods
 */
public class GameInteract {

    /**
     * Allows the player to interact with a door, depending on the door, this might mean they will engage in combat
     * or lose damage
     * @param currentMove Current move
     */
    public static void interactDoor(int currentMove, Game game) {
        Player player = game.getPlayer();
        ArrayList<Door> doors = player.getCurrentRoom().getDoors();
        if (currentMove < doors.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You stayed in the same room.");
                return;
            }
            Door currentDoor = doors.get(currentMove);
            currentDoor.engage(player, game);
        }
    }

    /**
     * Allows the player to interact with items and collect them
     * @param currentMove Current move
     */
    public static void interactItem(int currentMove, Game game) {
        Player player = game.getPlayer();
        ArrayList<Collectable> items = player.getCurrentRoom().getItems();
        if (currentMove < items.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You did not collect any items.");
                return;
            }
            items.get(currentMove).collect(player);
            player.getCurrentRoom().removeItem();
            System.out.println("You collected " + items.get(currentMove).toString());
        }
    }

    /**
     * Allows the player to interact with npcs, might engage them in combat or allow them to trade/get healed
     * @param currentMove Current move
     */
    public static void interactNPC(int currentMove, Game game) {
        Player player = game.getPlayer();
        ArrayList<DungeonNpc> npcs = player.getCurrentRoom().getNPCs();
        if (currentMove < npcs.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You decided to leave them alone.");
                return;
            }
            DungeonNpc currentNPC = npcs.get(currentMove);
            currentNPC.engage(player, game);
        }
    }

    /**
     * Allows the player to trade with an npc if they have enough gold
     * @param player Player
     * @param trader Trader
     */
    public static void tradeWith(Player player, Trader trader) {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.tradeDialog(trader);
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUIMessages.invalidInputMessage();
            return;
        }
        if (move == 1){
            if (player.getGold() < trader.getPrice()) {
                System.out.println("You do not have enough gold!");
                return;
            }
            trader.interact(player);
            player.getCurrentRoom().removeDeadNPC();
        } else if(move > 1) GUIMessages.invalidInputMessage();
    }

    /**
     * Allows the player to be healed if they have enough gold
     * @param player Player
     * @param healer Healer
     */
    public static void healPlayer(Player player, Healer healer) {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.healDialog(healer);
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUIMessages.invalidInputMessage();
            return;
        }
        if (move == 1){
            healer.interact(player);
            System.out.println("You are at " + player.getHitPoints() + " health.");
            player.getCurrentRoom().removeDeadNPC();
        } else if(move > 1) GUIMessages.invalidInputMessage();
    }

    /**
     * Returns the value to heal given a maximum health
     * @param currHealth Current Health
     * @param maxHealth Maximum Health
     * @param heal Maximum Heal
     * @return Value to heal
     */
    public static int getHealValue(int currHealth, int maxHealth, int heal) {
        return currHealth + heal > maxHealth ? maxHealth - currHealth : heal;
    }
}
