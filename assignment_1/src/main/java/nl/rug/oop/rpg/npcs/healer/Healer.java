package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.GameInteract;
import nl.rug.oop.rpg.game.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;

/**
 * Abstract class Healer extends abstract class DungeonNpc heals a player
 */
public abstract class Healer extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 21L;

    private int healPower;


    /**
     * Constructor for a healer
     * @param description
     * @param name
     * @param healPower
     */
    public Healer(String description, String name, int healPower){
        super(description, name);
        this.healPower = healPower;
        this.engaged = false;
    }

    /**
     * Set engaged to b
     * @param b
     */
    public void setHealStatus(boolean b) { this.engaged = b; }

    /**
     * Heal a player for the heal power of this healer
     * @param player
     */
    public void heal(Player player) {
        player.increaseHitPoints(this.healPower);
        this.engaged = true;
    }

    /**
     * Calls the healPlayer method from the game
     * @param player
     * @param game
     */
    @Override
    public void engage(Player player, Game game) {
        GameInteract.healPlayer(player, this);
    }

    /**
     * Call this healers heal method
     * @param player
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
