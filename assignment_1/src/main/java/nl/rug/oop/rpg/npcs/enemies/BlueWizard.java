package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.extra.DefaultStats;

import java.io.Serializable;
import java.util.Random;

/**
 * BlueWizard extends abstract class MiniBoss
 */
public class BlueWizard extends MiniBoss implements Serializable {

    private static final long serialVersionUID = 15L;

    /**
     * Constructor for a blue wizard
     * Sets the door associated with this mini boss to door
     * Attack points, hit points and gold value are all set to default values
     * @param description Description
     * @param name Name
     */
    public BlueWizard(String description, String name) {
        super(description, name, DefaultStats.WIZARD_HIT_POINTS , DefaultStats.WIZARD_ATTACK_POINTS,
                DefaultStats.WIZARD_GOLD_VALUE);
    }

    /**
     * Constructor for a blue wizard using only a name
     * Sets the door associated with this mini boss to null
     * @param name Name
     */
    public BlueWizard(String name) {
        this("You will freeze to Death!!", name);
    }

    /**
     * Defeating a blue wizard adds ice magic to the combat options of the game
     * @param game Game
     */
    @Override
    public void die(Game game) {
        game.addIceMagic();
        super.die(game);
    }

    /**
     * When a blue wizard attacks there is a chance to freeze the attacked
     * If the attacked does not get frozen then the blue wizard deals its attack power as damage
     * @param attacked Attacked
     */
    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            attacked.setFrozen(true);
        }
        super.attack(attacked);
    }

    /**
     * Returns the species of this enemy
     * @return "Blue wizard"
     */
    @Override
    public String getSpecies() {
        return "Blue Wizard";
    }
}
