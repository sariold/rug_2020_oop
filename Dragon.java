package nl.rug.oop.rpg;

public class Dragon extends Enemy{

    public Dragon(String description, String name) {
        super(description, name, DefaultStats.DRAGON_HIT_POINTS , DefaultStats.DRAGON_ATTACK_POINTS, DefaultStats.DRAGON_GOLD_VALUE);
    }

    public Dragon(String name) {
        this("Do not test me child for I am a mighty Dragon! Engage me and perish in flames!", name);
    }

    @Override
    public String getSpecies() {
        return "Dragon";
    }
}
