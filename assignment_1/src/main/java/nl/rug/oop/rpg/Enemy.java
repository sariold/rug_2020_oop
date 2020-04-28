package nl.rug.oop.rpg;

import java.util.Random;

public abstract class Enemy extends DungeonNpc implements Attackable {

    private String name;
    private int hitPoints;
    private int attackPoints;
    private int goldValue;
    private boolean burned;
    private boolean frozen;

    public Enemy(String description) {
        this(description, "an Enemy", 1, 1, 1);
    }

    public Enemy(String description, String name, int hitPoints, int attackPoints, int goldValue) {
        super(description);
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.goldValue = goldValue;
    }

    public boolean isBurned() {
        return burned;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void burn(){
        this.burned = true;
    }

    public void removeBurn() {
        this.burned = false;
    }

    public void freeze() {
        this.frozen = true;
    }

    public void removeFreeze() {
        this.frozen = false;
    }

    public int getGoldValue() {
        return this.goldValue;
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
        System.out.println(TextColor.ANSI_RED + "You are attacked by " + this.getName() + TextColor.ANSI_RESET);
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 16) {
            System.out.println(TextColor.ANSI_RED + "Critical Hit!" + TextColor.ANSI_RESET);
            attacked.reduceHitPoints(2 * this.attackPoints);
        } else {
            attacked.reduceHitPoints(this.attackPoints);
        }
        System.out.println(TextColor.ANSI_RED + "You are at " +  attacked.getHitPoints() + " health!" + TextColor.ANSI_RESET);
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

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void interact(Player player) {
        attack(player);
    }

    @Override
    public String getType() {
        return "Enemy";
    }
}
