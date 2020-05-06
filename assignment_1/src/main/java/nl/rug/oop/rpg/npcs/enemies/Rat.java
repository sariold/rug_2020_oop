package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Rat extends Enemy implements Serializable {

    private static final long serialVersionUID = 27L;

    public Rat(String description, String name) {
        super(description, name, DefaultStats.RAT_HIT_POINTS , DefaultStats.RAT_ATTACK_POINTS, DefaultStats.RAT_GOLD_VALUE);
    }

    public Rat(String name) {
        this("*angrily* SQUEEK!!", name);
    }

    @Override
    public String getSpecies() {
        return "Rat";
    }
}
