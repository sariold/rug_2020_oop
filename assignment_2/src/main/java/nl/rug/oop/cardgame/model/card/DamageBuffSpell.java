package nl.rug.oop.cardgame.model.card;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * Increase Hero damage until the next turn
 */
public class DamageBuffSpell extends SpellCard {

    /**
     * Create damage buff spell
     * @param enumCard EnumCard
     */
    public DamageBuffSpell(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Increase the Heros attack until the next turn
     * @param battlefield Battlefield
     * @param heroIndex Hero
     * @param pos Postion
     * @return Success of spell
     */
    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos) {
        Hero targetHero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        battlefield.setDamageBuff(targetHero, true, this.getEnumCard().getValue());
        return super.play(battlefield, heroIndex, pos);
    }
}
