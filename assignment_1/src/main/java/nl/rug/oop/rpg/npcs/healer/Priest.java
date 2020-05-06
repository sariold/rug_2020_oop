package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public class Priest extends Healer implements Serializable {

    private static final long serialVersionUID = 26L;

    public Priest(String name) {
        super("May the divine spirits look upon your soul!", name, DefaultStats.PRIEST_HEAL_POWER);
    }


    @Override
    public String getSpecies() {
        return "Priest";
    }
}
