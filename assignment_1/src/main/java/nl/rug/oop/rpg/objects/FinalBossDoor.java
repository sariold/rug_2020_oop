package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.Combat;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.npcs.Dragon;
import nl.rug.oop.rpg.npcs.Enemy;

import java.io.Serializable;

public class FinalBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 5L;

    public FinalBossDoor(String description, Room from, Room to) {
        super(DefaultStats.FINAL_BOSS_DOOR, from, to);
    }

    @Override
    public void engage(Player player, Game game) {
        if(game.getMiniBosses().get(0).isDead() && game.getMiniBosses().get(1).isDead()) {
            this.interact(player);
            player.getCurrentRoom().inspect();
            Dragon dragon = new Dragon("Draco");
            player.getCurrentRoom().addNPC(dragon);
            Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
        }
        this.inspect();
    }
}
