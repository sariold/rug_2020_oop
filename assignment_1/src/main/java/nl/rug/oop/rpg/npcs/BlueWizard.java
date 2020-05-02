package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.objects.MiniBossDoor;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;
import java.util.Random;

public class BlueWizard extends MiniBoss implements Serializable {

    private static final long serialVersionUID = 15L;

    public BlueWizard(String description, String name, MiniBossDoor door) {
        super(description, name, DefaultStats.WIZARD_HIT_POINTS , DefaultStats.WIZARD_ATTACK_POINTS, DefaultStats.WIZARD_GOLD_VALUE, door);
    }

    public BlueWizard(String name) {
        this("You will freeze to Death!!", name, null);
    }

    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int chance = r.nextInt(101);
        if (chance < 21) {
            ((Player)attacked).freeze();
        }
        super.attack(attacked);
    }

    @Override
    public String getSpecies() {
        return "Blue Wizard";
    }
}
