package nl.rug.oop.cardgame.model.hero;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;

import java.util.ArrayList;
import java.util.Collections;

/**
 * AI Hero
 */
public class AIHero extends Hero {

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

    /**
     * Rotate turns between Player and AI
     * @param battlefield Playing board
     */
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
            ArrayList<Card> playableCards;
            while(this.getMana() > 0) {
                playableCards = getPlayableCards();
                if (playableCards.size() == 0) {
                    System.out.println("AI does not have enough mana to play a card");
                    break;
                }
                Collections.shuffle(playableCards);
                Card played = playableCards.get(0);
                if(played.getCost() <= this.getMana()) {
                    System.out.println("AI plays " + played.getName() + " for " + played.getCost() + " mana");
                    this.deckHand.getDeckHand().remove(played.getCardNumber());
                    played.play(battlefield, 1);
                    this.setMana(this.getMana() - played.getCost());
                    System.out.println("Current Mana: " + this.getMana() + "/" + this.getMaxMana());
                }
            }
        } else System.out.println("AI has no cards in hand");
        attackPhase(battlefield);
    }

    /**
     * Attack enemy hero with your untapped creatures
     * @param battlefield Battlefield
     */
    @Override
    public void attackPhase(Battlefield battlefield) {
        ArrayList<CreatureCard> creatures = getPlayedCreatures();
        if (creatures.size() == 0) System.out.println("AI has no creatures to attack with");
        for (CreatureCard c: creatures) {
            if(c != null && !c.isUsed() && c.getBattlePosition() != -1) {
                System.out.println("AI attack you with " + c.getName());
                CreatureCard attackedCreature = battlefield.getPlayer().getPlayedCreatures().get(c.getBattlePosition());
                if (attackedCreature == null) c.attack(battlefield.getPlayer());
                else c.attack(attackedCreature);
                if(attackedCreature != null) attackedCreature.checkDeath(battlefield.getAi(),
                        attackedCreature.getBattlePosition());
                c.checkDeath(battlefield.getAi(), c.getBattlePosition());
            }
        }
    }

    /**
     * Return which cards can be played with your current mana amount
     * @return An arraylist of playable cards
     */
    private ArrayList<Card> getPlayableCards() {
        ArrayList<Card> playable = new ArrayList<>();
        for (Card c: this.deckHand.getDeckHand().values()){
            if (c.getCost() <= this.getMana()) {
                playable.add(c);
            }
        }
        return playable;
    }
}

