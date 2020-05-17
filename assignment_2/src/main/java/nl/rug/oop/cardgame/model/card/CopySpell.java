package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.util.Targetting;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

@EqualsAndHashCode(callSuper = true)
@Data
public class CopySpell extends SpellCard implements Targetting {

    public CopySpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero, int pos, MagicStoneFrame frame) {
        Hero targetHero = (hero == 0 ? battlefield.getPlayer() : battlefield.getAi());
        target(battlefield, targetHero);
        return super.play(battlefield, hero, pos, frame);
    }

    @Override
    public void target(Battlefield battlefield, Hero hero) {
        ArrayList<Integer> freePositions = battlefield.getFreePositions(hero);
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        boolean start = true;
        if (notEmptyBattlefield(hero)) {
            if (!(hero instanceof AIHero)) {
                while (start) {
                    try {
                        System.out.println("Which creature do you want to copy paste?");
                        showBattlefield(hero);
                        System.out.println();
                        currentMove = scanner.nextInt();
                        if (hero.getPlayedCreatures().get(currentMove) != null) {
                            CreatureCard creatureCard = new CreatureCard(hero.getPlayedCreatures().get(currentMove).getEnumCard());
                            creatureCard.setUsed(true);
//                            battlefield.placeCreature(creatureCard, hero);
                        }
                        start = false;
                    } catch (InputMismatchException e) {
                        e.printStackTrace();
                        System.out.println("INVALID INPUT");
                        scanner.nextLine();
                    }
                }
            } else {
                if (freePositions.size() > 0) {
                    ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
                    Collections.shuffle(creatures);
                    if (creatures.get(0) != null) {
                        CreatureCard creatureCard = new CreatureCard(creatures.get(0).getEnumCard());
                        creatureCard.setUsed(true);
//                        battlefield.placeCreature(creatureCard, hero);
                    }
                }
            }
        }
    }

    public boolean notEmptyBattlefield(Hero hero) {
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        for (CreatureCard c : creatures) {
            if (c != null) return true;
        }
        return false;
    }

    public void showBattlefield(Hero hero) {
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        int i = 0;
        for (CreatureCard c : creatures) {
            if (c != null) {
                System.out.print(i + ") " + c.getName() + " : Health = " + c.getCreatureHealth() + " : Attack = " +
                        c.getCreatureAttack());
            }
            i++;
        }
    }
}
