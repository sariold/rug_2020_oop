package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.interfaces.Attackable;

import java.awt.*;

/**
 * A type of card that summons a creature when played
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreatureCard extends Card implements Attackable {

    private int creatureAttack;
    private int creatureHealth;
    private boolean used;

    /**
     * Create a creature card
     * @param name Name
     * @param cost Cost
     * @param image Image
     * @param creatureAttack Attack
     * @param creatureHealth Health
     * @param used Has the card been used or just played?
     */
    public CreatureCard(String name, int cost, Image image, int creatureAttack, int creatureHealth, boolean used) {
        super(name, cost, image);
        this.creatureAttack = creatureAttack;
        this.creatureHealth = creatureHealth;
        this.used = used;
    }

    /**
     * Set health
     * @param health Health
     */
    @Override
    public void setHealth(int health) {
        this.setCreatureHealth(health);
    }

    /**
     * Get health
     * @return Health
     */
    @Override
    public int getHealth() {
        return this.getCreatureHealth();
    }

    /**
     * Attack method
     * @param attackable Pass in creature or hero
     */
    @Override
    public void attack(Attackable attackable) {
        attackable.setHealth(attackable.getHealth() - this.getAttack());
    }

    /**
     * Return attack
     * @return Attack
     */
    @Override
    public int getAttack() {
        return this.getCreatureAttack();
    }

    /**
     * Sets attack
     * @param attack Attack
     */
    @Override
    public void setAttack(int attack) {
        this.setCreatureAttack(attack);
    }

    /** Play a card
     * @param battlefield Battlefield
     * @param hero Hero
     */
    @Override
    public void play(Battlefield battlefield, int hero) {
        this.setUsed(true);
        if (hero == 0) {
            battlefield.getPlayer().getPlayedCreatures().add(this);
            return;
        }
        battlefield.getAi().getPlayedCreatures().add(this);
    }
}
