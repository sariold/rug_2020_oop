package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;

public class Snake extends Enemy {

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
