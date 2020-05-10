package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.player.PlayerStats;

import java.io.Serializable;

/**
 * ArmorSmith extends abstract class Trader increases maximum health of the player for gold
 */
public class ArmorSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 14L;

    /**
     * Constructor for an armor smith
     * Power and price are set to default values
     * @param name Name
     */
    public ArmorSmith(String name) {
        super("Shields and chestplates have saved my life!", name, DefaultStats.ARMORSMITH_POWER,
                DefaultStats.ARMORSMITH_PRICE);
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
     * @return "Armorsmith"
     */
    @Override
    public String getSpecies() {
        return "Armorsmith";
    }

    /**
     * Return the trade dialog for an armor smith
     * @return String
     */
    @Override
    public String tradeDialog() {
        return "I will increase your maximum health by " + this.getPower() + " for "
                + this.getPrice() + " gold!";
    }
}
