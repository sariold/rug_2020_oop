package nl.rug.oop.cardgame.interfaces;

import nl.rug.oop.cardgame.model.Battlefield;

/**
 * Objects that can be played
 */
public interface Playable {

    /**
     * Play the object
     * @return
     */
    boolean play(Battlefield battlefield, int hero);
}
