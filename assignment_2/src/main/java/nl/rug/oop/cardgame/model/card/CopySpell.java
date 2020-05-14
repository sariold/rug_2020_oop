package nl.rug.oop.cardgame.model.card;

import nl.rug.oop.cardgame.interfaces.Targetting;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.AIHero;
import nl.rug.oop.cardgame.model.hero.Hero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CopySpell extends SpellCard implements Targetting {

    public CopySpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero) {
        Hero targetHero = (hero == 0 ? battlefield.getPlayer() : battlefield.getAi());
        target(battlefield, targetHero);
        return true;
    }

    @Override
    public void target(Battlefield battlefield, Hero hero) {
        ArrayList<Integer> freePositions = battlefield.getFreePositions(hero);
        Scanner scanner = new Scanner(System.in);
        int currentMove;
        boolean start = true;
        if (!(hero instanceof AIHero)) {
            while (start) {
                try {
                    showBattlefield(hero);
                    currentMove = scanner.nextInt();
                    if (hero.getPlayedCreatures().get(currentMove) != null && hero.getPlayedCreatures().size() < 5) {
                        CreatureCard creatureCard = new CreatureCard(hero.getPlayedCreatures().get(currentMove).getEnumCard());
                        battlefield.placeCreature(creatureCard, hero);
                    }
                    start = false;
                } catch (Exception e) {
                    System.out.println("INVALID INPUT");
                }
            }
        } else {
            if(freePositions.size() > 0) {
                ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
                Collections.shuffle(creatures);
                if(creatures.get(0) != null) {
                    CreatureCard creatureCard = new CreatureCard(creatures.get(0).getEnumCard());
                    battlefield.placeCreature(creatureCard, hero);
                }
            }
        }
    }

    public void showBattlefield(Hero hero) {
        ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
        int i = 0;
        for(CreatureCard c: creatures) {
            System.out.print(i + ") " + c.getName() + " : Health = " + c.getCreatureHealth() + " : Attack = " +
                    c.getCreatureAttack());
            i++;
        }
    }
}
