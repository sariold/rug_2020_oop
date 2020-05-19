package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import java.awt.*;

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

}
