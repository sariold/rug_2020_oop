package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Orc extends Enemy implements Serializable {

    private static final long serialVersionUID = 25L;

    public Orc(String description, String name) {
        super(description, name, DefaultStats.ORC_HIT_POINTS , DefaultStats.ORC_ATTACK_POINTS, DefaultStats.ORC_GOLD_VALUE);
    }

    public Orc(String name) {
        this("FOR THE HORDE!!", name);
    }

    @Override
    public String getSpecies() {
        return "Orc";
    }
}
