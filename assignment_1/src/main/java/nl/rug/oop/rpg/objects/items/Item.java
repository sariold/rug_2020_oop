package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.DungeonObjects;

import java.io.Serializable;

public abstract class Item extends DungeonObjects implements Collectable, Serializable {

    private static final long serialVersionUID = 12L;

    private boolean used;
    private boolean collected;

    public Item(String description) {
        super(description);
        this.used = false;
        this.collected = false;
    }

    @Override
    public void collect(Player player) {
        player.addCollectable(this);
        this.collected = true;
    }

    @Override
    public void use(Player player) {
        this.used = true;
        player.removeUsedItem();
    }

    public boolean getUsed() {
        return this.used;
    }

    public void setUsed(boolean b) {
        this.used = b;
    }

    public boolean getCollected() {
        return this.collected;
    }

    public void setCollected(boolean b) {
        this.collected = b;
    }

}
