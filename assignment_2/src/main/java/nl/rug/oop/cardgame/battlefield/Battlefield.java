package nl.rug.oop.cardgame.battlefield;

import lombok.Data;
import nl.rug.oop.cardgame.card.CreatureCard;
import nl.rug.oop.cardgame.defaultstats.DefaultStats;
import nl.rug.oop.cardgame.hero.AIHero;
import nl.rug.oop.cardgame.hero.Hero;

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
            if (ai.getPlayedCreatures().get(i) == null) {
                System.out.println(i + ") null");
            }
            else {
                System.out.println(i + ") " + ai.getPlayedCreatures().get(i).getName() + ": Health = " +
                        ai.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                        ai.getPlayedCreatures().get(i).getAttack() + " : Tapped = "
                        + ai.getPlayedCreatures().get(i).isUsed());
            }
        }
        System.out.println();
        System.out.println("YOUR HEALTH: " + this.getPlayer().getHeroHealth());
        System.out.println("YOUR FIELD:");
        for(int i = 0; i < player.getPlayedCreatures().size(); i++) {
            if (player.getPlayedCreatures().get(i) == null) {
                System.out.println(i + ") null");
            }
            else {
                System.out.println(i + ") " + player.getPlayedCreatures().get(i).getName() + ": Health = " +
                        player.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                        player.getPlayedCreatures().get(i).getAttack() + " : Tapped = "
                        + player.getPlayedCreatures().get(i).isUsed());
            }
        }
        System.out.println();
    }

    public void placeCreature(CreatureCard creatureCard, Hero hero) {
        Scanner scanner = new Scanner(System.in);
        int position = 0;
        ArrayList<Integer> freePositions = getFreePositions(hero);
        if (freePositions.size() == 0) {
            System.out.println("No free spots for creatures");
            return;
        }
        System.out.println("On which position should the creature be played?");
        for (int i = 0; i < freePositions.size(); i++) {
            System.out.println(freePositions.get(i) + ")");
        }
        while (true) {
            try {
                position = scanner.nextInt();
                if (freePositions.contains(position)) break;
                else {
                    System.out.println("There is a creature already. Where will you place this one?");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Where to put the creature?");
                scanner.nextLine();
            }
        }
        hero.getPlayedCreatures().set(position, creatureCard);
    }

    private ArrayList<Integer> getFreePositions(Hero playing) {
        ArrayList<Integer> freePositions = new ArrayList<Integer>();
        for (int i = 0; i < playing.getPlayedCreatures().size(); i++) {
            if (playing.getPlayedCreatures().get(i) == null) freePositions.add(i);
        }
        return freePositions;
    }
}
