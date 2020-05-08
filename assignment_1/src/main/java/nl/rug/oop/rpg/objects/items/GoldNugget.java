package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.player.PlayerStats;

import java.io.Serializable;

/**
 * GoldNugget item which gives the player a set amount of gold
 */
public class GoldNugget extends Item implements Serializable {

    private static final long serialVersionUID = 6L;

    private final int value;

    /**
     * Creates a gold nugget with a gold value
     */
    public GoldNugget() {
        super("This nugget is worth some money.");
        this.value = DefaultStats.GOLD_NUGGET_VALUE;
    }

    /**
     * Overrides the default use method as this item gives the player a certain amount of gold
     * @param player Player
     */
    @Override
    public void use(Player player) {
        PlayerStats.increaseGold(this.value, player);
        System.out.println(TextColor.ANSI_YELLOW + "You gained " + this.value + " gold." + TextColor.ANSI_RESET);
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
        return true;
    }

    /**
     * Returning the type of item
     * @return The name of an item
     */
    @Override
    public String toString() {
        return "Gold Nugget";
    }
}
