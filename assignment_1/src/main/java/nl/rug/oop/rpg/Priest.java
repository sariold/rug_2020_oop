package nl.rug.oop.rpg;

public class Priest extends Healer{



    public Priest(String name) {
        super("May the divine spirits look upon your soul!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    @Override
    public String getSpecies() {
        return "Priest";
    }
}
