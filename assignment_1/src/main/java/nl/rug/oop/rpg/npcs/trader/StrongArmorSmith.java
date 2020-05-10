package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.player.PlayerStats;

import java.io.Serializable;

/**
 * StrongArmorSmith extends abstract class Trader increases maximum health of player for gold
 */
public class StrongArmorSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 31L;

    /**
     * Constructor for a strong armor smith
     * Power and price are set to default values
     * @param name Name
     */
    public StrongArmorSmith(String name) {
        super("Armor is not to be joked with!", name, DefaultStats.STRONG_ARMORSMITH_POWER,
                DefaultStats.STRONG_ARMORSMITH_PRICE);
    }

    /**
     * Trading results in increase of health of the player
     * @param player Player
     */
    @Override
    public void trade(Player player) {
        PlayerStats.increaseMaxHitPoints(this.getPower(), player);
        super.trade(player);
    }

    /**
     * Return the species of this trader
     * @return "Excellent Armorsmith"
     */
    @Override
    public String getSpecies() {
        return "Excellent Armorsmith";
    }

    /**
     * Return the trade dialog for a strong armor smith
     * @return String
     */
    @Override
    public String tradeDialog() {
        return "I will increase your maximum health by " + this.getPower()
                + " for " + this.getPrice() + " gold!";
    }
}
