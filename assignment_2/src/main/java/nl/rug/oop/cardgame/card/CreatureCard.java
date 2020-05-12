package nl.rug.oop.cardgame.card;

import nl.rug.oop.cardgame.interfaces.Attackable;
import nl.rug.oop.cardgame.interfaces.Attacking;

import java.awt.*;

/**
 * A type of card that summons a creature when played
 */
public class CreatureCard extends Card implements Attackable, Attacking {

    private int attack;
    private int health;

    /**
     * Create a creature card
     * @param name
     * @param cost
     * @param image
     * @param attack
     * @param health
     */
    public CreatureCard(String name, int cost, Image image, int attack, int health) {
        super(name, cost, image);
        this.attack = attack;
        this.health = health;
    }

    @Override
    public void getAttacked() {

    }

    @Override
    public void attack() {

    }

    @Override
    public void play() {
        super.play();
    }
}
