package nl.rug.oop.rpg;

public class AttackDoor extends Door implements Attackable {
    private int hitPoints;
    private int attackPoints;

    public AttackDoor(String description, Room from, Room to, int hitPoints, int attackPoints) {
        super(description, from, to);
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
    }

    @Override
    public void attack(Attackable attacked) {

    }

    @Override
    public void reduceHitPoints(int value) {
        this.hitPoints -= value;
    }

    @Override
    public void increaseHitPoints(int value) {
        this.hitPoints += value;
    }

    @Override
    public boolean isDead() {
        return this.hitPoints <= 0;
    }
}
