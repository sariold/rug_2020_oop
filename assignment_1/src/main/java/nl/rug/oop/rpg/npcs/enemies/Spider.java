package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;

/**
 * Spider extends abstract class Enemy
 */
public class Spider extends Enemy implements Serializable {

    private static final long serialVersionUID = 30L;

    /**
     * Constructor for a spider
     * Attack points, hit points and gold value are all set to default values
     * @param description Description
     * @param name Name
     */
    public Spider(String description, String name) {
        super(description, name, DefaultStats.SPIDER_HIT_POINTS , DefaultStats.SPIDER_ATTACK_POINTS,
                DefaultStats.SPIDER_GOLD_VALUE);
    }

    /**
     * Constructor for a spider using only a name
     * Sets the description to the standard description
     * @param name Name
     */
    public Spider(String name) {
        this("*loud clicking*", name);
    }

    /**
     * Return the species of this enemy
     * @return "Spider"
     */
    @Override
    public String getSpecies() {
        return "Spider";
    }
}
