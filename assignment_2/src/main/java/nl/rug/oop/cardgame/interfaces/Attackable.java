package nl.rug.oop.cardgame.interfaces;

import nl.rug.oop.cardgame.card.CreatureCard;

/**
 * Objects that can be attacked
 */
public interface Attackable {

    void setHealth(int health);

    int getHealth();

    void attack(Attackable attackable);

    int getAttack();

    void setAttack(int attack);
}
