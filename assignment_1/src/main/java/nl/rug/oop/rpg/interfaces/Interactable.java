package nl.rug.oop.rpg.interfaces;

import nl.rug.oop.rpg.Player;

/**
 * interface to make object interactable
 */
public interface Interactable {

    /**
     * triggers an interaction between the interactable and the player objects
     * @param player
     */
    void interact(Player player);
}
