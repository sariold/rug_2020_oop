package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.objects.Door;
import nl.rug.oop.rpg.objects.MiniBossDoor;

public abstract class MiniBoss extends Enemy {

    private static MiniBossDoor door;

    public MiniBoss(String description, String name, int hitPoints, int attackPoints, int goldValue, MiniBossDoor door){
        super(description, name, hitPoints, attackPoints, goldValue);
        this.door = door;
    }

    public Door getDoor() {
        return this.door;
    }

    public void setDoor(MiniBossDoor newDoor) {
        this.door = newDoor;
    }
}
