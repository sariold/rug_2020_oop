package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

public class StrongWeaponSmith extends Trader{

    public StrongWeaponSmith(String name) {
        super("Attack is the only defense!", name, DefaultStats.STRONG_WEAPONSMITH_POWER, DefaultStats.STRONG_WEAPONSMITH_PRICE);
    }

    @Override
    public void trade(Player player) {
        player.increaseAttackPoints(this.getPower());
        super.trade(player);
    }

    @Override
    public String getSpecies() {
        return "Excellent Weaponsmith";
    }

    @Override
    public String tradeDialog() {
        String toReturn = "I will increase your attack power by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}
