package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public class StrongArmorSmith extends Trader implements Serializable {

    private static final long serialVersionUID = 31L;

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
