package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

@EqualsAndHashCode(callSuper = true)
@Data
public class DrawSpell extends SpellCard {

    public DrawSpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int heroIndex, int pos, MagicStoneFrame frame) {
        System.out.println("Played a spell you draw 2 cards");
        Hero hero = (heroIndex == 0? battlefield.getPlayer(): battlefield.getAi());
        for (int i = 0; i < 2; i++) {
            Card card = hero.getDeck().drawCard();
            if (card != null) {
                System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                hero.getDeckHand().addCard(card, hero);
            }
        }
        return super.play(battlefield, heroIndex, pos, frame);
    }


}
