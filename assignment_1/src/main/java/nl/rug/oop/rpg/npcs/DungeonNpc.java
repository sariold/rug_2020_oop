package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.interfaces.Inspectable;
import nl.rug.oop.rpg.interfaces.Interactable;
import nl.rug.oop.rpg.TextColor;

public abstract class DungeonNpc implements Inspectable, Interactable {
    private String description;

    public DungeonNpc(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType(){
        return "DungeonNpc";
    }

    public String getSpecies(){
        return "";
    }

    @Override
    public void inspect() {
        System.out.println(TextColor.ANSI_PURPLE + description + TextColor.ANSI_RESET);
    }
}
