package nl.rug.oop.rpg.npcs.enemies;

import java.io.Serializable;

/**
 * Abstract class MiniBoss extends abstract class Enemy must be defeated to fight the boss of the game
 */
public abstract class MiniBoss extends Enemy implements Serializable {

    private static final long serialVersionUID = 24L;

    /**
     * Constructor for a mini boss
     * @param description Description
     * @param name Name
     * @param hitPoints Hit points
     * @param attackPoints Attack points
     * @param goldValue Gold value
     */
    public MiniBoss(String description, String name, int hitPoints, int attackPoints, int goldValue) {
        super(description, name, hitPoints, attackPoints, goldValue);
    }
}
