package nl.rug.oop.cardgame.util;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * Targetting interface
 */
public interface Targetting {

    /**
     * Target function for spell cards
     * @param battlefield battlefield
     * @param hero hero
     * @return boolean if targetting was a success
     */
    boolean target(Battlefield battlefield, Hero hero);

}
