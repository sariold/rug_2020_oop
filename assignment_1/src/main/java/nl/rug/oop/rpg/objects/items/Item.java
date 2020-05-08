package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.DungeonObjects;

import java.io.Serializable;

/**
 * Abstract class Item which will be extended to create items for the game
 */
public abstract class Item extends DungeonObjects implements Collectable, Serializable {

    private static final long serialVersionUID = 12L;

    private boolean used;
    private boolean collected;

    /**
     * Default item constructor which sets description, its collected boolean, and its used boolean
     * @param description Description
     */
    public Item(String description) {
        super(description);
        this.used = false;
        this.collected = false;
    }

    /**
     * Overrides the default collect method because this item can be collected
     * @param player Player
     */
    @Override
    public void collect(Player player) {
        player.addCollectable(this);
        this.collected = true;
    }

    /**
     * Allows the player to use the item and then removes it from their inventory
     * @param player Player
     */
    @Override
    public void use(Player player) {
        this.used = true;
        player.removeUsedItem();
    }

    /**
     * Returns if the item has been used yet
     * @return Boolean value if an item has been used
     */
    public boolean getUsed() {
        return this.used;
    }

    /**
     * Returns if this item has been collected by the player
     * @return Boolean value if an item has been collected
     */
    public boolean getCollected() {
        return this.collected;
    }

}
