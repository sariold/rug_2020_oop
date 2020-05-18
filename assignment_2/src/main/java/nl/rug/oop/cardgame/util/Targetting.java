package nl.rug.oop.cardgame.util;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

public interface Targetting {

    boolean target(Battlefield battlefield, Hero hero);

}
