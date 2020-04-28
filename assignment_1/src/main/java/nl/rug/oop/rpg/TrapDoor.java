package nl.rug.oop.rpg;

public class TrapDoor extends Door {

    private Room from;
    private Room to;
    private int attackPoints;

    public TrapDoor(String description, Room from, Room to, int attackPoints) {
        super(description, from, to);
        this.attackPoints = attackPoints;
    }

    @Override
    public void interact(Player player) {
        String color = TextColor.ANSI_RED;
        player.reduceHitPoints(this.attackPoints);
        System.out.println(color + "This is a trap door you fool, you lose " + this.attackPoints + " health!" + TextColor.ANSI_RESET);
    }
}