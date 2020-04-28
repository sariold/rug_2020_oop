package nl.rug.oop.rpg;

public abstract class Item extends DungeonObjects implements Collectable{

    public Item(String description) {
        super(description);
    }
}
