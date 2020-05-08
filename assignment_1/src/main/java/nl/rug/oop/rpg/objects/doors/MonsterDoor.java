package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.MonsterKey;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a type of door that only lets the player enter if they have defeated all enemy npcs in the current room
 */
public class MonsterDoor extends Door implements Serializable {

    private boolean defeated;

    private static final long serialVersionUID = 8L;

    /**
     * Creates a monsterdoor that only lets the player enter if they slaughter all enemy npcs in current room
     * @param from From
     * @param to To
     */
    public MonsterDoor(Room from, Room to, Boolean defeated) {
        super(DefaultStats.MONSTER_DOOR_DESCRIPTION, from, to);
        this.defeated = defeated;
    }

    /**
     * Overrides default interact method because this door has a requirement from the user if
     * they want to pass through
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        if (getDefeated() || player.getCurrentRoom().countEnemies(player.getCurrentRoom().getNPCs()) == 0 ||
                checkKey(player)) {
            super.interact(player);
            this.defeated = true;
            this.setDescription("A broken monster door");
        } else {
            String color = TextColor.ANSI_RED;
            System.out.println(color + "You must slaughter every monster in this room!" + TextColor.ANSI_RESET);
        }
    }

    /**
     * Check if a monster door is defeated
     * @return Boolean defeated
     */
    public boolean getDefeated() {
        return this.defeated;
    }

    /**
     * Checks if the player has a monster key in their inventory
     * @param player Player
     * @return Boolean if they have a key
     */
    public boolean checkKey(Player player) {
        ArrayList<Collectable> newInventory = player.getInventory();
        if(player.getInventory().size() > 0) {
            for (Collectable c : newInventory) {
                if (c instanceof MonsterKey) {
                    c.use(player);
                    return true;
                }
            }
        }
        return false;
    }


}
