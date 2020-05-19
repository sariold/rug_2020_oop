package nl.rug.oop.cardgame.model;

import lombok.Data;
import nl.rug.oop.cardgame.util.DefaultStats;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.*;

/**
 * Battlefield Game board
 */
@Data
public class Battlefield extends Observable {

    private Hero player;
    private Hero ai;
    private boolean playerTurn = true;
    private boolean attackPhase;
    private boolean playPhase;
    private Card selectedCard;

    /**
     * Create a new battlefield
     */
    public Battlefield() {
        this.player = new Hero("Diego", 100, 0, 0, 1);
        this.ai = new AIHero("AI", 10, 0, 0, 1);
        this.attackPhase = false;
        this.playPhase = true;
    }

    public void setAttackPhase(boolean attackPhase) {
        this.attackPhase = attackPhase;
        this.playPhase = false;
        setChanged();
        notifyObservers();
    }

    public void setPlayerTurn(boolean playerTurn) {
        if(playerTurn) setPlayPhase(true);
        else setPlayPhase(false);
    }

    public void setPlayPhase(boolean playPhase) {
        this.playPhase = playPhase;
        this.playerTurn = playPhase;
        this.attackPhase = false;
        setChanged();
        notifyObservers();
    }

    public void setSelectedCard(Card selected) {
        this.selectedCard = selected;
        setChanged();
        notifyObservers();
    }

    /**
     * Increase mana each turn
     *
     * @param hero Hero
     */
    /**
     * Increase mana each turn
     *
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        attackPhase = false;
        if (hero.getMaxMana() < DefaultStats.MAX_MANA) hero.setMaxMana(hero.getMaxMana() + 1);
    }

    /**
     * places a creature on a free spot on the battlefield
     *
     * @param creatureCard Creature
     * @param hero         Hero who played the creature
     * @return
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

    private int enemySpotIHaveEmpty(ArrayList<Integer> mySpots, ArrayList<Integer> enemySpots) {
        for(int i = 0; i < mySpots.size(); i++) {
            for(int j = 0; j < enemySpots.size(); j++) {
                if(mySpots.get(i) == enemySpots.get(j)) return mySpots.get(i);
            }
        }
        return -1;
    }

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
     *
     * @param hero Hero
     * @return Free Positions
     */
    public ArrayList<Integer> getFreePositions(Hero hero) {
        ArrayList<Integer> freePositions = new ArrayList<Integer>();
        for (int i = 0; i < DefaultStats.MAX_CREATURES_ON_BATTLEFIELD; i++) {
            if (hero.getPlayedCreatures().get(i) == null) freePositions.add(i);
        }
        return freePositions;
    }

    /**
     * Moves a creature to another spot on the battlefield
     *
     * @param hero
     * @param movingCreature Creature
     * @return If the creature has been moved
     */
    public boolean moveCreature(Hero hero, CreatureCard movingCreature) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> freePositions = getFreePositions(hero);
        if (freePositions.size() == 0) {
            System.out.println("No space to move a creature!");
            return false;
        }
        int position = 0;
        System.out.println("Where will you move the creature to? (-1 : nowhere)");
        for (int i = 0; i < freePositions.size(); i++) {
            System.out.println(freePositions.get(i) + ")");
        }
        while (true) {
            try {
                position = scanner.nextInt();
                if (position == -1) return false;
                if (freePositions.contains(position)) break;
                System.out.println("Invalid input. Where to move?");
                continue;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Where to move?");
                continue;
            }
        }
        hero.getPlayedCreatures().set(position, movingCreature);
        movingCreature.setBattlePosition(position);
        return true;
    }

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
