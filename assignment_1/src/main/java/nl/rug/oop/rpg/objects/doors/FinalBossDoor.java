package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.game.combat.Combat;
import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.npcs.enemies.Dragon;
import nl.rug.oop.rpg.npcs.enemies.Enemy;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * Checks if the player has defeated both minibosses, forces the player to fight a final boss
 */
public class FinalBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 5L;

    /**
     * Creates a finalbossdoor that forces the player to fight a final boss in order to win the game
     * @param from From
     * @param to To
     */
    public FinalBossDoor(Room from, Room to) {
        super(DefaultStats.FINAL_BOSS_DOOR, from, to);
    }

    /**
     * Overrides the Door engage method in order to check if both minibosses have been defeated then forces the player
     * to fight the final boss
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
        if(game.getMiniBosses().get(0).isDead() && game.getMiniBosses().get(1).isDead()) {
            this.interact(player);
            player.getCurrentRoom().inspect();
            Dragon dragon = new Dragon("Draco");
            player.getCurrentRoom().addNPC(dragon);
            Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
        }
        System.out.println(TextColor.ANSI_RED + "It seems like someone has some dying to do, I dare you to fight both "
                + "of my sons." + TextColor.ANSI_RESET);
    }

}
