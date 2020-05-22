package nl.rug.oop.cardgame.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.util.DefaultStats;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.*;

/**
 * Battlefield Game board
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Battlefield extends Observable {

    private Hero player;
    private Hero ai;
    private boolean playerTurn = true;
    private boolean attackPhase;
    private boolean playPhase;
    private Card selectedCard;
    private boolean hellFire;

    /**
     * Create a new battlefield
     */
    public Battlefield() {
        this.player = new Hero("Player", 15, 0, 0, 1);
        this.ai = new AIHero("AI", 15, 0, 0, 1);
        this.attackPhase = false;
        this.playPhase = true;
    }

    /**
     * notifies observers of change
     */
    public void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

    /**
     * Adds attack to the hero that played the damage buff if it has been played and notifies observers
     * @param hero Hero
     * @param damageBuff True if damage buff was played
     * @param value Amount of damage to buff
     */
    public void setDamageBuff(Hero hero, boolean damageBuff, int value) {
        if(damageBuff) {
            hero.setHeroAttack(hero.getHeroAttack() + value);
        } else hero.setHeroAttack(DefaultStats.DEFAULT_ATTACK);
        notifyUpdate();
    }

    /**
     * Set Hell fire to true if hell fire is played and notifies observers
     * @param hellFire True if hell fire is played
     * @param hero Hero
     */
    public void setHellFire(boolean hellFire, Hero hero) {
        this.hellFire = hellFire;
        removeDead(hero);
        notifyUpdate();
        this.hellFire = false;
    }

    /**
     * Set attack phase and set play phase to false and notifies observers
     * @param attackPhase True if it is the players attack phase
     */
    public void setAttackPhase(boolean attackPhase) {
        this.attackPhase = attackPhase;
        this.playPhase = false;
        notifyUpdate();
    }

    /**
     * Set whether it is the players turn
     * @param playerTurn True if it is the players turn
     */
    public void setPlayerTurn(boolean playerTurn) {
        setPlayPhase(playerTurn);
    }

    /**
     * Set Play Phase and set attack phase to false and notifies observers
     * @param playPhase True if player is in play phase
     */
    public void setPlayPhase(boolean playPhase) {
        this.playPhase = playPhase;
        this.playerTurn = playPhase;
        this.attackPhase = false;
        notifyUpdate();
    }

    /**
     * Sets the selected card and notifies observers
     * @param selected selected card
     */
    public void setSelectedCard(Card selected) {
        this.selectedCard = selected;
        notifyUpdate();
    }

    /**
     * Increase mana each turn
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        attackPhase = false;
        if (hero.getMaxMana() < DefaultStats.MAX_MANA) hero.setMaxMana(hero.getMaxMana() + 1);
    }

    /**
     * places a creature on a free spot on the battlefield
     * @param creatureCard Creature
     * @param hero         Hero who played the creature
     * @return Success of placing
     */
    public boolean placeCreature(CreatureCard creatureCard, Hero hero, int pos) {
        ArrayList<Integer> freePositions = getFreePositions(hero);
        int placePos = -1;
        if(freePositions.size() > 0) {
            if (hero instanceof AIHero) {
                ArrayList<Integer> enemySpots = playerHasBattlefieldCreature(this.getPlayer());
                if (enemySpots.size() > 0) {
                    placePos = enemySpotIHaveEmpty(freePositions, enemySpots);
                }
                if(placePos == -1){
                    Collections.shuffle(freePositions);
                    placePos = freePositions.get(0);
                }
                hero.getPlayedCreatures().set(placePos, creatureCard);
                hero.getPlayedCreatures().get(placePos).setBattlePosition(placePos);
                return true;
            }
            hero.getPlayedCreatures().set(pos, creatureCard);
            creatureCard.setBattlePosition(pos);
            return true;
        }
        return false;
    }

    /**
     * Return spots where both players have creatures on the battlefield
     * @param mySpots My Spots
     * @param enemySpots Enemy spots
     * @return Spots occupied by both
     */
    private int enemySpotIHaveEmpty(ArrayList<Integer> mySpots, ArrayList<Integer> enemySpots) {
        for (Integer mySpot : mySpots) {
            for (Integer enemySpot : enemySpots) {
                if (mySpot.equals(enemySpot)) return mySpot;
            }
        }
        return -1;
    }

    /**
     * Returns the positions where a hero has creatures on the battlefield
     * @param hero Hero
     * @return Positions
     */
    public ArrayList playerHasBattlefieldCreature(Hero hero) {
        ArrayList<Integer> battleSpots = new ArrayList<>();
        ArrayList<CreatureCard> playerCreatures = hero.getPlayedCreatures();
        for(CreatureCard c: playerCreatures) {
            if(c != null) battleSpots.add(c.getBattlePosition());
        }
        return battleSpots;
    }

    /**
     * Returns an array list of free positions on the battlefield for the hero
     * @param hero Hero
     * @return Free Positions
     */
    public ArrayList<Integer> getFreePositions(Hero hero) {
        ArrayList<Integer> freePositions = new ArrayList<>();
        for (int i = 0; i < DefaultStats.MAX_CREATURES_ON_BATTLEFIELD; i++) {
            if (hero.getPlayedCreatures().get(i) == null) freePositions.add(i);
        }
        return freePositions;
    }

    /**
     * Remove dead creatures of the hero from the battlefield
     * @param hero Hero
     */
    public void removeDead(Hero hero) {
        for (int i = 0; i < hero.getPlayedCreatures().size(); i++) {
            if (hero.getPlayedCreatures().get(i) != null) {
                if (hero.getPlayedCreatures().get(i).getCreatureHealth() < 1) {
                    hero.getPlayedCreatures().get(i).setBattlePosition(-1);
                    hero.getPlayedCreatures().set(i, null);
                }
            }
        }
    }
}
