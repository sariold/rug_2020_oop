package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

/**
 * WeaponSmith extends abstract class Trader increases players attack for gold
 */
public class WeaponSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 34L;

    /**
     * Constructor for a weapon smith
     * power and price are set to default values
     * @param name
     */
    public WeaponSmith(String name) {
        super("Better get them before they get you!", name, DefaultStats.WEAPONSMITH_POWER, DefaultStats.WEAPONSMITH_PRICE);
    }

    /**
     * trading results in increase of attack of the player
     * @param player
     */
    @Override
    public void trade(Player player) {
        player.increaseAttackPoints(this.getPower());
        super.trade(player);
    }

    /**
     * return the species of this trader
     * @return "Weaponsmith"
     */
    @Override
    public String getSpecies() {
        return "Weaponsmith";
    }

    /**
     * return the trade dialog for this trader
     * @return String
     */
    @Override
    public String tradeDialog() {
        String toReturn = "I will increase your attack power by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}
