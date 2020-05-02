package nl.rug.oop.rpg.objects;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;

public class MonsterDoor extends Door {

    public MonsterDoor(String description, Room from, Room to) {
        super(DefaultStats.MONSTER_DOOR_DESCRIPTION, from, to);
    }

    @Override
    public void interact(Player player) {
        if (player.getCurrentRoom().countEnemies(player.getCurrentRoom().getNPCs()) == 0) super.interact(player);
        else {
            String color = TextColor.ANSI_RED;
            System.out.println(color + "You must slaughter every monster in this room!" + TextColor.ANSI_RESET);
        }
    }

}
