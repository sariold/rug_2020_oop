package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.EnchantItem;

import java.util.ArrayList;

public class InventoryMethods {

    /**
     * returns the items arrayList of the player that can be used in combat
     * @param player
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
}
