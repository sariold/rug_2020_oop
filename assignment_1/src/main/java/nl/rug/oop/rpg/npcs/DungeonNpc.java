package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;
import nl.rug.oop.rpg.extra.TextColor;

import java.io.Serializable;

/**
 * Abstract Class for dungeon npc, is a super class that implements interactable, inspectable, ands serializable
 */
public abstract class DungeonNpc implements Inspectable, Interactable, Serializable {

    private static final long serialVersionUID = 18L;

    private final String description;
    private final String name;
    protected boolean engaged;
    protected int hitPoints;
    protected int attackPoints;

    /**
     * Creates a DungeonNPC with a description and a name
     * @param description Description
     * @param name Name
     */
    public DungeonNpc(String description, String name) {
        this.name = name;
        this.description = description;
        this.hitPoints = 1;
        this.attackPoints = 1;
    }

    /**
     * sets the hit points of this npc
     * @param hitPoints Hit points
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Boolean function to determine if a npc has been engaged
     * @return Boolean value
     */
    public boolean hasBeenEngaged() { return this.engaged; }

    /**
     * Return the type of dungeonnpc
     * @return "DungeonNPC"
     */
    public String getType(){
        return "DungeonNpc";
    }

    /**
     * Function that will be overrided for the type of npc species
     * @return String of species
     */
    public String getSpecies(){
        return "";
    }

    /**
     * Engage method for interacting with npcs
     * @param player Player
     * @param game Game
     */
    public void engage(Player player, Game game){}

    /**
     * Return the name of a npc
     * @return String of npc name
     */
    public String getName() { return this.name;}

    /**
     * Inspect method to return description of a npc
     */
    @Override
    public void inspect() {
        System.out.println(TextColor.ANSI_PURPLE + description + TextColor.ANSI_RESET);
    }
}
