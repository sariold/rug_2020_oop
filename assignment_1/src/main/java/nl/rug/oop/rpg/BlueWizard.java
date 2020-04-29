package nl.rug.oop.rpg;

import java.util.Random;

public class BlueWizard extends MiniBoss {

    public BlueWizard(String description, String name) {
        super(description, name, DefaultStats.WIZARD_HIT_POINTS , DefaultStats.WIZARD_ATTACK_POINTS, DefaultStats.WIZARD_GOLD_VALUE);
    }

    public BlueWizard(String name) {
        this("You will freeze to Death!!", name);
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
