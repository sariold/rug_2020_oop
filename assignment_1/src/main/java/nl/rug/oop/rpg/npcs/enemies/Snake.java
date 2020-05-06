package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Snake extends Enemy implements Serializable {

    private static final long serialVersionUID = 29L;

    public Snake(String description, String name) {
        super(description, name, DefaultStats.SNAKE_HIT_POINTS , DefaultStats.SNAKE_ATTACK_POINTS, DefaultStats.SNAKE_GOLD_VALUE);
    }

    public Snake(String name) {
        this("Ssssssssssss", name);
    }

    @Override
    public String getSpecies() {
        return "Snake";
    }
}
