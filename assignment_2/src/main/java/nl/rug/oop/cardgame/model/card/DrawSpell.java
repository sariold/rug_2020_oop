package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import nl.rug.oop.cardgame.model.Battlefield;

@Data
public class DrawSpell extends SpellCard {

    public DrawSpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero) {
        System.out.println("Played a spell you draw 2 cards");
        if (hero == 0) {
            for (int i = 0; i < 2; i++) {
                Card card = battlefield.getPlayer().getDeck().drawCard();
                if (card != null) {
                    System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                    battlefield.getPlayer().getDeckHand().addCard(card);
                }
            }
            return super.play(battlefield, hero);
        }
        for (int i = 0; i < 2; i++) {
            Card card = battlefield.getAi().getDeck().drawCard();
            if (card != null) {
                System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
                battlefield.getAi().getDeckHand().addCard(card);
            }
        }
        return super.play(battlefield, hero);
    }


}
