package nl.rug.oop.rpg;

public class Door extends DungeonObjects implements Interactable {

    private Room room;

    public Door(String description, Room room) {
        super(description);
        this.room = room;
    }

    @Override
    public void interact(Player player) {
        player.setCurrentRoom(this.room);
    }
}
