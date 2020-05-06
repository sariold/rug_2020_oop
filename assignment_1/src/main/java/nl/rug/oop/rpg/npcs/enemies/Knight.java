package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class Knight extends Enemy implements Serializable {

    private static final long serialVersionUID = 23L;

    /**
     * Constructor for a knight
     * attack points, hit points and gold value are all set to default values
     * @param description
     * @param name
     */
    public Knight(String description, String name) {
        super(description, name, DefaultStats.KNIGHT_HIT_POINTS , DefaultStats.KNIGHT_ATTACK_POINTS, DefaultStats.KNIGHT_GOLD_VALUE);
    }

    /**
     * Constructor for a knight using only a name
     * sets the description to the standard description
     * @param name
     */
    public Knight(String name) {
        this("Well met! I will fight with honor.", name);
    }

    /**
     * returns the species of this enemy
     * @return "Knight"
     */
    @Override
    public String getSpecies() {
        return "Knight";
    }
}
