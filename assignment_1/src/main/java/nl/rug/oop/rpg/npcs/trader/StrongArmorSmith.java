package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

/**
 * StrongArmorSmith extends abstract class Trader increases maximum health of player for gold
 */
public class StrongArmorSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 31L;

    /**
     * Constructor for a strong armor smith
     * power and price are set to default values
     * @param name
     */
    public StrongArmorSmith(String name) {
        super("Armor is not to be joked with!", name, DefaultStats.STRONG_ARMORSMITH_POWER, DefaultStats.STRONG_ARMORSMITH_PRICE);
    }

    /**
     * trading results in increase of health of the player
     * @param player
     */
    @Override
    public void trade(Player player) {
        player.increaseMaxHitPoints(this.getPower());
        super.trade(player);
    }

    /**
     * return the species of this trader
     * @return "Excellent Armorsmith"
     */
    @Override
    public String getSpecies() {
        return "Excellent Armorsmith";
    }

    /**
     * return the trade dialog for a strong armor smith
     * @return String
     */
    @Override
    public String tradeDialog() {
        String toReturn = "I will increase your maximum health by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}
