package nl.rug.oop.rpg.interfaces;

import nl.rug.oop.rpg.game.Player;

/**
 * Interface to make an object collectable by the player
 */
public interface Collectable {

    /**
     * Adds this collectable to the players inventory
     * @param player
     */
    void collect(Player player);

    /**
     * Uses this collectable and removes it from the players inventory
     * @param player
     */
    void use(Player player);

    /**
     * Checks whether this collectable can be used in combat
     * @return True if it has combat use
     */
    boolean hasCombatUse();

    /**
     * Checks whether this collectable can be used outside of combat
     * @return True if it has non combat use
     */
    boolean hasNonCombatUse();
}
