package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import java.util.ArrayList;

/**
 * Spell card
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SpellCard extends Card {

    /**
     * Creates a new spell card
     *
     * @param enumCard Enum Card
     */
    public SpellCard(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos, MagicStoneFrame frame) {
        Hero hero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        hero.getDiscardDeck().discard(this);
        return true;
    }

    public boolean notEmptyBattlefield(Hero hero) {
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        for (CreatureCard c : creatures) {
            if (c != null) return true;
        }
        return false;
    }

}
