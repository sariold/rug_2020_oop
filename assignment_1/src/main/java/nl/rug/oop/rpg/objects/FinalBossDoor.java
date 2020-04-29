package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;

public class FinalBossDoor extends Door {

    public FinalBossDoor(String description, Room from, Room to) {
        super(DefaultStats.FINAL_BOSS_DOOR, from, to);
    }

}
