package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.player.PlayerStats;

import java.io.Serializable;

/**
 * StrongWeaponSmith extends abstract class Trader increases players attack for gold
 */
public class StrongWeaponSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 32L;

    /**
     * Constructor for a strong weapon smith
     * Power and price are set to default values
     * @param name Name
     */
    public StrongWeaponSmith(String name) {
        super("Attack is the only defense!", name, DefaultStats.STRONG_WEAPONSMITH_POWER,
                DefaultStats.STRONG_WEAPONSMITH_PRICE);
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
     * @return "Excellent Weaponsmith"
     */
    @Override
    public String getSpecies() {
        return "Excellent Weaponsmith";
    }

    /**
     * Return the trade dialog for this trader
     * @return String
     */
    @Override
    public String tradeDialog() {
        return "I will increase your attack power by " + this.getPower() + " for "
                + this.getPrice() + " gold!";
    }
}
