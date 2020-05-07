package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.game.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * Creates a type of door that only lets the player enter if they have defeated all enemy npcs in the current room
 */
public class MonsterDoor extends Door implements Serializable {

    private static final long serialVersionUID = 8L;

    /**
     * Creates a monsterdoor that only lets the player enter if they slaughter all enemy npcs in current room
     * @param from From
     * @param to To
     */
    public MonsterDoor(Room from, Room to) {
        super(DefaultStats.MONSTER_DOOR_DESCRIPTION, from, to);
    }

    /**
     * Overrides default interact method because this door has a requirement from the user if
     * they want to pass through
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        if (player.getCurrentRoom().countEnemies(player.getCurrentRoom().getNPCs()) == 0) super.interact(player);
        else {
            String color = TextColor.ANSI_RED;
            System.out.println(color + "You must slaughter every monster in this room!" + TextColor.ANSI_RESET);
        }
    }

}
