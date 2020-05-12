package nl.rug.oop.cardgame.interfaces;

import nl.rug.oop.cardgame.Battlefield;

/**
 * Objects that can be played
 */
public interface Playable {

    /**
     * Play the object
     */
    void play(Battlefield battlefield, int hero);
}
