package nl.rug.oop.rpg.npcs.healer;

import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;

public abstract class Healer extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 21L;

    private String name;
    private int healPower;


    public Healer(String description, String name, int healPower){
        super(description);
        this.name = name;
        this.healPower = healPower;
        this.engaged = false;
    }

    public Healer(String description){
        this(description, "a Healer", 1);
    }

    public String getName() {
        return this.name;
    }

    public boolean getHealStatus() {
        return this.engaged;
    }

    public void setHealStatus(boolean b) { this.engaged = b; }

    public void heal(Player player) {
        player.increaseHitPoints(this.healPower);
        this.engaged = true;
    }

    public int getHealPower() {
        return this.healPower;
    }

    @Override
    public void engage(Player player, Game game) {
        game.healPlayer(player, this);
    }

    @Override
    public void interact(Player player) {
        heal(player);
    }

    @Override
    public String getType() {
        return "Healer";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
