package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Spider extends Enemy implements Serializable {

    private static final long serialVersionUID = 30L;

    public Spider(String description, String name) {
        super(description, name, DefaultStats.SPIDER_HIT_POINTS , DefaultStats.SPIDER_ATTACK_POINTS, DefaultStats.SPIDER_GOLD_VALUE);
    }

    public Spider(String name) {
        this("*loud clicking*", name);
    }

    @Override
    public String getSpecies() {
        return "Spider";
    }
}
