package nl.rug.oop.rpg;

public class Rat extends Enemy{

    public Rat(String description, String name) {
        super(description, name, DefaultStats.RAT_HIT_POINTS , DefaultStats.RAT_ATTACK_POINTS, DefaultStats.RAT_GOLD_VALUE);
    }

    public Rat(String name) {
        this("*angrily* SQUEEK!!", name);
    }

    @Override
    public String getSpecies() {
        return "Rat";
    }
}
