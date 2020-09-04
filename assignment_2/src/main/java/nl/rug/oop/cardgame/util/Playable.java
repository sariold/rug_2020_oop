package nl.rug.oop.cardgame.util;

import nl.rug.oop.cardgame.model.Battlefield;

/**
 * Objects that can be played
 */
public interface Playable {

    /**
     * Play the object
     * @param battlefield battlefield
     * @param hero herp
     * @param pos pos
     * @return boolean if card has been played
     */
    boolean play(Battlefield battlefield, int hero, int pos);
}
