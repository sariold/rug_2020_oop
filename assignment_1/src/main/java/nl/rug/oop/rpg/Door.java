package nl.rug.oop.rpg;

public class Door extends DungeonObjects implements Interactable {

    private Room from;
    private Room to;

    public Door(String description, Room from, Room to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void interact(Player player) {
        player.setCurrentRoom(this.to);
    }
}
