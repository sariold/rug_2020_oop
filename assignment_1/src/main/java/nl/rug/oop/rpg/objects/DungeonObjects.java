package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.interfaces.Inspectable;

import java.io.Serializable;

public abstract class DungeonObjects implements Inspectable, Serializable {

    private static final long serialVersionUID = 4L;

    private String description;

    public DungeonObjects(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
