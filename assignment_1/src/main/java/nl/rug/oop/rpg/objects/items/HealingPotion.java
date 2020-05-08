package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.game.GameInteract;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.player.Player;

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
     * @param player Player
     */
    @Override
    public void use(Player player) {
        int healValue = GameInteract.getHealValue(player.getHitPoints(), player.getMaxHitPoints(), this.healPower);
        GUIMessages.playerHealedMessage(healValue);
        player.setHitPoints(player.getHitPoints() + healValue);
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
     * @return Boolean
     */
    @Override
    public boolean hasNonCombatUse() {
        return true;
    }

    /**
     * Return the type of item this is
     * @return The type of item
     */
    @Override
    public String toString() {
        return "Healing Potion";
    }

    /**
     * This item can be enchanted to boosts its stats
     * @param value Value
     */
    @Override
    public void enchant(int value) {
        this.healPower = this.healPower + value;
    }
}
