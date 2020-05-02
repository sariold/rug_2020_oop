package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.objects.MiniBossDoor;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;
import java.util.Random;

public class RedWizard extends MiniBoss implements Serializable {

    private static final long serialVersionUID = 28L;

    public RedWizard(String description, String name, MiniBossDoor door) {
        super(description, name, DefaultStats.WIZARD_HIT_POINTS , DefaultStats.WIZARD_ATTACK_POINTS, DefaultStats.WIZARD_GOLD_VALUE, door);
    }

    public RedWizard(String name) {
        this("You will burn to Death!!", name, null);
    }

    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            ((Player)attacked).burn();
        }
        super.attack(attacked);
    }

    @Override
    public String getSpecies() {
        return "Red Wizard";
    }

}
