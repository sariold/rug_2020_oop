package nl.rug.oop.rpg;

public abstract class Healer extends DungeonNpc{

    private String name;
    private int healPower;
    private boolean hasHealed;


    public Healer(String description, String name, int healPower){
        super(description);
        this.name = name;
        this.healPower = healPower;
        this.hasHealed = false;
    }

    public Healer(String description){
        this(description, "a Healer", 1);
    }

    public String getName() {
        return this.name;
    }

    public boolean getHealStatus() {
        return this.hasHealed;
    }

    public void heal(Player player) {
        player.increaseHitPoints(this.healPower);
    }

    @Override
    public void interact(Player player) {
        heal(player);
        this.hasHealed = true;
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
