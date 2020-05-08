package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.game.combat.AttackMethods;
import nl.rug.oop.rpg.game.combat.Combat;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.npcs.DungeonNpc;
import java.io.Serializable;

/**
 * Abstract class Enemy extends abstract class DungeonNpc implements Attackable and can be fought by a player
 */
public abstract class Enemy extends DungeonNpc implements Attackable, Serializable {

    private static final long serialVersionUID = 19L;

    private final int maxHitPoints;
    private final int goldValue;
    protected final int bonusAttackPoints;
    private boolean burned;
    private boolean frozen;

    /**
     * Constructor for an enemy
     * @param description Description
     * @param name Name
     * @param hitPoints Hit points
     * @param attackPoints Attack points
     * @param goldValue Gold value
     */
    public Enemy(String description, String name, int hitPoints, int attackPoints, int goldValue) {
        super(description, name);
        this.hitPoints = hitPoints;
        this.maxHitPoints = hitPoints;
        this.attackPoints = attackPoints;
        this.bonusAttackPoints = 0;
        this.goldValue = goldValue;
        this.engaged = false;
    }


    /**
     * Returns whether the enemy is burned
     * @return Burned
     */
    public boolean isBurned() {
        return burned;
    }

    /**
     * Returns whether the enemy is frozen
     * @return Frozen
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Sets the frozen value of this enemy
     * @param b Boolean
     */
    @Override
    public void setFrozen(boolean b) {
        this.frozen = b;
    }

    /**
     * Sets the burned value of this enemy
     * @param b Boolean
     */
    @Override
    public void setBurned(boolean b) {
        this.burned = b;
    }

    /**
     * Returns the amount of gold gained by defeating this enemy
     * @return goldValue
     */
    public int getGoldValue() {
        return this.goldValue;
    }

    /**
     * Returns the hit points of this enemy
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Returns the maximum hit points of this enemy
     * @return maxHitPoints
     */
    public int getMaxHitPoints() { return maxHitPoints; }

    /**
     * Returns the attack points of this enemy
     * @return attackPoints
     */
    public int getAttackPoints() { return attackPoints; }

    /**
     * Returns bonus attack points
     * @return Bonus Attack Points
     */
    public int getBonusAttackPoints() { return bonusAttackPoints; }

    /**
     * Sets the engaged value of this enemy to true
     * @param game Game
     */
    public void die(Game game) { this.engaged = true; }

    /**
     * Checks status impairments of this enemy
     */
    @Override
    public void checkStatusImpairments() {
       AttackMethods.checkImpairments(this);
    }

    /**
     * Deals as much damage to the attacked object as this enemy has attack power
     * @param attacked Attacked
     */
    @Override
    public void attack(Attackable attacked) { AttackMethods.attackMove(this, attacked); }

    /**
     * Returns whether the hit points of this enemy are above 0
     * @return True if hit points are 0 or less
     */
    @Override
    public boolean isDead() {
        return this.hitPoints <= 0;
    }

    /**
     * Attacks a player
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        attack(player);
    }

    /**
     * Engages a fight with a player
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
        Combat.engageFight(player, this, game);
    }

    /**
     * Returns the type of npc this is
     * @return "Enemy"
     */
    @Override
    public String getType() {
        return TextColor.ANSI_RED + "Enemy" + TextColor.ANSI_RESET;
    }
}