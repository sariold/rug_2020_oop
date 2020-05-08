package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Interactable;
import nl.rug.oop.rpg.objects.DungeonObjects;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * Door class to connect two rooms to each other, allows the player to travel between rooms
 */
public class Door extends DungeonObjects implements Interactable, Serializable {

    private static final long serialVersionUID = 3L;

    private final Room from;
    private final Room to;

    /**
     * Constructor to create a new door object and its connecting rooms
     * @param description Description
     * @param from From
     * @param to To
     */
    public Door(String description, Room from, Room to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Engage method for a player to interact with a door, the type of door may override the engage method
     * @param player Player
     * @param game Game
     */
    public void engage(Player player, Game game) {
        this.interact(player);
        player.getCurrentRoom().inspect();
    }

    /**
     * Player interacts with a door, they will pass through it
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        if(equals(player.getCurrentRoom(), this.to) == 0) player.setCurrentRoom(this.from);
        else player.setCurrentRoom(this.to);
    }

    /**
     * Checks if two rooms are equal based off of their room descriptions
     * @param r1 Room 1
     * @param r2 Room 2
     * @return If two rooms are equal
     */
    public static int equals(Room r1, Room r2) {
        int result = 1;
        if(r1.getDescription().equals(r2.getDescription())) result = 0;
        return result;
    }

}
