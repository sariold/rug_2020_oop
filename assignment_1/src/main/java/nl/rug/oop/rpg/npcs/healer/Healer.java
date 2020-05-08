package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.GameInteract;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;

/**
 * Abstract class Healer extends abstract class DungeonNpc heals a player
 */
public abstract class Healer extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 21L;

    private final int healPower;


    /**
     * Constructor for a healer
     * @param description Description
     * @param name Name
     * @param healPower Heal power
     */
    public Healer(String description, String name, int healPower){
        super(description, name);
        this.healPower = healPower;
        this.engaged = false;
    }

    /**
     * Heal a player for the heal power of this healer
     * @param player Player
     */
    public void heal(Player player) {
        int healValue = GameInteract.getHealValue(player.getHitPoints(), player.getMaxHitPoints(), this.healPower);
        player.setHitPoints(player.getHitPoints() + healValue);
        GUIMessages.playerHealedMessage(healValue);
        this.engaged = true;
    }

    /**
     * Calls the healPlayer method from the game
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
        GameInteract.healPlayer(player, this);
    }

    /**
     * Call this healers heal method
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        heal(player);
    }

    /**
     * Return the type of this healer
     * @return "Healer"
     */
    @Override
    public String getType() {
        return TextColor.ANSI_GREEN +  "Healer" + TextColor.ANSI_RESET;
    }
}
