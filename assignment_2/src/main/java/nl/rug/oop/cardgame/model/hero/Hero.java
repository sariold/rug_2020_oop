package nl.rug.oop.cardgame.model.hero;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.util.Attackable;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.model.deck.DiscardDeck;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Hero class to store health, mana, deck, and deck hand
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Hero extends Observable implements Attackable {

    protected String name;
    protected int heroHealth;
    protected int mana;
    protected int maxMana;
    protected int heroAttack;
    protected Deck deck;
    protected DeckHand deckHand;
    protected DiscardDeck discardDeck;
    protected ArrayList<CreatureCard> playedCreatures;

    /**
     * Creates a new Hero
     * @param playerName Player name
     * @param heroHealth Player health
     * @param mana       Player mana
     * @param maxMana    Player max mana
     * @param heroAttack Player attack
     */
    public Hero(String playerName, int heroHealth, int mana, int maxMana, int heroAttack) {
        this.name = playerName;
        this.deck = new Deck();
        this.deckHand = new DeckHand();
        this.discardDeck = new DiscardDeck();
        this.mana = mana;
        this.maxMana = maxMana;
        this.heroHealth = heroHealth;
        this.heroAttack = heroAttack;
        this.playedCreatures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            playedCreatures.add(null);
        }
    }

    /**
     * Play a card
     * @param battlefield Battlefield
     */
    public void playCard(Battlefield battlefield, int pos, Card card) {
        if (this.deckHand.getDeckHand().size() > 0) {
            this.deckHand.viewHand();
            System.out.println("Which card would you like to play?");
            Card played = this.deckHand.getDeckHand().get(card.getCardNumber());
            if (played != null && played.getCost() <= this.mana) {
                this.deckHand.getDeckHand().remove(card.getCardNumber());
                if (played.play(battlefield, 0, pos)) {
                    this.setMana(this.getMana() - played.getCost());
                } else {
                    this.deckHand.getDeckHand().put(card.getCardNumber(), played);
                }
            } else System.out.println("You cease to have enough mana!");
            notifyUpdate();
        } else System.out.println("Empty Hand!");
    }

    /**
     * Present the player with game options
     * @param battlefield Playing board
     */
    public void takeTurn(Battlefield battlefield, MagicStoneGame game) {
        Card card = this.getDeck().drawCard();
        if (card != null) {
            this.getDeckHand().addCard(this.getDiscardDeck(), card);
        }
        notifyUpdate();
        while(battlefield.isPlayerTurn()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Attack enemy Hero
     * @param battlefield Battlefield
     */
    public void attackPhase(Battlefield battlefield, int pos, MagicStoneGame game) {
        CreatureCard attackingCreature = this.getPlayedCreatures().get(pos);
        CreatureCard attackedCreature = battlefield.getAi().getPlayedCreatures().get(pos);
        if (attackedCreature == null) attackingCreature.attack(battlefield.getAi());
        else attackingCreature.attack(attackedCreature);
        attackingCreature.checkDeath(this, attackingCreature.getBattlePosition());
        if(attackedCreature != null) attackedCreature.checkDeath(battlefield.getAi(), attackedCreature.getBattlePosition());
        attackingCreature.setUsed(true);
        battlefield.removeDead(this);
        battlefield.removeDead(battlefield.getAi());
        game.endGameCheck(battlefield);
        notifyUpdate();
    }

    /**
     * Return a boolean if your battlefield has untapped creatures
     * @return Boolean if a creature can be used for combat
     */
    public boolean untappedCreatures() {
        for (int i = this.getPlayedCreatures().size() - 1; i >= 0; i--) {
            if (this.getPlayedCreatures().get(i) != null && !this.getPlayedCreatures().get(i).isUsed()) return true;
        }
        return false;
    }

    /**
     * Set health
     * @param health Health
     */
    @Override
    public void setHealth(int health) {
        this.setHeroHealth(health);
        notifyUpdate();
    }

    /**
     * Return health
     * @return Health
     */
    @Override
    public int getHealth() {
        return this.getHeroHealth();
    }

    /**
     * Attack a hero or creature
     * @param attackable Attackable
     */
    @Override
    public void attack(Attackable attackable) {

    }

    /**
     * Return attack
     * @return Attack
     */
    @Override
    public int getAttack() {
        return this.getHeroAttack();
    }

    /**
     * Set attack
     * @param attack Attack
     */
    @Override
    public void setAttack(int attack) {
        this.setHeroAttack(attack);
    }

    /**
     * notifies observers of change
     */
    protected void notifyUpdate() {
        setChanged();
        notifyObservers();
    }
}
