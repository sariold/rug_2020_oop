package nl.rug.oop.rpg;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.player = new Player(name, this.totalRooms.get(0), DefaultStats.PLAYER_HIT_POINTS, DefaultStats.PLAYER_ATTACK_POINTS, DefaultStats.PLAYER_HIT_POINTS);

        Priest priest = new Priest("Priest");
        HighPriest highPriest = new HighPriest("High");
        Knight knight1 = new Knight("Knight1");
        Knight knight2 = new Knight("§§");
        Knight knight3 = new Knight("RRR");
        Rat rat = new Rat("Rat");
        Spider spider = new Spider("spider");
        Snake snake = new Snake("Snake");
        Orc orc = new Orc("orc");
        Dragon dragon = new Dragon("JAJA");
        WeaponSmith weaponSmith = new WeaponSmith("Weapons");
        ArmorSmith armorSmith = new ArmorSmith("Armor");

        this.totalRooms.get(0).addNPC(priest);
        this.totalRooms.get(0).addNPC(highPriest);
        this.totalRooms.get(0).addNPC(knight1);
        this.totalRooms.get(0).addNPC(knight2);
        this.totalRooms.get(0).addNPC(knight3);
        this.totalRooms.get(0).addNPC(rat);
        this.totalRooms.get(0).addNPC(spider);
        this.totalRooms.get(0).addNPC(orc);
        this.totalRooms.get(0).addNPC(dragon);
        this.totalRooms.get(0).addNPC(snake);
        this.totalRooms.get(0).addNPC(weaponSmith);
        this.totalRooms.get(0).addNPC(armorSmith);

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

    public void displayStats() {
        System.out.println("Your Character: " + player.getName());
        System.out.println("\tHealth: "  + player.getHitPoints() + "/" + player.getMaxHitPoints());
        System.out.println("\tAttack: " + player.getAttackPoints());
        System.out.println("\tGold: " + player.getGold());
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
        String color = TextColor.ANSI_RESET;
        System.out.println("You look if there's somebody.");
        System.out.println("You see:");
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        for (int i = 0; i < npcs.size(); i++) {
            if(npcs.get(i) instanceof Enemy) color = TextColor.ANSI_RED;
            if(npcs.get(i) instanceof Healer) color = TextColor.ANSI_GREEN;
            if(npcs.get(i) instanceof Trader) color = TextColor.ANSI_BLUE;
            System.out.print("\t(" + i + ") "+ "[" + color + npcs.get(i).getType() + TextColor.ANSI_RESET + "]" + "(" + npcs.get(i).getSpecies() + ") " + npcs.get(i).toString() + ": ");
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
            } else if (currentNPC instanceof Trader) {
                tradeWith(player, (Trader) currentNPC);
            }

        }
    }

    private void gameOver() {
        System.out.println("GAME OVER!");
        System.exit(0);
    }

    private void tradeWith(Player player, Trader trader) {
        Scanner scanner = new Scanner(System.in);
        int move;
        System.out.println(trader.getName() + ": " + trader.tradeDialog() + "\n Are you interested?");
        System.out.println("\t(0) I think that is too expensive!\n\t(1) Let's trade!");
        move = scanner.nextInt();
        if (move == 0) return;
        else {
            if (player.getGold() < trader.getPrice()) {
                System.out.println("You do not have enough gold!");
                return;
            }
            trader.interact(player);
            System.out.println("You traded with " + trader.getName() + ". You have " + player.getGold() + " gold.");
            player.getCurrentRoom().removeDeadNPC();
        }
    }

    private void healPlayer(Player player, Healer healer) {
        Scanner scanner = new Scanner(System.in);
        int move;
        System.out.println(healer.getName() + ":I can only heal you once, and then I will leave!\n Are you sure you want me to heal you?");
        System.out.println("\t(0) No better do it later!\n\t(1) Heal me!");
        move = scanner.nextInt();
        if (move == 0) return;
        else {
            healer.interact(player);
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
                    System.out.println("You have slain " + enemy.getName() + "!\nYou earned " + enemy.getGoldValue() + " gold.");
                    player.getCurrentRoom().removeDeadNPC();
                    player.increaseGold(enemy.getGoldValue());
                    player.increaseHitPoints(enemy.getAttackPoints());
                    return;
                }
                System.out.println("You are attacked by " + enemy.getName());
                enemy.interact(player);
                System.out.println("You are at " +  player.getHitPoints() + " health!");
                if (player.isDead()) {
                    System.out.println("You have been slain by " + enemy.getName() + "!");
                    gameOver();
                }
            }

        }
    }
}
