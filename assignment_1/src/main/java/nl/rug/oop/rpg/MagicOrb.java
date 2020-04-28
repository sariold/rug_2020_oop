package nl.rug.oop.rpg;

public class MagicOrb extends Item {

    private Room room;

    public MagicOrb(Room room) {
        super(DefaultStats.MAGIC_ORB_DESCRIPTION);
        this.room = room;
    }

    @Override
    public void use(Player player) {
        player.setCurrentRoom(room);
        super.use(player);
        System.out.println("You have been teleported to a new room!");
        player.getCurrentRoom().inspect();
    }

    @Override
    public String toString() {
        return "Magic Orb";
    }
}
