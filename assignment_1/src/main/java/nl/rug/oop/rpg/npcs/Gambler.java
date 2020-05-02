package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;

import java.io.Serializable;
import java.util.Random;

public class Gambler extends Trader implements Serializable {

    private static final long serialVersionUID = 20L;

    public Gambler(String name) {
        super("Are you feeling lucky?", name, DefaultStats.GAMBLER_POWER, DefaultStats.GAMBLER_PRICE);
    }

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

    @Override
    public String getSpecies() {
        return "Gambler";
    }

    @Override
    public String tradeDialog() {
        String toReturn = "I will increase one of your stats by " + this.getPower() + " for " + this.getPrice() + " gold! However it might not work.";
        return toReturn;
    }
}
