package nl.rug.oop.rpg;

import java.util.ArrayList;
import java.util.Random;

public class Player implements Attackable{

    private String name;
    private Room currentRoom;
    private int maxHitPoints;
    private int hitPoints;
    private int attackPoints;
    private int gold;
    private ArrayList<Collectable> inventory;


    public Player(String name, Room currentRoom, int hitPoints, int attackPoints, int maxHitPoints, ArrayList<Collectable> inventory) {
        this.name = name;
        this.currentRoom = currentRoom;
        this.hitPoints = hitPoints;
        this.maxHitPoints = maxHitPoints;
        this.attackPoints = attackPoints;
        this.gold = 0;
        this.inventory = new ArrayList<Collectable>();
    }

//    public Player(String name, Room currentRoom) {
//        this(name, currentRoom, 10, 0, 10, inventory);
//    }
//
//    public Player(String name) {
//        this(name, null,10, 0,10);
//    }

    public String[] getPossibleMoves(){
        String[] options = new String[3];

        return options;
    }

    public ArrayList<Collectable> getInventory() {
        return new ArrayList<Collectable>(this.inventory);
    }

    public void addCollectable(Collectable c) {
        this.getInventory().add(c);
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
        this.maxHitPoints += value;
        this.hitPoints += value;
    }

    public void increaseAttackPoints(int value) { this.attackPoints += value; };

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

    @Override
    public void increaseHitPoints(int value) {
        this.hitPoints += value;
        if (this.hitPoints > this.maxHitPoints) this.hitPoints = this.maxHitPoints;
    }

    @Override
    public void attack(Attackable attacked) {
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 21) {
            System.out.println("Critical Hit!");
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
