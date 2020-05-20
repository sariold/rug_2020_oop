package nl.rug.oop.cardgame.model.card;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

public class DamageBuffSpell extends SpellCard {

    public DamageBuffSpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos) {
        Hero targetHero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        battlefield.setDamageBuff(targetHero, true, this.getEnumCard().getValue());
        return super.play(battlefield, heroIndex, pos);
    }
}
