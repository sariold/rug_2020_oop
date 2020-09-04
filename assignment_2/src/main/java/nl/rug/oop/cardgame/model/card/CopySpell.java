package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.util.Targetting;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.ArrayList;

/**
 * Spell that copies a creature and places it on the battlefield
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CopySpell extends SpellCard implements Targetting {

    private int pos;

    /**
     * Create a copy spell
     * @param enumCard EnumCard
     */
    public CopySpell(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Play the spell
     * @param battlefield Battlefield
     * @param hero Hero who played the card
     * @param pos Position of copied creature
     * @return Success of playing
     */
    @Override
    public boolean play(Battlefield battlefield, int hero, int pos) {
        this.pos = pos;
        Hero targetHero = (hero == 0 ? battlefield.getPlayer() : battlefield.getAi());
        boolean played = target(battlefield, targetHero);
        if(played) return super.play(battlefield, hero, pos);
        return false;
    }

    /**
     * Target the creature that should be copied
     * @param battlefield Battlefield
     * @param hero Hero
     * @return If creature has been targeted
     */
    @Override
    public boolean target(Battlefield battlefield, Hero hero) {
        ArrayList<Integer> freePositions = battlefield.getFreePositions(hero);
        if (notEmptyBattlefield(hero)) {
            if (!(hero instanceof AIHero)) {
                CreatureCard creatureCard = new CreatureCard(hero.getPlayedCreatures().get(pos).getEnumCard());
                creatureCard.setUsed(true);
                battlefield.placeCreature(creatureCard, hero, freePositions.get(0));
            } else {
                if (freePositions.size() > 0) {
                    ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
                    CreatureCard creatureCard = new CreatureCard(creatures.get(getStrongestCreature(battlefield.getAi())).getEnumCard());
                    creatureCard.setUsed(true);
                    battlefield.placeCreature(creatureCard, hero, freePositions.get(0));
                    System.out.println("AI COPIES " + creatureCard.getName() + " AI PLACES IN " + freePositions.get(0));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Return the strongest creature of a hero
     * @param hero Hero
     * @return Strongest Creature
     */
    private int getStrongestCreature(Hero hero) {
        int pos = -1;
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        int max = 0;
        for(CreatureCard c: creatures) {
            if(c != null) {
                if(c.getCreatureAttack() > max) pos = c.getBattlePosition();
            }
        }
        return pos;
    }
}
