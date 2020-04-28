package nl.rug.oop.rpg;

import java.util.Random;

public class Gambler extends Trader{

    public Gambler(String name) {
        super("Are you feeling lucky?", name, DefaultStats.GAMBLER_POWER, DefaultStats.GAMBLER_PRICE);
    }

    @Override
    public void trade(Player player) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 41) {
            player.increaseMaxHitPoints(this.getPower());
            System.out.println("Your health increased by" + this.getPower() + ".");
        }
        else if (chance < 81) {
            player.increaseAttackPoints(this.getPower());
            System.out.println("Your attack increased by" + this.getPower() + ".");
        } else {
            System.out.println("Nothing happened.");
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
