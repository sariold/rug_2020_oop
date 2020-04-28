package nl.rug.oop.rpg;

public class MonsterDoor extends Door {

    public MonsterDoor(String description, Room from, Room to) {
        super(description, from, to);
    }

    @Override
    public void interact(Player player) {
        if (player.getCurrentRoom().countEnemies(player.getCurrentRoom().getNPCs()) == 0) super.interact(player);
        else System.out.println("You must slaughter every monster in this room!");
    }

}
