package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.DungeonNpc;
import nl.rug.oop.rpg.npcs.enemies.Enemy;
import nl.rug.oop.rpg.objects.doors.Door;
import nl.rug.oop.rpg.objects.items.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Room object that extends dungeonobjects and contains npcs, doors, and items
 */
public class Room extends DungeonObjects implements Serializable {

    private static final long serialVersionUID = 9L;

    private ArrayList<Door> doors;
    private ArrayList<DungeonNpc> NPCs;
    private ArrayList<Collectable> items;

    /**
     * Creats a room and sets the npc, door, and item arraylists
     * @param description
     */
    public Room(String description) {
        super(description);
        this.doors = new ArrayList<Door>();
        this.NPCs = new ArrayList<DungeonNpc>();
        this.items = new ArrayList<Collectable>();
    }

    /**
     * Returns the items arraylist of a room
     * @return Arraylist of items in the room
     */
    public ArrayList<Collectable> getItems() {
        return new ArrayList<Collectable>(this.items);
    }

    /**
     * Adds an item to the items arraylist of a room
     * @param collectable
     */
    public void addItem(Collectable collectable) {
        if(collectable != null) items.add(collectable);
    }

    /**
     * Removes an item from the items arraylist of a room
     */
    public void removeItem() {
        for (int i = this.items.size() - 1; i > -1; i--) {
            if (((Item)this.items.get(i)).getCollected()) this.items.remove(i);
        }
    }

    /**
     * Adds a door to the doors arraylist of a room
     * @param door
     */
    public void addDoor(Door door) {
        if(door != null) doors.add(door);
    }

    /**
     * Returns the doors arraylist of a room
     * @return A door arraylist of a room
     */
    public ArrayList<Door> getDoors() {
        return new ArrayList<Door>(this.doors);
    }

    /**
     * Prints all of the doors in a room
     */
    public void printDoors() {
        doors.forEach(door -> System.out.println(door.getDescription()));
    }

    /**
     * Adds an npc to the npc arraylist of a room
     * @param npc
     */
    public void addNPC(DungeonNpc npc) {
        if(npc != null) NPCs.add(npc);
    }

    /**
     * Removes dead npcs from npc arraylist of a room
     */
    public void removeDeadNPC() {
        for (int i = this.NPCs.size() - 1; i >= 0; i--) {
            DungeonNpc currNPC = this.NPCs.get(i);
            if (currNPC.hasBeenEngaged()) this.NPCs.remove(i);
        }
    }

    /**
     * Returns an arraylist of npcs from current room
     * @return Arraylist of npcs in the room
     */
    public ArrayList<DungeonNpc> getNPCs() {
        return new ArrayList<DungeonNpc>(this.NPCs);
    }

    /**
     * Counts how many dead npcs are in a current room
     * @param npcs
     * @return How many npcs are dead in a room
     */
    public int countEnemies(ArrayList<DungeonNpc> npcs) {
        int count = (int) npcs.stream().filter(npc -> npc instanceof Enemy).count();
        return count;
    }

}
