package nl.rug.oop.rpg.player;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.game.combat.AttackMethods;
import nl.rug.oop.rpg.game.InventoryMethods;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.interfaces.Collectable;
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
    private int bonusAttackPoints;
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
        this.bonusAttackPoints = 0;
        this.gold = 0;
        this.frozen = false;
        this.burned = false;
        this.inventory = new ArrayList<>();
    }

    /**
     * Sets the max health for a player
     * @param maxHitPoints Max hit points
     */
    public void setMaxHitPoints(int maxHitPoints) { this.maxHitPoints = maxHitPoints; }

    /**
     * Returns the bonus attack points attained by items
     * @return Bonus attack points
     */
    public int getBonusAttackPoints() { return bonusAttackPoints; }

    /**
     * sets the bonus attack points attained by items
     * @param bonusAttackPoints Bonus Attack Points
     */
    public void setBonusAttackPoints(int bonusAttackPoints) { this.bonusAttackPoints = bonusAttackPoints; }

    /**
     * Sets the health for a player
     * @param hitPoints Hit points
     */
    public void setHitPoints(int hitPoints) { this.hitPoints = hitPoints; }

    /**
     * Sets the attack damage a player can give
     * @param attackPoints Attack points
     */
    public void setAttackPoints(int attackPoints) { this.attackPoints = attackPoints; }

    /**
     * Sets the players gold value
     * @param gold Gold
     */
    public void setGold(int gold) { this.gold = gold; }

    /**
     * Returns the inventory arraylist of a player
     * @return The arraylist inventory
     */
    public ArrayList<Collectable> getInventory() { return this.inventory; }

    /**
     * Adds an item to the players inventory arraylist
     * @param c Collectable
     */
    public void addCollectable(Collectable c) { this.inventory.add(c); }

    /**
     * Returns the gold value the player has
     * @return Gold value
     */
    public int getGold() { return this.gold; }

    /**
     * Returns the health points of a player
     * @return The health of a player
     */
    public int getHitPoints() { return hitPoints; }

    /**
     * Returns the attack damage of a player
     * @return The attack damage of aplayer
     */
    public int getAttackPoints() { return this.attackPoints; }

    /**
     * Removes used items from a players inventory
     */
    public void removeUsedItem() { InventoryMethods.removePlayerUsedItem(this); }

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
    public Room getCurrentRoom() { return currentRoom; }

    /**
     * Sets the current room for the player
     * @param currentRoom Current room
     */
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    /**
     * Returns the name of the player
     * @return Name of the player
     */
    public String getName() { return name; }

    /**
     * Sets the name of the player
     * @param name Name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Checks if the player is frozen
     * @return If player is frozen
     */
    public boolean isFrozen() { return frozen; }

    /**
     * Checks if the player is burned
     * @return If player is burned
     */
    public boolean isBurned() { return burned; }

    /**
     * sets the frozen value of the player
     * @param b Boolean
     */
    public void setFrozen(boolean b) {
        if (b) GUIMessages.hasBeenFrozenMessage(this);
        this.frozen = b;
    }

    /**
     * sets the burned value of the player
     * @param b Boolean
     */
    public void setBurned(boolean b) {
        if (b) GUIMessages.hasBeenBurnedMessage(this);
        this.burned = b;
    }

    /**
     * Checks if the player is impaired by fire or ice magic, the player takes damage accordingly
     */
    @Override
    public void checkStatusImpairments() { AttackMethods.checkImpairments(this); }

    /**
     * Forces the player to attack an enemy and it might be a critical hit
     * @param attacked Attacked
     */
    @Override
    public void attack(Attackable attacked) { AttackMethods.attackMove(this, attacked); }

    /**
     * Checks if the player health is 0 or less
     * @return Boolean if player is dead
     */
    @Override
    public boolean isDead() { return this.hitPoints <= 0; }
}