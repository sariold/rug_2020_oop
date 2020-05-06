package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * Creates a minibossdoor which forces the player to engage in combat with a miniboss
 */
public class MiniBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 7L;

    private String wizardColor;
    private boolean defeated;

    /**
     * Creates a type of door which forces the player to fight a miniboss if they dare interact with this door
     * @param description
     * @param from
     * @param to
     * @param wizardColor
     * @param defeated
     */
    public MiniBossDoor(String description, Room from, Room to, String wizardColor, boolean defeated) {
        super(DefaultStats.MINI_BOSS_DOOR, from, to);
        this.wizardColor = wizardColor;
        this.defeated = defeated;
    }

    /**
     * Ovverides the default Door engage method because this door makes you engage in combat with either a red
     * or a blue wizard immediately upon interacting with this door
     * @param player
     * @param game
     */
    @Override
    public void engage(Player player, Game game) {
            if (this.isDefeated()) return;
            String type = this.getWizardColor();
            if(type == "Blue") {
                game.getMiniBosses().get(0).setDoor(this);
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(0));
//                Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
                player.getCurrentRoom().getNPCs().get(0).engage(player, game);
                if(game.getMiniBosses().get(0).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            } else {
                game.getMiniBosses().get(1).setDoor(this);
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(1));
//                Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), game);
                player.getCurrentRoom().getNPCs().get(0).engage(player, game);
                if(game.getMiniBosses().get(1).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            }

    }

    /**
     * Return the color of the miniboss in the room, two types of minibosses, either a blue or a red wizard
     * @return
     */
    public String getWizardColor() {
        return this.wizardColor;
    }

    /**
     * Boolean value that determines if a miniboss has been defeated
     * @return
     */
    public boolean isDefeated() {
        return defeated;
    }

    /**
     * Sets the boolean value defeated to true if the miniboss has been deafeated
     */
    public void defeated() {
        this.defeated = true;
    }
}