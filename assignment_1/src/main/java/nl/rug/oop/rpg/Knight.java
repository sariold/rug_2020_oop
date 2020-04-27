package nl.rug.oop.rpg;

public class Knight extends Enemy{

    public Knight(String description, String name) {
        super(description, name, DefaultStats.KNIGHT_HIT_POINTS , DefaultStats.KNIGHT_ATTACK_POINTS);
    }

    public Knight(String name) {
        super("Well met! I will fight with honor.", name, DefaultStats.KNIGHT_HIT_POINTS, DefaultStats.KNIGHT_ATTACK_POINTS);
    }

    @Override
    public String getSpecies() {
        return "Knight";
    }
}
