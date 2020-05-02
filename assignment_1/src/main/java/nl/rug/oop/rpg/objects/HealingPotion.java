package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public class HealingPotion extends Item implements Serializable {

    private static final long serialVersionUID = 11L;

    private int healPower;

    public HealingPotion() {
        super("This potion restores health.");
        this.healPower = DefaultStats.POTION_HEAL_POWER;
    }

    @Override
    public void use(Player player) {
        player.increaseHitPoints(this.healPower);
//        System.out.println(TextColor.ANSI_GREEN + "You have been healed for " + this.healPower + " health." + TextColor.ANSI_RESET);
        super.use(player);
    }

    @Override
    public String toString() {
        return "Healing Potion";
    }
}
