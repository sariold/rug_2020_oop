package nl.rug.oop.rpg.gui;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.DungeonNpc;
import nl.rug.oop.rpg.objects.doors.Door;

import java.util.ArrayList;

/**
 * GUI Class inspection methods
 */
public class GUInspect {

    /**
     * Prints the description of the current room
     * @param player Player
     */
    public static void inspectRoom(Player player) {
        System.out.print("You see: ");
        player.getCurrentRoom().inspect();
    }

    /**
     * Prints all doors in the current room of the player
     * @param player Player
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
     * Prints all items in the current room of the player
     * Returns if there are any items in the current room
     * @param player Player
     * @return If an item exists
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
     * Prints all npcs in the current room of the player
     * Returns if there are any npcs in the current room
     * @param player Player
     * @return If an npc exists
     */
    public static boolean inspectNPCs(Player player) {
        boolean npcExists = false;
        System.out.println("You look if there's somebody.");
        System.out.println("You see:");
        ArrayList<DungeonNpc> npcs = player.getCurrentRoom().getNPCs();
        if(npcs.size() > 0) {
            for (int i = 0; i < npcs.size(); i++) {
                System.out.print("\t(" + i + ") "+ "[" + npcs.get(i).getType() + "]" + "(" + npcs.get(i).getSpecies()
                        + ") " + npcs.get(i).getName() + ": ");
                npcs.get(i).inspect();
            }
            npcExists = true;
            System.out.println("Who will you approach? (-1 to stay by yourself)");
        } else System.out.println("\tNobody.");
        return npcExists;
    }

}
