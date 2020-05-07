package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.Item;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates a player which can attack npcs and interact with items/npcs
 */
public class Player implements Attackable, Serializable {

    private static final long serialVersionUID = 2L;

    private String name;
    private Room currentRoom;
    private int maxHitPoints;
    private int hitPoints;
    private int attackPoints;
    private int gold;
    private boolean frozen;
    private boolean burned;
    private final ArrayList<Collectable> inventory;


    /**
     * Player constructor, custom name and sets their stats depending on the config initialization
     * @param name Name
     * @param currentRoom Current room
     * @param hitPoints Hit points
     * @param attackPoints Attack points
     * @param maxHitPoints Max hit points
     */
    public Player(String name, Room currentRoom, int hitPoints, int attackPoints, int maxHitPoints) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.attackPoints = attackPoints;
        this.gold = 0;
        this.frozen = false;
        this.burned = false;
        this.inventory = new ArrayList<>();
    }

    /**
     * Sets the max health for a player
     * @param maxHitPoints Max hit points
     */
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    /**
     * Sets the health for a player
     * @param hitPoints Hit points
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Sets the attack damage a player can give
     * @param attackPoints Attack points
     */
    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    /**
     * Sets the players gold value
     * @param gold Gold
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * Returns the inventory arraylist of a player
     * @return The arraylist inventory
     */
    public ArrayList<Collectable> getInventory() {
        return new ArrayList<>(this.inventory);
    }

    /**
     * Adds an item to the players inventory arraylist
     * @param c Collectable
     */
    public void addCollectable(Collectable c) {
        this.inventory.add(c);
    }

    /**
     * Returns the gold value the player has
     * @return Gold value
     */
    public int getGold() {
        return this.gold;
    }

    /**
     * Returns the health points of a player
     * @return The health of a player
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Returns the attack damage of a player
     * @return The attack damage of aplayer
     */
    public int getAttackPoints() {
        return this.attackPoints;
    }

    /**
     * Setter method to increase the max health points a player has
     * @param value Value
     */
    public void increaseMaxHitPoints(int value) {
        System.out.println(TextColor.ANSI_YELLOW + "Your health increased by " + value + "." + TextColor.ANSI_RESET);
        this.maxHitPoints += value;
        this.hitPoints += value;
    }

    /**
     * Removes used items from a players inventory
     */
    public void removeUsedItem() {
        for (int i = this.inventory.size() - 1; i > -1; i--) {
            if (((Item)this.inventory.get(i)).getUsed()) this.inventory.remove(i);
        }
    }

    /**
     * Increases the attack dmamage of a player
     * @param value Value
     */
    public void increaseAttackPoints(int value) {
        System.out.println(TextColor.ANSI_YELLOW + "Your attack increased by " + value + "." + TextColor.ANSI_RESET);
        this.attackPoints += value;
    }

    /**
     * Gets the max health the player can have
     * @return Max hit points of a player
     */
    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    /**
     * Gets the current room the player is in
     * @return Player's current room
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room for the player
     * @param currentRoom Current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the name of the player
     * @return Name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Increases the player's gold value
     * @param value Value
     */
    public void increaseGold(int value) {
        this.gold += value;
    }

    /**
     * Decreases the player's gold value
     * @param value Value
     */
    public void decreaseGold(int value) {
        this.gold -= value;
    }

    /**
     * Checks if the player is frozen
     * @return If player is frozen
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Checks if the player is burned
     * @return If player is burned
     */
    public boolean isBurned() {
        return burned;
    }

    /**
     * sets the frozen value of the player
     * @param b Boolean
     */
    public void setFrozen(boolean b) {
        if (b) GUI.frozenMessage();
        this.frozen = b;
    }

    /**
     * sets the burned value of the player
     * @param b Boolean
     */
    public void setBurned(boolean b) {
        if (b) GUI.burnedMessage();
        this.burned = b;
    }

    /**
     * Checks if the player is impaired by fire or ice magic, the player takes damage accordingly
     */
    @Override
    public void checkStatusImpairments() {
        AttackMethods.checkImpairments(this);
    }

    /**
     * Increases the health of a player
     * @param value Value
     */
    @Override
    public void increaseHitPoints(int value) {
        System.out.println(TextColor.ANSI_GREEN + "You have been healed " + value + " hit points!" + TextColor.ANSI_RESET);
        this.hitPoints += value;
        if (this.hitPoints > this.maxHitPoints) this.hitPoints = this.maxHitPoints;
    }

    /**
     * Forces the player to attack an enemy and it might be a critical hit
     * @param attacked Attacked
     */
    @Override
    public void attack(Attackable attacked) {
       AttackMethods.playerAttacker(attacked, this);
    }

    /**
     * Reduce the health of a player
     * @param value Value
     */
    @Override
    public void reduceHitPoints(int value) {
        this.hitPoints -= value;
    }

    /**
     * Checks if the player health is 0 or less
     * @return Boolean if player is dead
     */
    @Override
    public boolean isDead() {
        return this.hitPoints <= 0;
    }
}