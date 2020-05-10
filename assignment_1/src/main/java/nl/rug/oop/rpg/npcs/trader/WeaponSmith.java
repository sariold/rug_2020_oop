package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.player.PlayerStats;

import java.io.Serializable;

/**
 * WeaponSmith extends abstract class Trader increases players attack for gold
 */
public class WeaponSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 34L;

    /**
     * Constructor for a weapon smith
     * Power and price are set to default values
     * @param name Name
     */
    public WeaponSmith(String name) {
        super("Better get them before they get you!", name, DefaultStats.WEAPONSMITH_POWER,
                DefaultStats.WEAPONSMITH_PRICE);
    }

    /**
     * Trading results in increase of attack of the player
     * @param player Player
     */
    @Override
    public void trade(Player player) {
        PlayerStats.increaseAttackPoints(this.getPower(), player);
        super.trade(player);
    }

    /**
     * Return the species of this trader
     * @return "Weaponsmith"
     */
    @Override
    public String getSpecies() {
        return "Weaponsmith";
    }

    /**
     * Return the trade dialog for this trader
     * @return String
     */
    @Override
    public String tradeDialog() {
        return "I will increase your attack power by " + this.getPower() + " for " + this.getPrice() + " gold!";
    }
}
