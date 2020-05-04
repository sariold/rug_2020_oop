package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public class MagicOrb extends Item implements Serializable {

    private static final long serialVersionUID = 13L;

    private Room room;

    public MagicOrb(Room room) {
        super(DefaultStats.MAGIC_ORB_DESCRIPTION);
        this.room = room;
    }

    @Override
    public void use(Player player) {
        player.setCurrentRoom(room);
        super.use(player);
        System.out.println("You have been teleported to a new room!");
        player.getCurrentRoom().inspect();
    }

    @Override
    public boolean hasCombatUse() {
        return false;
    }

    @Override
    public String toString() {
        return "Magic Orb";
    }
}
