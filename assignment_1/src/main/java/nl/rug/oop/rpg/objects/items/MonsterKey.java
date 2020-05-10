package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.player.Player;

import java.io.Serializable;

/**
 * Lets the player open a monster door, regular game item
 */
public class MonsterKey extends Item implements Serializable {

    private static final long serialVersionUID = 38L;


    /**
     * A key that can open monster doors so the player does not have to kill all enemy npcs in order to open the door
     *
     */
    public MonsterKey() {
        super("Open monster doors easily!");
    }

    /**
     * Overrides the default use method as this item gives the player a certain amount of gold
     * @param player Player
     */
    @Override
    public void use(Player player) {
        System.out.println(TextColor.ANSI_BLUE + "Nice job bypassing monster door requirements!"
                + TextColor.ANSI_RESET);
        super.use(player);
    }

    /**
     * This item has no use during combat so set to false
     * @return Boolean value of item's combat use
     */
    @Override
    public boolean hasCombatUse() {
        return false;
    }

    /**
     * This item has no combat use
     * @return Boolean value if item has non combat use
     */
    @Override
    public boolean hasNonCombatUse() {
        return false;
    }

    /**
     * Returning the type of item
     * @return The name of an item
     */
    @Override
    public String toString() {
        return "Monster Key";
    }


}
