package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;

/**
 * Rat extends abstract class Enemy
 */
public class Rat extends Enemy implements Serializable {

    private static final long serialVersionUID = 27L;

    /**
     * Constructor for a rat
     * Attack points, hit points and gold value are all set to default values
     * @param description
     * @param name
     */
    public Rat(String description, String name) {
        super(description, name, DefaultStats.RAT_HIT_POINTS , DefaultStats.RAT_ATTACK_POINTS,
                DefaultStats.RAT_GOLD_VALUE);
    }

    /**
     * Constructor for a rat using only a name
     * Sets the description to the standard description
     * @param name
     */
    public Rat(String name) {
        this("*angrily* SQUEEK!!", name);
    }

    /**
     * Return the species of this enemy
     * @return "Rat"
     */
    @Override
    public String getSpecies() {
        return "Rat";
    }
}
