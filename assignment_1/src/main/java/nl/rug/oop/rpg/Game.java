package nl.rug.oop.rpg;

import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
        Gambler gambler = new Gambler("gambler");

        this.totalRooms.get(0).addNPC(gambler);
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
        this.possibleMoves.add("Look in your inventory");

        this.fightMoves.add("Run");
        this.fightMoves.add("Attack");

        this.player.addCollectable(new HealingPotion());
        this.player.addCollectable(new GoldNugget());
    }

    public boolean notOver() {
        return !player.isDead();
    }

    public void printOptions() {
        System.out.println("What do you want to do?");
        for (int i = 0; i < this.possibleMoves.size(); i++) {
            System.out.println("\t(" + i + ") " + this.possibleMoves.get(i));
        }
    }

    public void useInventory() {
        Scanner scanner = new Scanner(System.in);
        int move;
        if (player.getInventory().size() == 0) {
            System.out.println("You currently have no items.");
            return;
        }
        for (int i = 0; i < player.getInventory().size(); i++) {
            System.out.println("\t(" + i + ") " + this.player.getInventory().get(i).toString());
        }
        System.out.println("What item will you use (-1 to not use an item)");
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That is not a valid input!");
            return;
        }
        if (move == -1) return;
        if (move < player.getInventory().size() && move > -1) {
            player.getInventory().get(move).use(player);
        } else System.out.println("That is not a valid item!");
    }

    public void displayStats() {
        System.out.println("Your Character: " + player.getName());
        System.out.println(TextColor.ANSI_GREEN + "\tHealth: " + TextColor.ANSI_RESET + player.getHitPoints() + "/" + player.getMaxHitPoints());
        System.out.println(TextColor.ANSI_RED + "\tAttack: " + TextColor.ANSI_RESET + player.getAttackPoints());
        System.out.println(TextColor.ANSI_YELLOW + "\tGold: " + TextColor.ANSI_RESET + player.getGold());
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

    private void winGame() {
        System.out.println(TextColor.ANSI_BLACK + "Congratulations you have won the game! You are a real dungeon master!" + TextColor.ANSI_RESET);
        System.exit(0);
    }

    private void gameOver() {
        System.out.println(TextColor.ANSI_BLACK + "GAME OVER!" + TextColor.ANSI_RESET);
        System.exit(0);
    }

    private void tradeWith(Player player, Trader trader) {
        Scanner scanner = new Scanner(System.in);
        int move;
        System.out.println(trader.getName() + ": " + TextColor.ANSI_PURPLE + trader.tradeDialog() + "\n Are you interested?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) I think that is too expensive!\n\t(1) Let's trade!");
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That is not a valid input!");
            return;
        }
        if (move == 0) return;
        else if (move == 1){
            if (player.getGold() < trader.getPrice()) {
                System.out.println("You do not have enough gold!");
                return;
            }
            trader.interact(player);
            System.out.println("You traded with " + trader.getName() + ". You have " + player.getGold() + " gold.");
            player.getCurrentRoom().removeDeadNPC();
        } else System.out.println("That is not a valid input!");
    }

    private void healPlayer(Player player, Healer healer) {
        Scanner scanner = new Scanner(System.in);
        int move;
        System.out.println(healer.getName() + ":" + TextColor.ANSI_PURPLE + "I can only heal you once, and then I will leave!\n Are you sure you want me to heal you?" + TextColor.ANSI_RESET);
        System.out.println("\t(0) No better do it later!\n\t(1) Heal me!");
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("That is not a valid input!");
            return;
        }
        if (move == 0) return;
        else if (move == 1){
            healer.interact(player);
            System.out.println("You are at " + player.getHitPoints() + " health.");
            player.getCurrentRoom().removeDeadNPC();
        } else System.out.println("That is not a valid input!");
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
            try {
                move = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid input!");
                return;
            }
            if (move == 0){
                System.out.println(TextColor.ANSI_YELLOW + "You ran from the fight. " + TextColor.ANSI_RED + enemy.getName() + TextColor.ANSI_YELLOW + " recovered to full health!" + TextColor.ANSI_RESET);
                enemy.increaseHitPoints(damageToEnemy);
                return;
            } else if (move == 1){
                System.out.println(TextColor.ANSI_YELLOW + "You attack " + enemy.getName() + TextColor.ANSI_RESET);
                player.attack(enemy);
                damageToEnemy += this.player.getAttackPoints();
                if (enemy.isDead()) {
                    System.out.println(TextColor.ANSI_YELLOW + "You have slain " + enemy.getName() + "!\nYou earned " + enemy.getGoldValue() + " gold." + TextColor.ANSI_RESET);
                    player.getCurrentRoom().removeDeadNPC();
                    player.increaseGold(enemy.getGoldValue());
                    player.increaseHitPoints(enemy.getAttackPoints());
                    return;
                }
                System.out.println(TextColor.ANSI_RED + "You are attacked by " + enemy.getName() + TextColor.ANSI_RESET);
                enemy.interact(player);
                System.out.println(TextColor.ANSI_RED + "You are at " +  player.getHitPoints() + " health!" + TextColor.ANSI_RESET);
                if (player.isDead()) {
                    System.out.println(TextColor.ANSI_RED + "You have been slain by " + enemy.getName() + "!" + TextColor.ANSI_RESET);
                    gameOver();
                }
            } else {
                System.out.println("That is not a valid input!");
            }

        }
    }
}
