package nl.rug.oop.rpg;

public class Room {

    private String description;

    public Room(String description) {
        this.description = description;
    }

    public void inspect() {
        System.out.println(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
