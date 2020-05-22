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
     * @param enumCard Enum Card
     */
    public SpellCard(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Play a spell
     * @param battlefield Battlefield
     * @param heroIndex Hero
     * @param pos Position
     * @return Success of spell
     */
    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos) {
        Hero hero = (heroIndex == 0 ? battlefield.getPlayer() : battlefield.getAi());
        hero.getDiscardDeck().discard(this);
        return true;
    }

    /**
     * Checks if the hero has creatures on the battlefield
     * @param hero Hero
     * @return True if at least one creature is on the battlefield
     */
    public boolean notEmptyBattlefield(Hero hero) {
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        for (CreatureCard c : creatures) {
            if (c != null) return true;
        }
        return false;
    }

}
