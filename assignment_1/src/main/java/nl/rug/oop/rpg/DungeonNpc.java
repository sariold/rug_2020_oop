package nl.rug.oop.rpg;

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

    @Override
    public void inspect() {
        System.out.println(description);
    }
}
