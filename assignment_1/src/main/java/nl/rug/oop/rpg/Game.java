package nl.rug.oop.rpg;

import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.*;
import nl.rug.oop.rpg.objects.*;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

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
        this.possibleMoves.add("QuickSave");
        this.possibleMoves.add("QuickLoad");
        this.possibleMoves.add("Save");
        this.possibleMoves.add("Load");

        this.fightMoves.add("Run");
        this.fightMoves.add("Attack");
        this.fightMoves.add("Items");

        this.totalRooms.get(0).addNPC(new Enchanter("Henrietta"));
        this.totalRooms.get(0).addItem(new HealingPotion());

        if(player.getName().equals("John Wick")) {
            System.out.println(TextColor.ANSI_YELLOW + "Check your stats Mr. Wick." + TextColor.ANSI_RESET);
            player.increaseGold(100 - player.getGold());
            player.increaseMaxHitPoints(100 - player.getMaxHitPoints());
            player.increaseAttackPoints(100 - player.getAttackPoints());
            addIceMagic();
            addFireMagic();
        }
    }

    public ArrayList<String> getPossibleMoves() {
        return new ArrayList<String>(possibleMoves);
    }

    public ArrayList<String> getFightMoves() {
        return new ArrayList<String>(fightMoves);
    }

    public Player getPlayer() {
        return player;
    }

    public int getFireMagic() {
        return fireMagic;
    }

    public int getIceMagic() {
        return iceMagic;
    }

    public boolean notOver() {
        return !player.isDead();
    }

    public void useInventory() {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.displayInventory(player);
        if (player.getInventory().size() == 0) return;
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUI.invalidInputMessage();
            return;
        }
        if (move == -1) return;
        if (move < player.getInventory().size() && move > -1) {
            if (!player.getInventory().get(move).hasNonCombatUse()) {
                GUI.onlyCombatItemMessage();
                return;
            }
            player.getInventory().get(move).use(player);
        } else GUI.invalidItemMessage();
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
                    if(miniBosses.get(0).isDead()) {
                        ((MiniBossDoor) doors.get(currentMove)).defeated();
                        doors.get(currentMove).setDescription("This was once a mini boss door, congrats on surviving the battle");
                    }
                } else {
                    miniBosses.get(1).setDoor((MiniBossDoor) doors.get(currentMove));
                    player.getCurrentRoom().addNPC(miniBosses.get(1));
                    Combat.engageFight(player, (Enemy) player.getCurrentRoom().getNPCs().get(0), this);
                    if(miniBosses.get(1).isDead()) {
                        ((MiniBossDoor) doors.get(currentMove)).defeated();
                        doors.get(currentMove).setDescription("This was once a mini boss door, congrats on surviving the battle");
                    }
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

    public void interactNPC(int currentMove) {
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        if (currentMove < npcs.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You decided to leave them alone.");
                return;
            }
            DungeonNpc currentNPC= npcs.get(currentMove);
            currentNPC.engage(player, this);
        }
    }

    public void winGame() {
        GUI.winGameMessage();
        System.exit(0);
    }

    public void gameOver() {
        GUI.gameOverMessage();
        System.exit(0);
    }

    public void tradeWith(Player player, Trader trader) {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.tradeDialog(trader);
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUI.invalidInputMessage();
            return;
        }
        if (move == 0) return;
        else if (move == 1){
            if (player.getGold() < trader.getPrice()) {
                System.out.println("You do not have enough gold!");
                return;
            }
            trader.interact(player);
            player.getCurrentRoom().removeDeadNPC();
        } else GUI.invalidInputMessage();
    }

    public void healPlayer(Player player, Healer healer) {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.healDialog(healer);
        try {
            move = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUI.invalidInputMessage();
            return;
        }
        if (move == 0) return;
        else if (move == 1){
            healer.interact(player);
            System.out.println("You are at " + player.getHitPoints() + " health.");
            player.getCurrentRoom().removeDeadNPC();
        } else GUI.invalidInputMessage();
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
