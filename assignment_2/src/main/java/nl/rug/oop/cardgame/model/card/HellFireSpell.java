package nl.rug.oop.cardgame.model.card;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import java.util.ArrayList;

public class HellFireSpell extends SpellCard {

    public HellFireSpell(EnumCard enumCard) {
        super(enumCard);
    }

    @Override
    public boolean play(Battlefield battlefield, int hero, int pos) {
        if(hero == 1) {
            boolean playerHasCreatures = notEmptyBattlefield(battlefield.getPlayer());
            if(playerHasCreatures) {
                dealDamageToCreatures(battlefield.getPlayer());
                battlefield.setHellFire(true, battlefield.getPlayer());
                return super.play(battlefield, hero, pos);
            } else return false;
        }
        dealDamageToCreatures(battlefield.getAi());
        battlefield.setHellFire(true, battlefield.getAi());
        return super.play(battlefield, hero, pos);
    }

    private void dealDamageToCreatures(Hero hero) {
        ArrayList<CreatureCard> enemyCreatures = hero.getPlayedCreatures();
        for(CreatureCard c: enemyCreatures) {
            if(c != null) c.setCreatureHealth(c.getCreatureHealth() - 2);
        }
    }

}
