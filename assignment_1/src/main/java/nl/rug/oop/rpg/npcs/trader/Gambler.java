package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;

import java.io.Serializable;
import java.util.Random;

/**
 * Gambler extends abstract class Trader increases either health attack or nothing for gold
 */
public class Gambler extends Trader implements Serializable {

    private static final long serialVersionUID = 20L;

    /**
     * Constructor for a gambler
     * power and price are set to default values
     * @param name
     */
    public Gambler(String name) {
        super("Are you feeling lucky?", name, DefaultStats.GAMBLER_POWER, DefaultStats.GAMBLER_PRICE);
    }

    /**
     * trading with a gambler has a 40% chance to increase health, 40% chance to increase attack, 20% chance to not increase stats
     * @param player
     */
    @Override
    public void trade(Player player) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 41) {
            player.increaseMaxHitPoints(this.getPower());
        }
        else if (chance < 81) {
            player.increaseAttackPoints(this.getPower());
        } else {
            System.out.println(TextColor.ANSI_YELLOW + "Nothing happened." + TextColor.ANSI_RESET);
        }
        super.trade(player);
    }

    /**
     * return species of this trader
     * @return "Gambler"
     */
    @Override
    public String getSpecies() {
        return "Gambler";
    }

    /**
     * return the trade dialog for a gambler
     * @return String
     */
    @Override
    public String tradeDialog() {
        String toReturn = "I will increase one of your stats by " + this.getPower() + " for " + this.getPrice() + " gold! However it might not work.";
        return toReturn;
    }
}
