package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;

/**
 * Snake extends abstract class Enemy
 */
public class Snake extends Enemy implements Serializable {

    private static final long serialVersionUID = 29L;

    /**
     * Constructor for a snake
     * Attack points, hit points and gold value are all set to default values
     * @param description Description
     * @param name Name
     */
    public Snake(String description, String name) {
        super(description, name, DefaultStats.SNAKE_HIT_POINTS , DefaultStats.SNAKE_ATTACK_POINTS,
                DefaultStats.SNAKE_GOLD_VALUE);
    }

    /**
     * Constructor for a snake using only a name
     * Sets the description to the standard description
     * @param name Name
     */
    public Snake(String name) {
        this("Ssssssssssss", name);
    }

    /**
     * Return the species of this enemy
     * @return "Snake"
     */
    @Override
    public String getSpecies() {
        return "Snake";
    }
}
