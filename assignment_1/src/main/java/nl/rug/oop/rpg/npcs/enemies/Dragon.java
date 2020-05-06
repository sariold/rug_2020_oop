package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;
import java.util.Random;

public class Dragon extends Boss implements Serializable {

    private static final long serialVersionUID = 17L;

    /**
     * Constructor for a dragon
     * attack points, hit points and gold value are all set to default values
     * @param description
     * @param name
     */
    public Dragon(String description, String name) {
        super(description, name, DefaultStats.DRAGON_HIT_POINTS , DefaultStats.DRAGON_ATTACK_POINTS, DefaultStats.DRAGON_GOLD_VALUE);
    }

    /**
     * Constructor for a dragon using only a name
     * sets the description to the standard description
     * @param name
     */
    public Dragon(String name) {
        this("Do not test me child for I am a mighty Dragon! Engage me and perish in flames!", name);
    }

    /**
     * when a dragon attacks there is a chance to burn or freeze the attacked
     * if the attacked does not get burnt or frozen then the dragon deals its attack power as damage
     * @param attacked
     */
    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            ((Player) attacked).burn();
        } else {
            chance = r.nextInt(101);
            if (chance < 21) {
                ((Player) attacked).freeze();
            } else {
                super.attack(attacked);
            }
        }
    }

    /**
     * return the species of this enemy
     * @return "Dragon"
     */
    @Override
    public String getSpecies() {
        return "Dragon";
    }
}
