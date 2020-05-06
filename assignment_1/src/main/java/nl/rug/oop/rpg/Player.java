package nl.rug.oop.rpg;

import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.EnchantItem;
import nl.rug.oop.rpg.objects.items.Item;
import nl.rug.oop.rpg.objects.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<Collectable> inventory;


    public Player(String name, Room currentRoom, int hitPoints, int attackPoints, int maxHitPoints) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.attackPoints = attackPoints;
        this.gold = 0;
        this.frozen = false;
        this.burned = false;
        this.inventory = new ArrayList<Collectable>();
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Collectable> getInventory() {
        return new ArrayList<Collectable>(this.inventory);
    }

    public ArrayList<Collectable> getCombatInventory() {
        ArrayList<Collectable> combatItems = new ArrayList<Collectable>();
        for (Collectable c: this.getInventory()) {
            if (c.hasCombatUse()) combatItems.add(c);
        }
        return combatItems;
    }

    public ArrayList<Collectable> getEnchantableInventory() {
        ArrayList<Collectable> enchantables = new ArrayList<Collectable>();
        for (Collectable c: this.getInventory()) {
            if (c instanceof EnchantItem) enchantables.add(c);
        }
        return enchantables;
    }

    public void addCollectable(Collectable c) {
        this.inventory.add(c);
    }

    public int getGold() {
        return this.gold;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }

    public void increaseMaxHitPoints(int value) {
        System.out.println(TextColor.ANSI_YELLOW + "Your health increased by " + value + "." + TextColor.ANSI_RESET);
        this.maxHitPoints += value;
        this.hitPoints += value;
    }

    public void removeUsedItem() {
        for (int i = this.inventory.size() - 1; i > -1; i--) {
            if (((Item)this.inventory.get(i)).getUsed()) this.inventory.remove(i);
        }
    }

    public void increaseAttackPoints(int value) {
        System.out.println(TextColor.ANSI_YELLOW + "Your attack increased by " + value + "." + TextColor.ANSI_RESET);
        this.attackPoints += value;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void increaseGold(int value) {
        this.gold += value;
    }

    public void decreaseGold(int value) {
        this.gold -= value;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public boolean isBurned() {
        return burned;
    }

    public void burn(){
        System.out.println(TextColor.ANSI_RED + "You have been burned!" + TextColor.ANSI_RESET);
        this.burned = true;
    }

    public void removeBurn() {
        this.burned = false;
    }

    public void freeze() {
        System.out.println(TextColor.ANSI_RED + "You have been frozen!" + TextColor.ANSI_RESET);
        this.frozen = true;
    }

    public void removeFreeze() {
        this.frozen = false;
    }

    @Override
    public void checkStatusImpairments() {
        Random r = new Random();
        int chance;
        if (this.isFrozen()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.FREEZE_CHANCE) {
                System.out.println(TextColor.ANSI_RED + "You are frozen solid." + TextColor.ANSI_RESET);
            } else {
                System.out.println(TextColor.ANSI_BLUE + "You are no longer frozen!" + TextColor.ANSI_RESET);
                this.removeFreeze();
            }
        }
        if (this.isBurned()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.BURN_CHANCE) {
                System.out.println(TextColor.ANSI_RED + "You are burned and take " + DefaultStats.BURN_DAMAGE  + " damage." +TextColor.ANSI_RESET);
            } else {
                System.out.println(TextColor.ANSI_BLUE + "You do no longer burn!" + TextColor.ANSI_RESET);
                this.removeBurn();
            }
        }
    }

    @Override
    public void increaseHitPoints(int value) {
        System.out.println(TextColor.ANSI_GREEN + "You have been healed " + value + " hit points!" + TextColor.ANSI_RESET);
        this.hitPoints += value;
        if (this.hitPoints > this.maxHitPoints) this.hitPoints = this.maxHitPoints;
    }

    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 21) {
            System.out.println(TextColor.ANSI_YELLOW + "Critical Hit!" + TextColor.ANSI_RESET);
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
    public boolean isDead() {
        return this.hitPoints <= 0;
    }
}
