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
        if(equals(player.getCurrentRoom(), this.to) == 0) player.setCurrentRoom(this.from);
        else player.setCurrentRoom(this.to);
    }

    public static int equals(Room r1, Room r2) {
        int result = 1;
        if(r1.getDescription().equals(r2.getDescription())) result = 0;
//        System.out.println(result);
        return result;
    }

    public Room returnTo() {
        return this.to;
    }
}
