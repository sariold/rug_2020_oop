package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

public class StrongArmorSmith extends Trader{

    public StrongArmorSmith(String name) {
        super("Armor is not to be joked with!", name, DefaultStats.STRONG_ARMORSMITH_POWER, DefaultStats.STRONG_ARMORSMITH_PRICE);
    }

    @Override
    public void trade(Player player) {
        player.increaseMaxHitPoints(this.getPower());
        super.trade(player);
    }

    @Override
    public String getSpecies() {
        return "Excellent Armorsmith";
    }

    @Override
    public String tradeDialog() {
        String toReturn = "I will increase your maximum health by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}
