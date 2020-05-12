package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.card.Card;

import java.util.ArrayList;
import java.util.Random;

public class AIHero extends Hero{

    /**
     * Creates a hero that plays on its own
     * @param playerName Name
     * @param heroHealth Health
     * @param mana Mana
     * @param maxMana Maximum Mana
     * @param heroAttack Attack
     */
    public AIHero(String playerName, int heroHealth, int mana, int maxMana, int heroAttack) {
        super(playerName, heroHealth, mana, maxMana, heroAttack);
    }

    @Override
    public void takeTurn(Battlefield battlefield) {
        System.out.println(this.name + "'s TURN!");
        Card card = this.getDeck().drawCard();
        System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
        this.getDeckHand().addCard(card);
        if(this.deckHand.getDeckHand().size() > 0) {
            System.out.println("AI has cards in hand");
            ArrayList<Card> playableCards = getPlayableCards();
            if (playableCards.size() == 0) {
                System.out.println("AI has not enough mana to play a card");
                return;
            }
            Random r = new Random();
            int currentMove = r.nextInt(playableCards.size());
            Card played = this.deckHand.getDeckHand().get(currentMove);
            System.out.println("AI plays " + played.getName());
            played.play(battlefield, 1);
            this.deckHand.getDeckHand().remove(currentMove);
            this.setMana(this.getMana() - played.getCost());
        } else System.out.println("AI has no cards in hand");
    }

    private ArrayList<Card> getPlayableCards() {
        ArrayList<Card> playable = new ArrayList<Card>();
        for (Card c: this.deckHand.getDeckHand()){
            if (c.getCost() <= this.getMana()) {
                playable.add(c);
            }
        }
        return playable;
    }
}

