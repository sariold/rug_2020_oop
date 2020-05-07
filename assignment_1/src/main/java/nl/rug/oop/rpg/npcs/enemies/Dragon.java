package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;
import java.util.Random;

/**
 * Dragon extends abstract class Boss
 */
public class Dragon extends Boss implements Serializable {

    private static final long serialVersionUID = 17L;

    /**
     * Constructor for a dragon
     * Attack points, hit points and gold value are all set to default values
     * @param description Description
     * @param name Name
     */
    public Dragon(String description, String name) {
        super(description, name, DefaultStats.DRAGON_HIT_POINTS , DefaultStats.DRAGON_ATTACK_POINTS,
                DefaultStats.DRAGON_GOLD_VALUE);
    }

    /**
     * Constructor for a dragon using only a name
     * Sets the description to the standard description
     * @param name Name
     */
    public Dragon(String name) {
        this("Do not test me child for I am a mighty Dragon! Engage me and perish in flames!", name);
    }

    /**
     * When a dragon attacks there is a chance to burn or freeze the attacked
     * If the attacked does not get burnt or frozen then the dragon deals its attack power as damage
     * @param attacked Attacked
     */
    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            attacked.setBurned(true);
        } else {
            chance = r.nextInt(101);
            if (chance < 21) {
                attacked.setFrozen(true);
            } else {
                super.attack(attacked);
            }
        }
    }

    /**
     * Return the species of this enemy
     * @return "Dragon"
     */
    @Override
    public String getSpecies() {
        return "Dragon";
    }
}
