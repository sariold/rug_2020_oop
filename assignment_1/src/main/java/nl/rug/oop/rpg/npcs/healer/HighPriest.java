package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.player.Player;

import java.io.Serializable;

/**
 * HighPriest extends abstract class Healer heals the player fully
 */
public class HighPriest extends Healer implements Serializable {

    private static final long serialVersionUID = 22L;

    /**
     * Constructor for a HighPriest
     * Heal power is set to a default value
     * @param name name
     */
    public HighPriest(String name) {
        super("God Shall bless you!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    /**
     * A high priest heals a player fully
     * @param player Player
     */
    @Override
    public void heal(Player player) {
        GUIMessages.playerHealedMessage(player.getMaxHitPoints() - player.getHitPoints());
        player.setHitPoints(player.getMaxHitPoints());
        this.engaged = true;
    }

    /**
     * Return the species of this healer
     * @return "High Priest"
     */
    @Override
    public String getSpecies() {
        return "High Priest";
    }
}
