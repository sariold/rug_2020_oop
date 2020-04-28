package nl.rug.oop.rpg;

public class MagicOrb extends Item {

    private Room room;

    public MagicOrb(String description, Room room) {
        super(description);
        this.room = room;
    }

    @Override
    public void use(Player player) {
        player.setCurrentRoom(room);
        super.use(player);
    }

    @Override
    public String toString() {
        return "Magic Orb";
    }
}
