package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * Spell to draw cards
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DrawSpell extends SpellCard {

    /**
     * Create Draw Spell
     * @param enumCard EnumCard
     */
    public DrawSpell(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Draw two cards
     * @param battlefield Battlefield
     * @param hero Hero
     * @param pos Position
     * @return Success of spell
     */
    @Override
    public boolean play(Battlefield battlefield, int hero, int pos) {
        Hero h = (hero == 0 ? battlefield.getPlayer() : battlefield.getAi());
        for (int i = 0; i < 2; i++) {
            Card card = h.getDeck().drawCard();
            if (card != null) {
                h.getDeckHand().addCard(h.getDiscardDeck(), card);
            }
        }
        return super.play(battlefield, hero, pos);
    }


}
