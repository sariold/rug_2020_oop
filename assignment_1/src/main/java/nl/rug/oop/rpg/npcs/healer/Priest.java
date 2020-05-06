package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

/**
 * Priest extends abstract class Healer heals the player for its heal power
 */
public class Priest extends Healer implements Serializable {

    private static final long serialVersionUID = 26L;

    /**
     * Constructor for a Priest
     * heal power is set to a default value
     * @param name
     */
    public Priest(String name) {
        super("May the divine spirits look upon your soul!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    /**
     * return the species of this healer
     * @return "Priest"
     */
    @Override
    public String getSpecies() {
        return "Priest";
    }
}
