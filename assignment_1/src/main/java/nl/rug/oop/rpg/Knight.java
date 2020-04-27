package nl.rug.oop.rpg;

public class Knight extends Enemy{

    public Knight(String description, String name) {
        super(description, name, DefaultStats.KNIGHT_HIT_POINTS , DefaultStats.KNIGHT_ATTACK_POINTS, DefaultStats.KNIGHT_GOLD_VALUE);
    }

    public Knight(String name) {
        this("Well met! I will fight with honor.", name);
    }

    @Override
    public String getSpecies() {
        return "Knight";
    }
}
