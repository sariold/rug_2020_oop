package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

@Data
public class HealthSpell extends SpellCard {

    private final String type;

    public HealthSpell(EnumCard enumCard) {
        super(enumCard);
        this.type = enumCard.getFace().toString();
    }

    @Override
    public boolean play(Battlefield battlefield, int hero) {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        Hero target;
        boolean heal = this.type.equals("INSTANTHEALTH");
        int dealValue = this.getEnumCard().getValue();
        if(!heal) dealValue *= -1;
        if(hero == 0) {
            if(heal) target = player;
            else target = ai;
        }
        else {
            if(heal) target = ai;
            else target = player;
        }
        target.setHeroHealth(target.getHealth() + dealValue);
        return super.play(battlefield, hero);
    }


}
