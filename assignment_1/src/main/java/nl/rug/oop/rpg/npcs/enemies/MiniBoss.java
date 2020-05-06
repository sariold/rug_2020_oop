package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.objects.doors.Door;
import nl.rug.oop.rpg.objects.doors.MiniBossDoor;

import java.io.Serializable;

/**
 * abstract class MiniBoss extends abstract class Enemy must be defeated to fight the boss of the game
 */
public abstract class MiniBoss extends Enemy implements Serializable {

    private static final long serialVersionUID = 24L;

    private static MiniBossDoor door;

    /**
     * Constructor for a mini boss
     * @param description
     * @param name
     * @param hitPoints
     * @param attackPoints
     * @param goldValue
     * @param door
     */
    public MiniBoss(String description, String name, int hitPoints, int attackPoints, int goldValue, MiniBossDoor door){
        super(description, name, hitPoints, attackPoints, goldValue);
        this.door = door;
    }

    /**
     * returns the door this mini boss is associated with
     * @return door object
     */
    public Door getDoor() {
        return this.door;
    }

    /**
     * sets the door this mini boss is associated with
     * @param newDoor
     */
    public void setDoor(MiniBossDoor newDoor) {
        this.door = newDoor;
    }
}
