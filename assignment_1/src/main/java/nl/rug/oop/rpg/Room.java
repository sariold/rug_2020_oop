package nl.rug.oop.rpg;

import java.util.ArrayList;

public class Room extends DungeonObjects {

    private ArrayList<Door> doors;
    private ArrayList<DungeonNpc> NPCs;
    private ArrayList<Collectable> items;

    public Room(String description) {
        super(description);
        this.doors = new ArrayList<Door>();
        this.NPCs = new ArrayList<DungeonNpc>();
        this.items = new ArrayList<Collectable>();
    }

    public ArrayList<Collectable> getItems() {
        return new ArrayList<Collectable>(this.items);
    }

    public void addItem(Collectable collectable) {
        if(collectable != null) items.add(collectable);
    }

    public void removeItem() {
        for (int i = this.items.size() - 1; i > -1; i--) {
            if (((Item)this.items.get(i)).getCollected()) this.items.remove(i);
        }
    }

    public void addDoor(Door door) {
        if(door != null) doors.add(door);
    }

    public ArrayList<Door> getDoors() {
        return new ArrayList<Door>(this.doors);
    }

    public void printDoors() {
        doors.forEach(door -> System.out.println(door.getDescription()));
    }

    public void addNPC(DungeonNpc npc) {
        if(npc != null) NPCs.add(npc);
    }

    public void removeDeadNPC() {
        for (int i = this.NPCs.size() - 1; i >= 0; i--) {
            DungeonNpc currNPC = this.NPCs.get(i);
            if (currNPC instanceof Enemy && ((Enemy) currNPC).isDead()) this.NPCs.remove(i);
            if (currNPC instanceof Healer && ((Healer) currNPC).getHealStatus()) this.NPCs.remove(i);
            if (currNPC instanceof Trader && ((Trader) currNPC).getTradeStatus()) this.NPCs.remove(i);
        }
    }

    public ArrayList<DungeonNpc> getNPCs() {
        return new ArrayList<DungeonNpc>(this.NPCs);
    }

    public int countEnemies(ArrayList<DungeonNpc> npcs) {
        int count = (int) npcs.stream().filter(npc -> npc instanceof Enemy).count();
        return count;
    }

}
