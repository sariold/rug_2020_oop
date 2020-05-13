package nl.rug.oop.cardgame.hero;

import nl.rug.oop.cardgame.battlefield.Battlefield;
import nl.rug.oop.cardgame.card.Card;
import nl.rug.oop.cardgame.card.CreatureCard;

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
        if(card != null) {
            System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
            this.getDeckHand().addCard(card);
        }
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
            this.deckHand.getDeckHand().remove(currentMove);
            played.play(battlefield, 1);
            this.setMana(this.getMana() - played.getCost());
        } else System.out.println("AI has no cards in hand");
        attackHero(battlefield);
    }

    @Override
    public void attackHero(Battlefield battlefield) {
        ArrayList<CreatureCard> creatures = getPlayedCreatures();
        if (creatures.size() == 0) System.out.println("AI has no creatures to attack with");
        for (CreatureCard c: creatures) {
            if (c != null) {
                System.out.println("AI attack you with " + c.getName());
                c.attack(battlefield.getPlayer());
            }

        }
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

