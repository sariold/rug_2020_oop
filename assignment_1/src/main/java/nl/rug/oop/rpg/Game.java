package nl.rug.oop.rpg;

import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.*;
import nl.rug.oop.rpg.objects.*;
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
    private ArrayList<MiniBoss> miniBosses;
    private int iceMagic;
    private int fireMagic;

    public Game(String name) {
        this.totalRooms = new ArrayList<Room>();
        this.totalDoors = new ArrayList<Door>();
        this.possibleMoves = new ArrayList<String>();
        this.fightMoves = new ArrayList<String>();
        this.miniBosses = new ArrayList<MiniBoss>();

        BlueWizard blueWizard = new BlueWizard("Ice Poseidon");
        miniBosses.add(blueWizard);

        RedWizard redWizard = new RedWizard("Hades");
        miniBosses.add(redWizard);

        JsonReader jsonReader = new JsonReader();
        try {
            jsonReader.parseRoomJSON(totalRooms);
            jsonReader.parseDoorJSON(totalRooms, totalDoors);
            jsonReader.parseConnectionJSON(totalRooms, totalDoors);
            jsonReader.parseNPCJSON(totalRooms, "enemies.json");
            jsonReader.parseNPCJSON(totalRooms, "healers.json");
            jsonReader.parseNPCJSON(totalRooms, "traders.json");
            jsonReader.parseItemJSON(totalRooms);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.player = new Player(name, this.totalRooms.get(0), DefaultStats.PLAYER_HIT_POINTS, DefaultStats.PLAYER_ATTACK_POINTS, DefaultStats.PLAYER_HIT_POINTS);

        this.possibleMoves.add("Look around");
        this.possibleMoves.add("Look for a way out");
        this.possibleMoves.add("Look for company");
        this.possibleMoves.add("Look for items");
        this.possibleMoves.add("Look in your inventory");
        this.possibleMoves.add("Look at your stats");

        this.fightMoves.add("Run");
        this.fightMoves.add("Attack");

        if(player.getName().equals("John Wick")) {
            System.out.println(TextColor.ANSI_YELLOW + "Check your stats Mr. Wick." + TextColor.ANSI_RESET);
            player.increaseGold(100 - player.getGold());
            player.increaseMaxHitPoints(100 - player.getMaxHitPoints());
            player.increaseAttackPoints(100 - player.getAttackPoints());
            addIceMagic();
            addFireMagic();
        }
    }

    public ArrayList<String> getFightMoves() {
        return new ArrayList<String>(fightMoves);
    }

    public Player getPlayer() {
        return player;
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
        System.out.println("Your inventory contains:");
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
            if(!(doors.get(currentMove) instanceof FinalBossDoor)) {
                doors.get(currentMove).interact(player);
                if (player.isDead()) {
                    System.out.println(TextColor.ANSI_RED + "You have been slain by the mighty trap door!" + TextColor.ANSI_RESET);
                    gameOver();
                }
                player.getCurrentRoom().inspect();
            }
            if(doors.get(currentMove) instanceof MiniBossDoor) {
                if (((MiniBossDoor) doors.get(currentMove)).isDefeated()) return;
                String type = ((MiniBossDoor) doors.get(currentMove)).getWizardColor();
                if(type == "Blue") {
                    miniBosses.get(0).setDoor((MiniBossDoor) doors.get(currentMove));
                    player.getCurrentRoom().addNPC(miniBosses.get(0));
                    Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), this);
                    if(miniBosses.get(0).isDead()) ((MiniBossDoor) doors.get(currentMove)).defeated();
                } else {
                    miniBosses.get(1).setDoor((MiniBossDoor) doors.get(currentMove));
                    player.getCurrentRoom().addNPC(miniBosses.get(1));
                    Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), this);
                    if(miniBosses.get(1).isDead()) ((MiniBossDoor) doors.get(currentMove)).defeated();
                }
            } else if(doors.get(currentMove) instanceof FinalBossDoor) {
                if(miniBosses.get(0).isDead() && miniBosses.get(1).isDead()) {
                    doors.get(currentMove).interact(player);
                    player.getCurrentRoom().inspect();
                    Dragon dragon = new Dragon("Draco");
                    player.getCurrentRoom().addNPC(dragon);
                    Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), this);
                }
                doors.get(currentMove).inspect();
            }
        }
    }

    public boolean inspectItems() {
        boolean itemExists = false;
        System.out.println("You look for items.");
        System.out.println("You see:");
        ArrayList<Collectable> items = this.player.getCurrentRoom().getItems();
        if(items.size() > 0) {
            itemExists = true;
            for (int i = 0; i < items.size(); i++) {
                System.out.print("\t(" + i + ") ");
                System.out.println(items.get(i).toString());
            }
            System.out.println("Which item will you take? (-1 to not collect any items)");
        } else System.out.println("Nothing in sight");
        return itemExists;
    }

    public void interactItem(int currentMove) {
        ArrayList<Collectable> items = this.player.getCurrentRoom().getItems();
        if (currentMove < items.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You did not collect any items.");
                return;
            }
            items.get(currentMove).collect(player);
            player.getCurrentRoom().removeItem();
            System.out.println("You collected " + items.get(currentMove).toString());
        }
    }

    public boolean inspectNPCs() {
        boolean npcExists = false;
        String color = TextColor.ANSI_RESET;
        System.out.println("You look if there's somebody.");
        System.out.println("You see:");
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        if(npcs.size() > 0) {
            for (int i = 0; i < npcs.size(); i++) {
                if(npcs.get(i) instanceof Enemy) color = TextColor.ANSI_RED;
                if(npcs.get(i) instanceof Healer) color = TextColor.ANSI_GREEN;
                if(npcs.get(i) instanceof Trader) color = TextColor.ANSI_BLUE;
                System.out.print("\t(" + i + ") "+ "[" + color + npcs.get(i).getType() + TextColor.ANSI_RESET + "]" + "(" + npcs.get(i).getSpecies() + ") " + npcs.get(i).toString() + ": ");
                npcs.get(i).inspect();
            }
            npcExists = true;
            System.out.println("Who will you approach? (-1 to stay by yourself)");
        } else System.out.println("\tNobody.");
        return npcExists;
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
                Combat.engageFight(player, (Enemy) currentNPC, this);
            } else if (currentNPC instanceof Healer) {
                healPlayer(player, (Healer) currentNPC);
            } else if (currentNPC instanceof Trader) {
                tradeWith(player, (Trader) currentNPC);
            }
        }
    }

    public int getFireMagic() {
        return fireMagic;
    }

    public int getIceMagic() {
        return iceMagic;
    }

    public void winGame() {
        System.out.println(TextColor.ANSI_BLACK + "Congratulations you have won the game! You are a real dungeon master!" + TextColor.ANSI_RESET);
        System.exit(0);
    }

    public void gameOver() {
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

    public void addIceMagic() {
        for (String move: fightMoves) { if (move.equals("Ice Magic")) return; }
        System.out.println(TextColor.ANSI_YELLOW + "You gained ice magic and can now freeze enemies in combat!" + TextColor.ANSI_RESET);
        iceMagic = fightMoves.size();
        fightMoves.add("Ice Magic");
    }

    public void addFireMagic() {
        for (String move: fightMoves) { if (move.equals("Fire Magic")) return; }
        System.out.println(TextColor.ANSI_YELLOW + "You gained fire magic and can now burn enemies in combat!" + TextColor.ANSI_RESET);
        fireMagic = fightMoves.size();
        fightMoves.add("Fire Magic");
    }
}
