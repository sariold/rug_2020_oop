package nl.rug.oop.rpg.objects.items;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * MagicOrb extends abstract class Item and allows player to teleport to another room
 */
public class MagicOrb extends Item implements Serializable {

    private static final long serialVersionUID = 13L;

    private Room room;

    /**
     * Creates a magic orb and sets the room it will teleport the player too
     * @param room
     */
    public MagicOrb(Room room) {
        super(DefaultStats.MAGIC_ORB_DESCRIPTION);
        this.room = room;
    }

    /**
     * Overrides the default use method because this item teleports the player to another room
     * @param player
     */
    @Override
    public void use(Player player) {
        player.setCurrentRoom(room);
        super.use(player);
        System.out.println("You have been teleported to a new room!");
        player.getCurrentRoom().inspect();
    }

    /**
     * This item has no combat use
     * @return
     */
    @Override
    public boolean hasCombatUse() {
        return false;
    }

    /**
     * This item has use outside of combat
     * @return
     */
    @Override
    public boolean hasNonCombatUse() {
        return true;
    }

    /**
     * Returns the type of item
     * @return
     */
    @Override
    public String toString() {
        return "Magic Orb";
    }
}
