package nl.rug.oop.cardgame.model.hero;

import lombok.Data;
import nl.rug.oop.cardgame.controller.button.AttackPhaseButton;
import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.util.Attackable;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.model.deck.DiscardDeck;
import nl.rug.oop.cardgame.view.MagicStoneFrame;
import nl.rug.oop.cardgame.view.MagicStonePanel;

import javax.swing.*;
import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
    public void playCard(Battlefield battlefield, MagicStoneFrame frame, int pos, Card card) {
        if (this.deckHand.getDeckHand().size() > 0) {
            Scanner scanner = new Scanner(System.in);
            this.deckHand.viewHand();
            System.out.println("Which card would you like to play?");
            boolean start = true;
            int currentMove;
            Card played = this.deckHand.getDeckHand().get(card.getCardNumber());
            if (played != null && played.getCost() <= this.mana) {
                this.deckHand.getDeckHand().remove(card.getCardNumber());
                if (played.play(battlefield, 0, pos, frame)) {
                    this.setMana(this.getMana() - played.getCost());
                } else {
                    this.deckHand.getDeckHand().put(card.getCardNumber(), played);
                }
            } else System.out.println("You cease to have enough mana!");
//            while (start) {
//                try {
//                    currentMove = scanner.nextInt();
//                    Card played = this.deckHand.getDeckHand().get(currentMove);
//                    if (played != null && played.getCost() <= this.mana) {
//                        this.deckHand.getDeckHand().remove(currentMove);
//                        if (played.play(battlefield, 0, pos)) {
//                            this.setMana(this.getMana() - played.getCost());
//                        } else {
//                            this.deckHand.getDeckHand().put(currentMove, played);
//                        }
//                    } else System.out.println("You cease to have enough mana!");
//                    start = false;
//                } catch (InputMismatchException e) {
//                    System.out.println("NOT VALID INPUT!");
//                }
//            }
            frame.update(frame.getGraphics());
        } else System.out.println("Empty Hand!");
    }

    /**
     * Present the player with game options
     *
     * @param battlefield Playing board
     */
    public void takeTurn(Battlefield battlefield, MagicStoneFrame frame, MagicStonePanel panel, MagicStoneGame game,
    EndTurnButton endTurnButton) {
        AttackPhaseButton attackPhaseButton = new AttackPhaseButton(game, frame, panel, frame.getClicker());
        Card card = this.getDeck().drawCard();
        if (card != null) {
            this.getDeckHand().addCard(this.getDiscardDeck(), card);
        }
        if(this.untappedCreatures()) {
            attackPhaseButton.setBounds(590, 108, 100, 30);
            panel.add(endTurnButton);
            panel.add(attackPhaseButton);
        }
        frame.update(frame.getGraphics());
        while(battlefield.isPlayerTurn()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        panel.remove(attackPhaseButton);
//        battlefield.setPlayerTurn(false);
        return;
//                case 4:
//                    swapCreaturePositions(battlefield, frame);
//                    break;
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
     * @param battlefield Battlefield
     */
    public void attackPhase(Battlefield battlefield, MagicStoneFrame frame, int pos, MagicStoneGame game, MagicStonePanel panel) {
        CreatureCard attackingCreature = this.getPlayedCreatures().get(pos);
        CreatureCard attackedCreature = battlefield.getAi().getPlayedCreatures().get(pos);
        frame.playGif("SWORD", attackingCreature.getCardImage().getCoordinates());
        if (attackedCreature == null) attackingCreature.attack(battlefield.getAi());
        else attackingCreature.attack(attackedCreature);
        attackingCreature.checkDeath(this, attackingCreature.getBattlePosition());
        if(attackedCreature != null) attackedCreature.checkDeath(battlefield.getAi(), attackedCreature.getBattlePosition());
        attackingCreature.setUsed(true);
        battlefield.removeDead(this);
        battlefield.removeDead(battlefield.getAi());
        game.endGameCheck(battlefield);
        frame.update(frame.getGraphics());
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

}
