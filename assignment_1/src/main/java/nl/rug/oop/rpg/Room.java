package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room extends DungeonObjects {

    private ArrayList<Door> doors;
    private ArrayList<DungeonNpc> NPCs;

    public Room(String description) {
        super(description);
        this.doors = new ArrayList<Door>();
    }

    public void addDoor(Door door) {
        if(door != null) doors.add(door);
    }

    public ArrayList<Door> getDoors() {
        return new ArrayList<Door>(this.doors);
    }

    public void addNPC(DungeonNpc npc) {
        if(npc != null) NPCs.add(npc);
    }

    public ArrayList<DungeonNpc> getNPCs() {
        return new ArrayList<DungeonNpc>(this.NPCs);
    }


}
