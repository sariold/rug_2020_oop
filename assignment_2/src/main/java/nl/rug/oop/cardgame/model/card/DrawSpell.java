package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

@EqualsAndHashCode(callSuper = true)
@Data
public class DrawSpell extends SpellCard {

    public DrawSpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero, int pos, MagicStoneFrame frame) {
        System.out.println("Played a spell you draw 2 cards");
        if (hero == 0) {
            for (int i = 0; i < 2; i++) {
                Card card = battlefield.getPlayer().getDeck().drawCard();
                if (card != null) {
                    System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                    battlefield.getPlayer().getDeckHand().addCard(battlefield.getPlayer().getDiscardDeck(), card);
                }
            }
            return super.play(battlefield, hero, pos, frame);
        }
        for (int i = 0; i < 2; i++) {
            Card card = battlefield.getAi().getDeck().drawCard();
            if (card != null) {
                System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                battlefield.getAi().getDeckHand().addCard(battlefield.getAi().getDiscardDeck(), card);
            }
        }
        return super.play(battlefield, hero, pos, frame);
    }


}
