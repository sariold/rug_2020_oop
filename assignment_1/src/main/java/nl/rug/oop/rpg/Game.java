package nl.rug.oop.rpg;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private Player player;
    private ArrayList<Room> totalRooms;
    private ArrayList<Door> totalDoors;
    private ArrayList<String> possibleMoves;
    private ArrayList<String> fightMoves;


    public Game(String name) {
        this.totalRooms = new ArrayList<Room>();
        this.totalDoors = new ArrayList<Door>();
        this.possibleMoves = new ArrayList<String>();
        this.fightMoves = new ArrayList<String>();
        try {
            JsonReader.parseRoomJSON(totalRooms);
            JsonReader.parseDoorJSON(totalRooms, totalDoors);
            JsonReader.parseConnectionJSON(totalRooms, totalDoors);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.player = new Player(name, this.totalRooms.get(0), DefaultStats.PLAYER_HIT_POINTS, DefaultStats.PLAYER_ATTACK_POINTS, DefaultStats.PLAYER_HIT_POINTS);
        this.possibleMoves.add("Look around");
        this.possibleMoves.add("Look for a way out");
        this.possibleMoves.add("Look for company");
        this.possibleMoves.add("Look at your stats");

        this.fightMoves.add("Run");
        this.fightMoves.add("Attack");

    }

    public void printOptions() {
        System.out.println("What do you want to do?");
        for (int i = 0; i < this.possibleMoves.size(); i++) {
            System.out.println("\t(" + i + ") " + this.possibleMoves.get(i));
        }
    }

    public void inspectRoom() {
        System.out.print("You see: ");
        this.player.getCurrentRoom().inspect();
    }

    public void inspectDoors() {
        System.out.println("You look for doors.");
        System.out.println("You see:");
        ArrayList<Door> doors = this.player.getCurrentRoom().getDoors();
        for (int i = 0; i < doors.size(); i++) {
            System.out.print("\t(" + i + ") ");
            doors.get(i).inspect();
        }
        System.out.println("Which door will you take? (-1 to stay)");
    }

    public void interactDoor(int currentMove) {
        ArrayList<Door> doors = this.player.getCurrentRoom().getDoors();
        if (currentMove < doors.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You stayed in the same room.");
                return;
            }
            doors.get(currentMove).interact(player);
            player.getCurrentRoom().inspect();
        }
    }

    public void inspectNPCs() {
        System.out.println("You look if there's somebody.");
        System.out.println("You see:");
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        for (int i = 0; i < npcs.size(); i++) {
            System.out.print("\t(" + i + ") " + "[" + npcs.get(i).getType() + "]" + "(" + npcs.get(i).getSpecies() + ")" + npcs.get(i).toString() + ": ");
            npcs.get(i).inspect();
        }
        System.out.println("Who will you approach? (-1 to stay by yourself)");
    }

    public void interactNPC(int currentMove) {
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        if (currentMove < npcs.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You decided to leave them alone.");
                return;
            }
            DungeonNpc currentNPC= npcs.get(currentMove);
            if (currentNPC instanceof Enemy) {
                engageFight(player, (Enemy) currentNPC);
            } else if (currentNPC instanceof Healer) {
                healPlayer(player, (Healer) currentNPC);
            }

        }
    }

    private void gameOver() {
        System.out.println("GAME OVER!");
        System.exit(0);
    }

    private void healPlayer(Player player, Healer healer) {
        Scanner scanner = new Scanner(System.in);
        int move;
        System.out.println(healer.getName() + ":I can only heal you once, and then I will leave!\n Are you sure you want me to heal you?");
        System.out.println("\t(0) Heal me!\n\t(1) No better do it later!");
        move = scanner.nextInt();
        if (move == 0) return;
        else {
            healer.heal(player);
            System.out.println("You have been healed. You are at " + player.getHitPoints() + " health.");
            player.getCurrentRoom().removeDeadNPC();
        }
    }

    private void engageFight(Player player, Enemy enemy) {
        int move;
        int damageToEnemy = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("You engaged a fight with " + enemy.getName());
        while (enemy.getHitPoints() > 0 && player.getHitPoints() > 0) {
            System.out.println("What will you do?");
            for(int i = 0; i < this.fightMoves.size(); i++) {
                System.out.println("\t (" + i + ") " + this.fightMoves.get(i));
            }
            move = scanner.nextInt();
            if (move == 0){
                enemy.increaseHitPoints(damageToEnemy);
                return;
            } else {
                System.out.println("You attack " + enemy.getName());
                player.attack(enemy);
                damageToEnemy += this.player.getAttackPoints();
                if (enemy.isDead()) {
                    System.out.println("You have slain " + (enemy.getName() + "!"));
                    player.getCurrentRoom().removeDeadNPC();
                    return;
                }
                enemy.interact(player);
                System.out.println("You are attacked by " + enemy.getName() + " you are at " + player.getHitPoints() + " health!");
                if (player.isDead()) {
                    System.out.println("You have been slain by " + enemy.getName() + "!");
                    gameOver();
                }
            }

        }
    }
}
