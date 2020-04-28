package nl.rug.oop.rpg;

public class Orc extends Enemy{

    public Orc(String description, String name) {
        super(description, name, DefaultStats.ORC_HIT_POINTS , DefaultStats.ORC_ATTACK_POINTS, DefaultStats.ORC_GOLD_VALUE);
    }

    public Orc(String name) {
        this("FOR THE HORDE!!", name);
    }

    @Override
    public String getSpecies() {
        return "Orc";
    }
}
