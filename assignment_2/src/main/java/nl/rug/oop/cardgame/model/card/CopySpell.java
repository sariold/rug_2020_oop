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

@EqualsAndHashCode(callSuper = true)
@Data
public class CopySpell extends SpellCard implements Targetting {

    private int pos;

    public CopySpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero, int pos, MagicStoneFrame frame) {
        this.pos = pos;
        Hero targetHero = (hero == 0 ? battlefield.getPlayer() : battlefield.getAi());
        boolean played = target(battlefield, targetHero);
        if(played) return super.play(battlefield, hero, pos, frame);
        return false;
    }

    @Override
    public boolean target(Battlefield battlefield, Hero hero) {
        ArrayList<Integer> freePositions = battlefield.getFreePositions(hero);
        if (notEmptyBattlefield(hero)) {
            if (!(hero instanceof AIHero)) {
                CreatureCard creatureCard = new CreatureCard(hero.getPlayedCreatures().get(pos).getEnumCard());
                creatureCard.setUsed(true);
                battlefield.placeCreature(creatureCard, hero, freePositions.get(0));
//                while (start) {
//                    try {
//                        System.out.println("Which creature do you want to copy paste?");
//                        showBattlefield(hero);
//                        System.out.println();
//                        currentMove = scanner.nextInt();
//                        if (hero.getPlayedCreatures().get(currentMove) != null) {
//                            CreatureCard creatureCard = new CreatureCard(hero.getPlayedCreatures().get(currentMove).getEnumCard());
//                            creatureCard.setUsed(true);
////                            battlefield.placeCreature(creatureCard, hero);
//                        }
//                        start = false;
//                    } catch (InputMismatchException e) {
//                        e.printStackTrace();
//                        System.out.println("INVALID INPUT");
//                        scanner.nextLine();
//                    }
//                }
            } else {
                if (freePositions.size() > 0) {
                    ArrayList<CreatureCard> creatures = hero.getPlayedCreatures();
                    Collections.shuffle(creatures);
                    if (creatures.get(0) != null) {
                        CreatureCard creatureCard = new CreatureCard(creatures.get(0).getEnumCard());
                        creatureCard.setUsed(true);
                        battlefield.placeCreature(creatureCard, hero, freePositions.get(0));
                        System.out.println("AI COPIES " + creatureCard.getName() + " AI PLACES IN " + freePositions.get(0));
                    }
                }
            }
            return true;
        }
        return false;
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
