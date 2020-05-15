package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.interfaces.Attackable;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * A type of card that summons a creature when played
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreatureCard extends Card implements Attackable {

    private int creatureAttack;
    private int creatureHealth;
    private boolean used;
    private int battlePosition = -1;

    /**
     * Create a creature card
     * @param enumCard Enum Card
     */
    public CreatureCard(EnumCard enumCard) {
        super(enumCard);
        this.creatureAttack = enumCard.getAttack();
        this.creatureHealth = enumCard.getHealth();
        this.used = false;
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
        if(!this.isUsed()) {
            attackable.setHealth(attackable.getHealth() - this.getAttack());
            this.setHealth(this.getHealth() - attackable.getAttack());
            this.setUsed(true);
        }
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
     * @param heroIndex Hero that played the card 0 for player, 1 for AI
     * @return
     */
    @Override
    public boolean play(Battlefield battlefield, int heroIndex) {
        this.setUsed(true);
        Hero hero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        return battlefield.placeCreature(this, hero);
    }

    @Override
    public String getType() {
        return this.name;
    }

    /**
     * Checks if a creature has died if so removes it from the battlefield
     * @param hero Hero
     * @param index Index in played creatures array list
     */
    public void checkDeath(Hero hero, int index) {
        if (this.getCreatureHealth() <= 0) {
            System.out.println(this.getName() + " has died in combat");
            System.out.println(index);
            hero.getPlayedCreatures().set(index, null);
            this.setBattlePosition(-1);
            hero.getDiscardDeck().discard(this);
        }
    }
}
