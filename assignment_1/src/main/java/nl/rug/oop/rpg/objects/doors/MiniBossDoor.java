package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.Combat;
import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.npcs.enemies.Enemy;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

public class MiniBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 7L;

    private String wizardColor;
    private boolean defeated;

    public MiniBossDoor(String description, Room from, Room to, String wizardColor, boolean defeated) {
        super(DefaultStats.MINI_BOSS_DOOR, from, to);
        this.wizardColor = wizardColor;
        this.defeated = defeated;
    }

    @Override
    public void engage(Player player, Game game) {
            if (this.isDefeated()) return;
            String type = this.getWizardColor();
            if(type == "Blue") {
                game.getMiniBosses().get(0).setDoor(this);
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(0));
                Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
                if(game.getMiniBosses().get(0).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            } else {
                game.getMiniBosses().get(1).setDoor(this);
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(1));
                Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
                if(game.getMiniBosses().get(1).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            }

    }

    public String getWizardColor() {
        return this.wizardColor;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void defeated() {
        this.defeated = true;
    }
}
