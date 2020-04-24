package nl.rug.oop.rpg;

public abstract class Enemy extends DungeonNpc implements Attackable{

    private String name;
    private int hitPoints;
    private int attackPoints;

    public Enemy(String description) {
        super(description);
        this.name = "an Enemy";
        this.hitPoints = 1;
        this.attackPoints = 1;
    }

    public Enemy(String description, String name, int hitPoints, int attackPoints) {
        super(description);
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
    }

    public String getName() {
        return this.name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    @Override
    public void attack(Attackable attacked) {
        attacked.reduceHitPoints(this.attackPoints);
    }

    @Override
    public void reduceHitPoints(int value) {
        this.hitPoints -= value;
    }

    @Override
    public boolean isDead() {
        return this.hitPoints <= 0;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
