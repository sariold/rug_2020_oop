package nl.rug.oop.cardgame.model;

import lombok.Data;
import nl.rug.oop.cardgame.DefaultStats;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Battlefield Game board
 */
@Data
public class Battlefield {

    private Hero player;
    private Hero ai;
    private boolean playerTurn = true;

    /**
     * Create a new battlefield
     */
    public Battlefield() {
        this.player = new Hero("Diego", 100, 0, 0, 1);
        this.ai = new AIHero("Felix", 10, 0, 0, 1);
    }

    /**
     * Increase mana each turn
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        if(hero.getMana() < DefaultStats.MAX_MANA) hero.setMaxMana(hero.getMaxMana() + 1);
    }

    /**
     * Show the creatures on the battlefield for AI and Player
     */
    public void showBattlefield() {
        System.out.println("ENEMY HEALTH: " + this.getAi().getHeroHealth());
        System.out.println("ENEMY FIELD:");
        for(int i = 0; i < ai.getPlayedCreatures().size(); i++) {
            CreatureCard creature = ai.getPlayedCreatures().get(i);
            if (creature == null) System.out.println(i + ") null");
            else {
                System.out.println(i + ") " + creature.getName() + ": Health = " + creature.getHealth() + ": Attack = " +
                        creature.getAttack() + " : Tapped = " + creature.isUsed());
            }
        }
        System.out.println();
        System.out.println("YOUR HEALTH: " + this.getPlayer().getHeroHealth());
        System.out.println("YOUR FIELD:");
        for(int i = 0; i < player.getPlayedCreatures().size(); i++) {
            CreatureCard creature = player.getPlayedCreatures().get(i);
            if (creature == null) System.out.println(i + ") null");
            else {
                System.out.println(i + ") " + creature.getName() + ": Health = " + creature.getHealth() + ": Attack = " +
                        creature.getAttack() + " : Tapped = " + creature.isUsed());
            }
        }
        System.out.println();
    }

    /**
     * places a creature on a free spot on the battlefield
     * @param creatureCard Creature
     * @param hero Hero who played the creature
     * @return
     */
    public boolean placeCreature(CreatureCard creatureCard, Hero hero) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> freePositions = getFreePositions(hero);
        if (freePositions.size() == 0) {
            System.out.println("No space to place a creature!");
            return false;
        }
        int position = 0;
        System.out.println("Where will you place the creature?");
        for (int i = 0; i < freePositions.size(); i++) {
            System.out.println(freePositions.get(i) + ")");
        }
        while (true) {
            try {
                position = scanner.nextInt();
                if (freePositions.contains(position)) break;
                System.out.println("Invalid input. Where to place?");
                continue;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Where to place?");
                continue;
            }
        }
        hero.getPlayedCreatures().set(position, creatureCard);
        return true;
    }

    /**
     * Returns an arra list of free positions on the battlefield for the hero
     * @param hero Hero
     * @return Free Positions
     */
    private ArrayList<Integer> getFreePositions(Hero hero) {
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
        return true;
    }
}
