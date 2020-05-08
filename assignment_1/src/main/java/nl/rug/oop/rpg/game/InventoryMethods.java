package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.EnchantItem;
import nl.rug.oop.rpg.objects.items.Item;

import java.util.ArrayList;

/**
 * Class that returns certain types of inventories of a player
 */
public class InventoryMethods {

    /**
     * Returns the items arrayList of the player that can be used in combat
     * @param player Player
     * @return The items arrayList of the player that can be used in combat
     */
    public static ArrayList<Collectable> getCombatInventory(Player player) {
        ArrayList<Collectable> combatItems = new ArrayList<>();
        for (Collectable c: player.getInventory()) {
            if (c.hasCombatUse()) combatItems.add(c);
        }
        return combatItems;
    }

    /**
     * Returns the enchantable friendly arraylist inventory of a player
     * @return The enchantable items arraylist of the player
     */
    public static ArrayList<Collectable> getEnchantableInventory(Player player) {
        ArrayList<Collectable> enchantables = new ArrayList<>();
        for (Collectable c: player.getInventory()) {
            if (c instanceof EnchantItem) enchantables.add(c);
        }
        return enchantables;
    }

    /**
     * Removes used items from a players inventory
     * @param player Player
     */
    public static void removePlayerUsedItem(Player player) {
        for (int i = player.getInventory().size() - 1; i > -1; i--) {
            if (((Item) player.getInventory().get(i)).getUsed()) player.getInventory().remove(i);
        }
    }
}
