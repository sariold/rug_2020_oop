package nl.rug.oop.rpg;

public abstract class Healer extends DungeonNpc{

    private String name;
    private int healPower;

    public Healer(String description){
        super(description);
        this.name = "a Healer";
        this.healPower = 1;
    }

    public Healer(String description, String name, int healPower){
        super(description);
        this.name = name;
        this.healPower = healPower;
    }

    public String getName() {
        return this.name;
    }

    public void heal(Player player) {
        player.increaseHitPoints(this.healPower);
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
