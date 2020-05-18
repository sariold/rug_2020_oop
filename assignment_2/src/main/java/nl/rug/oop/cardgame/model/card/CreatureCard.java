package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.util.Attackable;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;
import nl.rug.oop.cardgame.view.textures.StatEnum;
import nl.rug.oop.cardgame.view.textures.StatTextures;

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
    private int battlePosition = -1;

    /**
     * Create a creature card
     *
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
        if (!this.isUsed()) {
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

    /**
     * Play a card
     * @param battlefield Battlefield
     * @param heroIndex   Hero that played the card 0 for player, 1 for AI
     * @return
     */
    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos, MagicStoneFrame frame) {
        this.setUsed(true);
        Hero hero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        return battlefield.placeCreature(this, hero, pos);
    }

    @Override
    public String getType() {
        return this.name;
    }

    /**
     * Checks if a creature has died if so removes it from the battlefield
     *
     * @param hero  Hero
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

    @Override
    public void display(Graphics g, MagicStonePanel panel) {
        super.display(g, panel);
        int[] coords = this.cardImage.getCoordinates();
        if (this.battlePosition == -1) {
            g.setColor(Color.BLACK);
            String attackAndHealth = (this.getAttack() + "/" + (this.getHealth()));
            g.drawString(attackAndHealth, coords[0] + 130 -
                    g.getFontMetrics().stringWidth(attackAndHealth), 670);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), coords[0] + 103, coords[1] + 10, 50, 65, panel);
            g.drawString(Integer.toString(this.getAttack()), coords[0] + 120, coords[1] + 55);
            g.drawImage(StatTextures.getTexture(StatEnum.HEART), coords[0] + 104, coords[1] + 90, 45, 45, panel);
            g.drawString(Integer.toString(this.getHealth()), coords[0] + 122, coords[1] + 115);
        }

    }
}
