package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room extends DungeonObjects {

    private ArrayList<Door> doors;

    public Room(String description) {
        super(description);
        this.doors = new ArrayList<Door>();
    }

    public void addDoor(Door door) {
        doors.add(door);
    }

    public ArrayList<Door> getDoors() {
        return new ArrayList<Door>(this.doors);
    }

}
