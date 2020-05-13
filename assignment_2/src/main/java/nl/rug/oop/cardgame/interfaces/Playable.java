package nl.rug.oop.cardgame.interfaces;

import nl.rug.oop.cardgame.battlefield.Battlefield;

/**
 * Objects that can be played
 */
public interface Playable {

    /**
     * Play the object
     */
    void play(Battlefield battlefield, int hero);
}
