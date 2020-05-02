package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public class HighPriest extends Healer implements Serializable {

    private static final long serialVersionUID = 22L;

    public HighPriest(String name) {
        super("God Shall bless you!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    @Override
    public void heal(Player player) {
//        System.out.println(TextColor.ANSI_GREEN + "You have been fully healed." + TextColor.ANSI_RESET);
        player.increaseHitPoints(player.getMaxHitPoints());
    }

    @Override
    public String getSpecies() {
        return "High Priest";
    }
}
