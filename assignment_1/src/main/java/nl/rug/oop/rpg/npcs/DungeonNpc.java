package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.Player;
import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;
import nl.rug.oop.rpg.extra.TextColor;

import java.io.Serializable;

/**
 * Abstract Class for dungeon npc, is a super class that implements interactable, inspectable, ands serializable
 */
public abstract class DungeonNpc implements Inspectable, Interactable, Serializable {

    private static final long serialVersionUID = 18L;

    private String description;
    private String name;
    protected boolean engaged;

    /**
     * Creates a DungeonNPC with a description and a name
     * @param description
     * @param name
     */
    public DungeonNpc(String description, String name) {
        this.name = name;
        this.description = description;
    }

    /**
     * Boolean function to determine if a npc has been engaged
     * @return Boolean value
     */
    public boolean hasBeenEngaged() { return this.engaged; }

    /**
     * Get the description of a npc
     * @return The string description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for a npc
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

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
     * @param player
     * @param game
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
