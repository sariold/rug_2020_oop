package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * TrapDoor class tricks the user by impersonating a normal door but deals damage instead and leads nowhere
 */
public class TrapDoor extends Door implements Serializable {

    private static final long serialVersionUID = 10L;

    private final int attackPoints;

    /**
     * Creates a trap door and deals a certain amount of damage to the player
     * @param description Description
     * @param from From
     * @param attackPoints Attack points
     */
    public TrapDoor(String description, Room from, int attackPoints) {
        super(description, from, null);
        this.attackPoints = attackPoints;
    }

    /**
     * Overrides the default door interact method because this door deals damage to the player
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        String color = TextColor.ANSI_RED;
        player.setHitPoints(player.getHitPoints() - this.attackPoints);
        System.out.println(color + "This is a trap door you fool, you lose " + this.attackPoints + " health!"
                + TextColor.ANSI_RESET);
    }

    /**
     * Overrides the default Door engage method because this door must check if the player has died as a result of the
     * damage they have taken
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
        this.interact(player);
        if (player.isDead()) {
            System.out.println(TextColor.ANSI_RED + "You have been slain by the mighty trap door!"
                    + TextColor.ANSI_RESET);
            game.gameOver();
        }
        player.getCurrentRoom().inspect();
    }
}
