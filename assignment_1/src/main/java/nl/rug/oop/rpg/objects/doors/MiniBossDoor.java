package nl.rug.oop.rpg.objects.doors;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;

/**
 * Creates a minibossdoor which forces the player to engage in combat with a miniboss
 */
public class MiniBossDoor extends Door implements Serializable {

    private static final long serialVersionUID = 7L;

    private final String wizardColor;
    private boolean defeated;

    /**
     * Creates a type of door which forces the player to fight a miniboss if they dare interact with this door
     * @param from From
     * @param to To
     * @param wizardColor Wizard color
     * @param defeated Defeated boolean
     */
    public MiniBossDoor(Room from, Room to, String wizardColor, boolean defeated) {
        super(DefaultStats.MINI_BOSS_DOOR, from, to);
        this.wizardColor = wizardColor;
        this.defeated = defeated;
    }

    /**
     * Overrides the default Door engage method because this door makes you engage in combat with either a red
     * or a blue wizard immediately upon interacting with this door
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
            this.interact(player);
            if (this.isDefeated()) return;
            String type = this.getWizardColor();
            if(type.equals("Blue")) {
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(0));
                player.getCurrentRoom().getNPCs().get(0).engage(player, game);
                if(game.getMiniBosses().get(0).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            } else {
                player.getCurrentRoom().addNPC(game.getMiniBosses().get(1));
                player.getCurrentRoom().getNPCs().get(0).engage(player, game);
                if(game.getMiniBosses().get(1).isDead()) {
                    this.defeated();
                    this.setDescription("This was once a mini boss door, congrats on surviving the battle");
                }
            }

    }

    /**
     * Return the color of the miniboss in the room, two types of minibosses, either a blue or a red wizard
     * @return The type of wizard miniboss
     */
    public String getWizardColor() {
        return this.wizardColor;
    }

    /**
     * Boolean value that determines if a miniboss has been defeated
     * @return The boolean value of defeated
     */
    public boolean isDefeated() {
        return defeated;
    }

    /**
     * Sets the boolean value defeated to true if the miniboss has been defeated
     */
    public void defeated() {
        this.defeated = true;
    }
}
