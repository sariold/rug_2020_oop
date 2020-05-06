package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.*;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

/**
 * abstract class Enemy extends abstract class DungeonNpc implements Attackable and can be fought by a player
 */
public abstract class Enemy extends DungeonNpc implements Attackable, Serializable {

    private static final long serialVersionUID = 19L;

    private int hitPoints;
    private int maxHitPoints;
    private int attackPoints;
    private int goldValue;
    private boolean burned;
    private boolean frozen;

    /**
     * Constructor for an enemy
     * @param description
     * @param name
     * @param hitPoints
     * @param attackPoints
     * @param goldValue
     */
    public Enemy(String description, String name, int hitPoints, int attackPoints, int goldValue) {
        super(description, name);
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.goldValue = goldValue;
        this.engaged = false;
    }

    /**
     * Constructor for an enemy with only a description
     * @param description
     */
    public Enemy(String description) {
        this(description, "an Enemy", 1, 1, 1);
    }

    /**
     * returns whether the enemy is burned
     * @return burned
     */
    public boolean isBurned() {
        return burned;
    }

    /**
     * returns whether the enemy is frozen
     * @return frozen
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * burns this enemy
     */
    public void burn(){
        this.burned = true;
    }

    /**
     * unburns this enemy
     */
    public void removeBurn() {
        this.burned = false;
    }

    /**
     * freezes this enemy
     */
    public void freeze() {
        this.frozen = true;
    }

    /**
     * unfreezes this enemy
     */
    public void removeFreeze() {
        this.frozen = false;
    }

    /**
     * returns the amount of gold gained by defeating this enemy
     * @return goldValue
     */
    public int getGoldValue() {
        return this.goldValue;
    }

    /**
     * returns the hit points of this enemy
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * returns the maximum hit points of this enemy
     * @return maxHitPoints
     */
    public int getMaxHitPoints() { return maxHitPoints; }

    /**
     * returns the attack points of this enemy
     * @return attackPoints
     */
    public int getAttackPoints() { return attackPoints; }

    /**
     * sets the engaged value of this enemy to true
     * @param game
     */
    public void die(Game game) { this.engaged = true; }

    /**
     * checks status impairments of this enemy
     * if the enemy is frozen it has a chance of being unfrozen otherwise it will skip a turn
     * if the enemy is burnt it has a chance of being unburnt otherwise it will take damage
     */
    @Override
    public void checkStatusImpairments() {
        Random r = new Random();
        int chance;
        if (this.isFrozen()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.FREEZE_CHANCE) {
                System.out.println(TextColor.ANSI_BLUE + this.getName() + " is frozen solid." + TextColor.ANSI_RESET);
            } else {
                System.out.println(TextColor.ANSI_BLUE + this.getName() + " is no longer frozen!" + TextColor.ANSI_RESET);
                this.removeFreeze();
            }
        }
        if (this.isBurned()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.BURN_CHANCE) {
                System.out.println(TextColor.ANSI_YELLOW + this.getName() + " is burned and takes " + DefaultStats.BURN_DAMAGE  + " damage." +TextColor.ANSI_RESET);
                this.reduceHitPoints(DefaultStats.BURN_DAMAGE);
            } else {
                System.out.println(TextColor.ANSI_YELLOW + this.getName() + " does no longer burn!" + TextColor.ANSI_RESET);
                this.removeBurn();
            }
        }
    }

    /**
     * deals as much damage to the attacked object as this enemy has attack power
     * @param attacked
     */
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

    /**
     * reduce the hit points of this enemy by the given value
     * @param value
     */
    @Override
    public void reduceHitPoints(int value) {
        this.hitPoints -= value;
    }

    /**
     * increase the hit points of this enemy by the given value
     * @param value
     */
    @Override
    public void increaseHitPoints(int value) {
        this.hitPoints += value;
    }

    /**
     * returns whether the hit points of this enemy are above 0
     * @return true if hit points are 0 or less
     */
    @Override
    public boolean isDead() {
        return this.hitPoints <= 0;
    }

    /**
     * attacks a player
     * @param player
     */
    @Override
    public void interact(Player player) {
        attack(player);
    }

    /**
     * engages a fight with a player
     * @param player
     * @param game
     */
    @Override
    public void engage(Player player, Game game) {
        Combat.engageFight(player, this, game);
    }

    /**
     * returns the type of npc this is
     * @return "Enemy"
     */
    @Override
    public String getType() {
        return TextColor.ANSI_RED + "Enemy" + TextColor.ANSI_RESET;
    }
}
