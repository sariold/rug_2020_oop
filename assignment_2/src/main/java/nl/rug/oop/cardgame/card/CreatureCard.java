package nl.rug.oop.cardgame.card;

import lombok.Data;
import nl.rug.oop.cardgame.Battlefield;
import nl.rug.oop.cardgame.interfaces.Attackable;
import nl.rug.oop.cardgame.interfaces.Attacking;

import java.awt.*;

/**
 * A type of card that summons a creature when played
 */
@Data
public class CreatureCard extends Card implements Attackable, Attacking {

    private int attack;
    private int health;

    /**
     * Create a creature card
     * @param name Name
     * @param cost Cost
     * @param image Image
     * @param attack Attack
     * @param health Health
     */
    public CreatureCard(String name, int cost, Image image, int attack, int health) {
        super(name, cost, image);
        this.attack = attack;
        this.health = health;
    }

    /**
     * Method when this creature is attacked
     */
    @Override
    public void getAttacked() {

    }

    /**
     * Attack method
     */
    @Override
    public void attack() {

    }

    /**
     * Play method
     */
    @Override
    public void play(Battlefield battlefield, int hero) {
        if (hero == 0) {
            battlefield.getPlayer().getPlayedCreatures().add(this);
            return;
        }
        battlefield.getAi().getPlayedCreatures().add(this);
    }
}
