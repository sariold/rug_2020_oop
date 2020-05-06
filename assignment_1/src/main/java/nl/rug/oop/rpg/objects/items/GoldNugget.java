package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;

import java.io.Serializable;

/**
 * GoldNugget item which gives the player a set amount of gold
 */
public class GoldNugget extends Item implements Serializable {

    private static final long serialVersionUID = 6L;

    private int value;

    /**
     * Creats a gold nugget with a gold value
     */
    public GoldNugget() {
        super("This nugget is worth some money.");
        this.value = DefaultStats.GOLD_NUGGET_VALUE;
    }

    /**
     * Overrides the default use method as this item gives the player a certain amount of gold
     * @param player
     */
    @Override
    public void use(Player player) {
        player.increaseGold(this.value);
        System.out.println(TextColor.ANSI_YELLOW + "You gained " + this.value + " gold." + TextColor.ANSI_RESET);
        super.use(player);
    }

    /**
     * This item has no use during combat so set to false
     * @return
     */
    @Override
    public boolean hasCombatUse() {
        return false;
    }

    /**
     * This item has no combat use
     * @return
     */
    @Override
    public boolean hasNonCombatUse() {
        return true;
    }

    /**
     * Printing the type of item
     * @return
     */
    @Override
    public String toString() {
        return "Gold Nugget";
    }
}
