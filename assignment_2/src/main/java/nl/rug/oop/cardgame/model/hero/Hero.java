package nl.rug.oop.cardgame.model.hero;

import lombok.Data;
import nl.rug.oop.cardgame.interfaces.Attackable;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.model.deck.DiscardDeck;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hero class to store health, mana, deck, and deck hand
 */
@Data
public class Hero implements Attackable {

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
     *
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
     *
     * @param battlefield Battlefield
     */
    public void playCard(Battlefield battlefield, MagicStoneFrame frame) {
        if (this.deckHand.getDeckHand().size() > 0) {
            Scanner scanner = new Scanner(System.in);
            this.deckHand.viewHand();
            System.out.println("Which card would you like to play?");
            boolean start = true;
            int currentMove;
            while (start) {
                try {
                    currentMove = scanner.nextInt();
                    Card played = this.deckHand.getDeckHand().get(currentMove);
                    if (played != null && played.getCost() <= this.mana) {
                        this.deckHand.getDeckHand().remove(currentMove);
                        if (played.play(battlefield, 0)) {
                            this.setMana(this.getMana() - played.getCost());
                        } else {
                            this.deckHand.getDeckHand().put(currentMove, played);
                        }
                    } else System.out.println("You cease to have enough mana!");
                    start = false;
                } catch (InputMismatchException e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
            frame.update(frame.getGraphics());
        } else System.out.println("Empty Hand!");
    }

    /**
     * Present the player with game options
     *
     * @param battlefield Playing board
     */
    public void takeTurn(Battlefield battlefield, MagicStoneFrame frame) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int currentMove = 0;
        System.out.println(this.name + "'s TURN!");
        Card card = this.getDeck().drawCard();
        if (card != null) {
            System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
            this.getDeckHand().addCard(card);
        }
        frame.update(frame.getGraphics());
        while (start) {
            System.out.println("Current Mana: " + this.getMana() + "/" + this.getMaxMana());
            System.out.println("0) Check Hand");
            System.out.println("1) Play a Card");
            System.out.println("2) Discard a Card");
            System.out.println("3) Attack with creature");
            System.out.println("4) Swap a creatures position");
            System.out.println("5) Show Battlefield");
            System.out.println("6) End turn");
            try {
                currentMove = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("NOT VALID INPUT!");
            }
            switch (currentMove) {
                case 0:
                    this.getDeckHand().viewHand();
                    break;
                case 1:
                    this.playCard(battlefield, frame);
                    break;
                case 2:
                    this.getDeckHand().discardCard(this.getDiscardDeck());
                    frame.update(frame.getGraphics());
                    break;
                case 3:
                    attackPhase(battlefield, frame);
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
                case 4:
                    swapCreaturePositions(battlefield, frame);
                    break;
                case 5:
                    battlefield.showBattlefield();
                    break;
                case 6:
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
            }
        }
    }

    private void swapCreaturePositions(Battlefield battlefield, MagicStoneFrame frame) {
        if (this.untappedCreatures()) {
            Scanner scanner = new Scanner(System.in);
            boolean start = true;
            int currentMove;
            while (start && this.untappedCreatures()) {
                System.out.println("Which creature would oyu like to swap to another position? (-1 : none)");
                showPlayedCreatures();
                try {
                    currentMove = scanner.nextInt();
                    if (currentMove == -1) return;
                    CreatureCard movingCreature = this.getPlayedCreatures().get(currentMove);
                    if (battlefield.moveCreature(this, movingCreature)) {
                        movingCreature.setUsed(true);
                        this.getPlayedCreatures().set(currentMove, null);
                    }
                    frame.update(frame.getGraphics());
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Either you have no creatures or you just placed them down!");
    }

    /**
     * Attack enemy Hero
     *
     * @param battlefield Battlefield
     */
    public void attackPhase(Battlefield battlefield, MagicStoneFrame frame) {
        if (this.untappedCreatures()) {
            Scanner scanner = new Scanner(System.in);
            boolean start = true;
            int currentMove;
            while (start && this.untappedCreatures()) {
                System.out.println("Which creature would you like to attack with? (-1 : End Turn)");
                showPlayedCreatures();
                try {
                    currentMove = scanner.nextInt();
                    if (currentMove == -1) start = false;
                    CreatureCard attackingCreature = this.getPlayedCreatures().get(currentMove);
                    CreatureCard attackedCreature = battlefield.getAi().getPlayedCreatures().get(currentMove);
                    if (attackingCreature == null) continue;
                    if (attackedCreature == null) attackingCreature.attack(battlefield.getAi());
                    else attackingCreature.attack(attackedCreature);
                    attackingCreature.checkDeath(this, attackingCreature.getBattlePosition());
                    attackedCreature.checkDeath(battlefield.getAi(), attackedCreature.getBattlePosition());
                    attackingCreature.setUsed(true);
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
                battlefield.removeDead(this);
                frame.update(frame.getGraphics());
            }
        } else System.out.println("Either you have no creatures or you just placed them down!");
    }

    /**
     * Print out all creatures are not tapped
     */
    public void showPlayedCreatures() {
        for (int i = 0; i < this.getPlayedCreatures().size(); i++) {
            if (this.playedCreatures.get(i) != null && !this.getPlayedCreatures().get(i).isUsed()) {
                System.out.println(i + ") " + this.getPlayedCreatures().get(i).getName() + ": Health = " +
                        this.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                        this.getPlayedCreatures().get(i).getAttack());

            }
        }
    }

    /**
     * Return a boolean if your battlefield has untapped creatures
     *
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
     *
     * @param health Health
     */
    @Override
    public void setHealth(int health) {
        this.setHeroHealth(health);
    }

    /**
     * Return health
     *
     * @return Health
     */
    @Override
    public int getHealth() {
        return this.getHeroHealth();
    }

    /**
     * Attack a hero or creature
     *
     * @param attackable Attackable
     */
    @Override
    public void attack(Attackable attackable) {

    }

    /**
     * Return attack
     *
     * @return Attack
     */
    @Override
    public int getAttack() {
        return this.getHeroAttack();
    }

    /**
     * Set attack
     *
     * @param attack Attack
     */
    @Override
    public void setAttack(int attack) {
        this.setHeroAttack(attack);
    }
}
