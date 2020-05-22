package nl.rug.oop.cardgame.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * Spell to change health
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HealthSpell extends SpellCard {

    /**
     * Create new health spell
     * @param enumCard Enum Card
     */
    public HealthSpell(EnumCard enumCard) {
        super(enumCard);
    }

    /**
     * Heal or damage a hero
     * @param battlefield Battlefield
     * @param hero Hero
     * @param pos Position
     * @return Success of spell
     */
    @Override
    public boolean play(Battlefield battlefield, int hero, int pos) {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        Hero target;
        boolean heal = this.getEnumCard().getFace() == EnumCard.Face.INSTANTHEALTH;
        System.out.println(heal);
        int dealValue = this.getEnumCard().getValue();
        if (!heal) dealValue *= -1;
        if (hero == 0) {
            if (heal) target = player;
            else target = ai;
        } else {
            if (heal) target = ai;
            else target = player;
        }
        target.setHealth(target.getHealth() + dealValue);
        System.out.println(target.getName());
        return super.play(battlefield, hero, pos);
    }

}
