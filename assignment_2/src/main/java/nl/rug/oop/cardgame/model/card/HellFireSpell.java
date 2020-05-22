package nl.rug.oop.cardgame.model.card;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.ArrayList;

/**
 * Spell to damage enemy creatures
 */
public class HellFireSpell extends SpellCard {

    /**
     * Create new Hell Fire spell
     * @param enumCard Enum Card
     */
    public HellFireSpell(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Deal 2 damage to all enemy creatures
     * @param battlefield Battlefield
     * @param hero Hero
     * @param pos Position
     * @return Success of spell
     */
    @Override
    public boolean play(Battlefield battlefield, int hero, int pos) {
        if(hero == 1) {
            boolean playerHasCreatures = notEmptyBattlefield(battlefield.getPlayer());
            if(playerHasCreatures) {
                dealDamageToCreatures(battlefield.getPlayer());
                battlefield.setHellFire(true, battlefield.getPlayer());
                return super.play(battlefield, hero, pos);
            } else return false;
        }
        dealDamageToCreatures(battlefield.getAi());
        battlefield.setHellFire(true, battlefield.getAi());
        return super.play(battlefield, hero, pos);
    }

    /**
     * Deals damage to creatures of the given hero
     * @param hero Hero
     */
    private void dealDamageToCreatures(Hero hero) {
        ArrayList<CreatureCard> enemyCreatures = hero.getPlayedCreatures();
        for(CreatureCard c: enemyCreatures) {
            if(c != null) c.setCreatureHealth(c.getCreatureHealth() - 2);
        }
    }

}
