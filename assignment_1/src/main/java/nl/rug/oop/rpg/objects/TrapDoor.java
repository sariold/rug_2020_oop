package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;

import java.io.Serializable;

public class TrapDoor extends Door implements Serializable {

    private static final long serialVersionUID = 10L;

    private Room from;
    private int attackPoints;

    public TrapDoor(String description, Room from, int attackPoints) {
        super(description, from, null);
        this.attackPoints = attackPoints;
    }

    @Override
    public void interact(Player player) {
        String color = TextColor.ANSI_RED;
        player.reduceHitPoints(this.attackPoints);
        System.out.println(color + "This is a trap door you fool, you lose " + this.attackPoints + " health!" + TextColor.ANSI_RESET);
    }

    @Override
    public void engage(Player player, Game game) {
        this.interact(player);
        if (player.isDead()) {
            System.out.println(TextColor.ANSI_RED + "You have been slain by the mighty trap door!" + TextColor.ANSI_RESET);
            game.gameOver();
        }
        player.getCurrentRoom().inspect();
    }
}
