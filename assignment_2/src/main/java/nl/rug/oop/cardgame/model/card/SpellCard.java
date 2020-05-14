package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Spell card
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class SpellCard extends Card {

    /**
     * Creates a new spell card
     * @param enumCard Enum Card
     */
    public SpellCard(EnumCard enumCard) {
        super(enumCard);
    }

}
