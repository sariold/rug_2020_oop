package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;
import java.util.Random;

public class Dragon extends Boss implements Serializable {

    private static final long serialVersionUID = 17L;

    public Dragon(String description, String name) {
        super(description, name, DefaultStats.DRAGON_HIT_POINTS , DefaultStats.DRAGON_ATTACK_POINTS, DefaultStats.DRAGON_GOLD_VALUE);
    }

    public Dragon(String name) {
        this("Do not test me child for I am a mighty Dragon! Engage me and perish in flames!", name);
    }

    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            ((Player) attacked).burn();
        } else {
            chance = r.nextInt(101);
            if (chance < 21) {
                ((Player) attacked).freeze();
            } else {
                super.attack(attacked);
            }
        }
    }

    @Override
    public String getSpecies() {
        return "Dragon";
    }
}
