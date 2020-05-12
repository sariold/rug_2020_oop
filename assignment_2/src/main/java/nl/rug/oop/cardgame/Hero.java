package nl.rug.oop.cardgame;

import lombok.Data;
import nl.rug.oop.cardgame.card.Card;
import nl.rug.oop.cardgame.card.CreatureCard;
import nl.rug.oop.cardgame.interfaces.Attackable;

import java.util.ArrayList;
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
    protected ArrayList<CreatureCard> playedCreatures;

    /**
     * Creates a new Hero
     * @param playerName Player name
     * @param heroHealth Player health
     * @param mana Player mana
     * @param maxMana Player max mana
     * @param heroAttack Player attack
     */
    public Hero(String playerName, int heroHealth, int mana, int maxMana, int heroAttack) {
        this.name = playerName;
        this.deck = new Deck();
        this.deckHand = new DeckHand();
        this.mana = mana;
        this.maxMana = maxMana;
        this.heroHealth = heroHealth;
        this.heroAttack = heroAttack;
        this.playedCreatures = new ArrayList<CreatureCard>();
    }

    /**
     * Play a card
     */
    public void playCard(Battlefield battlefield) {
        if(this.deckHand.getDeckHand().size() > 0) {

            Scanner scanner = new Scanner(System.in);
            this.deckHand.viewHand();
            System.out.println("Which card would you like to play?");
            boolean start = true;
            int currentMove = 0;
            while (start) {
                try {
                    currentMove = scanner.nextInt();
                    if(this.deckHand.getDeckHand().get(currentMove).getCost() <= this.mana) {
                        Card played =  this.deckHand.getDeckHand().get(currentMove);
                        this.deckHand.getDeckHand().remove(currentMove);
                        played.play(battlefield, 0);
                        this.setMana(this.getMana()-played.getCost());
                    } else System.out.println("You cease to have enough mana!");
                    start = false;
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Empty Hand!");
    }

    /**
     * Present the player with game options
     * @param battlefield Playing board
     */
    public void takeTurn(Battlefield battlefield) {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        int currentMove = 0;
        System.out.println(this.name + "'s TURN!");
        Card card = this.getDeck().drawCard();
        if(card != null) {
            System.out.println("Drawing card: " + card.getName() + " : Mana Cost -> " + card.getCost());
            this.getDeckHand().addCard(card);
        }
        while (start) {
            System.out.println("Current Mana: " + this.getMana() + "/" + this.getMaxMana());
            System.out.println("0) Check Hand");
            System.out.println("1) Play a Card");
            System.out.println("2) Discard a Card");
            System.out.println("3) Attack Enemy Hero");
            System.out.println("4) End turn");
            try {
                currentMove = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("NOT VALID INPUT!");
            }
            switch (currentMove) {
                case 0:
                    this.getDeckHand().viewHand();
                    break;
                case 1:
                    this.playCard(battlefield);
                    break;
                case 2:
                    this.getDeckHand().discardCard();
                    break;
                case 3:
                    attackHero(battlefield);
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
                case 4:
                    battlefield.setPlayerTurn(false);
                    start = false;
                    break;
            }
        }
    }

    /**
     *
     * @param battlefield
     */
    public void attackHero(Battlefield battlefield) {
        if(this.playableCreatures()) {
            Scanner scanner = new Scanner(System.in);
            boolean start = true;
            int currentMove = 0;
            while (start && this.playableCreatures()) {
                System.out.println("Which creature would you like to attack with? (-1 : End Turn)");
                showPlayedCreatures();
                try {
                    currentMove = scanner.nextInt();
                    if(currentMove == -1) break;
                    this.getPlayedCreatures().get(currentMove).attack(battlefield.getAi());
                    this.getPlayedCreatures().get(currentMove).setUsed(true);
                } catch (Exception e) {
                    System.out.println("NOT VALID INPUT!");
                }
            }
        } else System.out.println("Either you have no creatures or you just placed them down!");
    }

    /**
     *
     */
    public void showPlayedCreatures() {
        for(int i = 0; i < this.getPlayedCreatures().size(); i++) {
            if(this.getPlayedCreatures().get(i).isUsed() == false) {
                System.out.println(i + ") " + this.getPlayedCreatures().get(i).getName() + ": Health = " +
                        this.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                        this.getPlayedCreatures().get(i).getAttack());
            }
        }
    }

    /**
     *
     * @return
     */
    public boolean playableCreatures() {
        for(int i = this.getPlayedCreatures().size() - 1; i >= 0; i--) {
            if(this.getPlayedCreatures().get(i).isUsed() == false) return true;
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
