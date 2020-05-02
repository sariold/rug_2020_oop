package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;

import java.io.Serializable;

public class FinalBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 5L;

    public FinalBossDoor(String description, Room from, Room to) {
        super(DefaultStats.FINAL_BOSS_DOOR, from, to);
    }

}
