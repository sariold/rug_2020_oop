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

    private final ArrayList<Door> doors;
    private final ArrayList<DungeonNpc> NPCs;
    private final ArrayList<Collectable> items;

    /**
     * Creats a room and sets the npc, door, and item arraylists
     * @param description Description
     */
    public Room(String description) {
        super(description);
        this.doors = new ArrayList<>();
        this.NPCs = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    /**
     * Returns the items arraylist of a room
     * @return Arraylist of items in the room
     */
    public ArrayList<Collectable> getItems() {
        return new ArrayList<>(this.items);
    }

    /**
     * Adds an item to the items arraylist of a room
     * @param collectable Collectable
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
     * @param door Door
     */
    public void addDoor(Door door) {
        if(door != null) doors.add(door);
    }

    /**
     * Returns the doors arraylist of a room
     * @return A door arraylist of a room
     */
    public ArrayList<Door> getDoors() {
        return new ArrayList<>(this.doors);
    }

    /**
     * Adds an npc to the npc arraylist of a room
     * @param npc NPC
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
        return new ArrayList<>(this.NPCs);
    }

    /**
     * Counts how many dead npcs are in a current room
     * @param npcs NPCS
     * @return How many npcs are dead in a room
     */
    public int countEnemies(ArrayList<DungeonNpc> npcs) {
        return (int) npcs.stream().filter(npc -> npc instanceof Enemy).count();
    }

}
