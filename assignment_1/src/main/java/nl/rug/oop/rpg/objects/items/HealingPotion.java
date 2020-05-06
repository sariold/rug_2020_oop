package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

/**
 * Creates a healing potion which can be enchanted
 */
public class HealingPotion extends EnchantItem implements Serializable {

    private static final long serialVersionUID = 11L;

    private int healPower;

    /**
     * Healing potion constructor which sets its healing power
     */
    public HealingPotion() {
        super("This potion restores health.");
        this.healPower = DefaultStats.POTION_HEAL_POWER;
    }

    /**
     * Overrides the default user method as this healing potion will give the player a health increase
     * @param player
     */
    @Override
    public void use(Player player) {
        player.increaseHitPoints(this.healPower);
//        System.out.println(TextColor.ANSI_GREEN + "You have been healed for " + this.healPower + " health." + TextColor.ANSI_RESET);
        super.use(player);
    }

    /**
     * This item has combat use so set to true
     */
    @Override
    public boolean hasCombatUse() {
        return true;
    }

    /**
     * This item can be used outside combat
     * @return
     */
    @Override
    public boolean hasNonCombatUse() {
        return true;
    }

    /**
     * Return the type of item this is
     * @return
     */
    @Override
    public String toString() {
        return "Healing Potion";
    }

    /**
     * This item can be enchanted to boosts its stats
     * @param value
     */
    @Override
    public void enchant(int value) {
        this.healPower = this.healPower + value;
    }
}
