package nl.rug.oop.cardgame.model.hero;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import java.util.ArrayList;
import java.util.Collections;

/**
 * AI Hero
 */
public class AIHero extends Hero {

    /**
     * Creates a hero that plays on its own
     *
     * @param playerName Name
     * @param heroHealth Health
     * @param mana       Mana
     * @param maxMana    Maximum Mana
     * @param heroAttack Attack
     */
    public AIHero(String playerName, int heroHealth, int mana, int maxMana, int heroAttack) {
        super(playerName, heroHealth, mana, maxMana, heroAttack);
    }

    /**
     * Rotate turns between Player and AI
     *
     * @param battlefield Playing board
     */
    @Override
    public void takeTurn(Battlefield battlefield, MagicStoneGame game) {
        System.out.println(this.name + "'s TURN!");
        Card card = this.getDeck().drawCard();
        if (card != null) {
            System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
            this.getDeckHand().addCard(this.getDiscardDeck(), card);
        }
        notifyUpdate();
        if (this.deckHand.getDeckHand().size() > 0) {
            System.out.println("AI has cards in hand");
            ArrayList<Card> playableCards;
            while (this.getMana() > 0) {
                playableCards = getPlayableCards(battlefield);
                if (playableCards.size() == 0) {
                    System.out.println("AI does not have enough mana to play a card");
                    break;
                }
                Collections.shuffle(playableCards);
                Card played = playableCards.get(0);
                if (played.getCost() <= this.getMana()) {
                    boolean playedSuccessfully = played.play(battlefield, 1, -1);
                    if(playedSuccessfully) {
                        System.out.println("AI plays " + played.getName() + " for " + played.getCost() + " mana");
                        this.deckHand.getDeckHand().remove(played.getCardNumber());
                        this.setMana(this.getMana() - played.getCost());
                        System.out.println("Current Mana: " + this.getMana() + "/" + this.getMaxMana());
                    }
                    notifyUpdate();
                }
            }
        } else System.out.println("AI has no cards in hand");
        attackPhase(battlefield, -1, game);
        battlefield.setPlayerTurn(true);
    }

    /**
     * Attack enemy hero with your untapped creatures
     *
     * @param battlefield Battlefield
     */
    @Override
    public void attackPhase(Battlefield battlefield, int pos, MagicStoneGame game) {
        ArrayList<CreatureCard> creatures = getPlayedCreatures();
        if (creatures.size() == 0) System.out.println("AI has no creatures to attack with");
        for (CreatureCard c : creatures) {
            if (c != null && !c.isUsed() && c.getBattlePosition() != -1) {
                System.out.println("AI attack you with " + c.getName());
                CreatureCard attackedCreature = battlefield.getPlayer().getPlayedCreatures().get(c.getBattlePosition());
                if (attackedCreature == null) c.attack(battlefield.getPlayer());
                else c.attack(attackedCreature);
                if (attackedCreature != null) attackedCreature.checkDeath(battlefield.getPlayer(),
                attackedCreature.getBattlePosition());
                c.checkDeath(battlefield.getAi(), c.getBattlePosition());
                battlefield.removeDead(this);
                battlefield.removeDead(battlefield.getPlayer());
                game.endGameCheck(battlefield);
                notifyUpdate();
            }
        }
    }

    /**
     * Return which cards can be played with your current mana amount
     *
     * @return An arraylist of playable cards
     */
    private ArrayList<Card> getPlayableCards(Battlefield battlefield) {
        boolean addable;
        ArrayList<Card> playable = new ArrayList<>();
        for (Card c : this.deckHand.getDeckHand().values()) {
            if (c.getCost() <= this.getMana()) {
                addable = true;
                if(c.getEnumCard().getFace() == EnumCard.Face.COPYPASTE ) {
                    if(battlefield.playerHasBattlefieldCreature(battlefield.getAi()).size() == 0 || battlefield.getFreePositions(battlefield.getAi()).size() == 0) addable = false;
                }
                if(c.getEnumCard().getFace() == EnumCard.Face.HELLFIRE ) {
                    if(battlefield.playerHasBattlefieldCreature(battlefield.getPlayer()).size() == 0) addable = false;
                }
                if(addable) playable.add(c);
            }
        }
        return playable;
    }
}

