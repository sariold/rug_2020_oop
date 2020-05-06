package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Orc extends Enemy implements Serializable {

    private static final long serialVersionUID = 25L;

    /**
     * Constructor for an orc
     * attack points, hit points and gold value are all set to default values
     * @param description
     * @param name
     */
    public Orc(String description, String name) {
        super(description, name, DefaultStats.ORC_HIT_POINTS , DefaultStats.ORC_ATTACK_POINTS, DefaultStats.ORC_GOLD_VALUE);
    }

    /**
     * Constructor for an orc using only a name
     * sets the description to the standard description
     * @param name
     */
    public Orc(String name) {
        this("FOR THE HORDE!!", name);
    }

    /**
     * return the species of this enemy
     * @return "Orc"
     */
    @Override
    public String getSpecies() {
        return "Orc";
    }
}
