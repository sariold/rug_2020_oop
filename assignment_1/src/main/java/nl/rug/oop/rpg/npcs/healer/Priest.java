package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;

/**
 * Priest extends abstract class Healer heals the player for its heal power
 */
public class Priest extends Healer implements Serializable {

    private static final long serialVersionUID = 26L;

    /**
     * Constructor for a Priest
     * Heal power is set to a default value
     * @param name Name
     */
    public Priest(String name) {
        super("May the divine spirits look upon your soul!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    /**
     * Return the species of this healer
     * @return "Priest"
     */
    @Override
    public String getSpecies() {
        return "Priest";
    }
}
