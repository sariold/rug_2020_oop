package nl.rug.oop.rpg;

import java.util.Random;

public abstract class Enemy extends DungeonNpc implements Attackable {

    private String name;
    private int hitPoints;
    private int attackPoints;
    private int goldValue;

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
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 16) {
            System.out.println(TextColor.ANSI_RED + "Critical Hit!" + TextColor.ANSI_RESET);
            attacked.reduceHitPoints(2 * this.attackPoints);
        } else {
            attacked.reduceHitPoints(this.attackPoints);
        }
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
