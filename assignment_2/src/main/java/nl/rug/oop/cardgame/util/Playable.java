package nl.rug.oop.cardgame.util;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

/**
 * Objects that can be played
 */
public interface Playable {

    /**
     * Play the object
     *
     * @return
     */
    boolean play(Battlefield battlefield, int hero, int pos, MagicStoneFrame frame);
}
