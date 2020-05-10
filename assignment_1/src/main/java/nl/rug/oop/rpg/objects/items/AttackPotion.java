package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.interfaces.Enchantable;
import nl.rug.oop.rpg.player.Player;

/**
 * Attack poition extends abstract class Item increases attack value for a player until after the next fight
 * can be used in an outside of combat and is enchatable
 */
public class AttackPotion extends EnchantItem implements Enchantable {

    private static final long serialVersionUID = 37L;

    private int attackPower;

    /**
     * Attack potion constructor which sets its attack power
     */
    public AttackPotion() {
        super("This potion increases your attack until after the next fight.");
        this.attackPower = DefaultStats.POTION_ATTACK_POWER;
    }

    /**
     * Overrides the default user method as this attack potion will give the player an attack increase
     * @param player Player
     */
    @Override
    public void use(Player player) {
        GUIMessages.attackPotionMessage(this.attackPower);
        player.setBonusAttackPoints(player.getBonusAttackPoints() + this.attackPower);
        super.use(player);
    }

    /**
     * Returns that this item can be used in combat
     * @return true
     */
    @Override
    public boolean hasCombatUse() {
        return true;
    }

    /**
     * Returns that this item can be used outside of combat
     * @return true
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
        return "Attack Potion";
    }

    /**
     * increases this attack value by the given value
     * @param value Value
     */
    @Override
    public void enchant(int value) { this.attackPower += value; }
}
