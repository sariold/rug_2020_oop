package nl.rug.oop.rpg;

public abstract class DungeonObjects implements Inspectable {
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
