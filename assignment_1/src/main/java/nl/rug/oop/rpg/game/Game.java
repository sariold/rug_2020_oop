package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.io.JsonReader;
import nl.rug.oop.rpg.npcs.*;
import nl.rug.oop.rpg.npcs.enemies.BlueWizard;
import nl.rug.oop.rpg.npcs.enemies.MiniBoss;
import nl.rug.oop.rpg.npcs.enemies.RedWizard;
import nl.rug.oop.rpg.npcs.healer.Healer;
import nl.rug.oop.rpg.npcs.trader.Trader;
import nl.rug.oop.rpg.objects.*;
import nl.rug.oop.rpg.objects.doors.Door;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Game object which creates the rpg map and all of the npcs and items inside it
 */
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

    /**
     * Creates a new game and loads in the default map, items, and npcs
     * @param name
     */
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
            System.out.println("The default map files could not be found!");

        } catch (ParseException e) {
            System.out.println("The default map files could not be parsed correctly!");

        } catch (IOException e) {
            System.out.println("The default map files are not formatted correctly!");

        }
        this.player = new Player(name, this.totalRooms.get(0), DefaultStats.PLAYER_HIT_POINTS,
                DefaultStats.PLAYER_ATTACK_POINTS, DefaultStats.PLAYER_HIT_POINTS);

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

        Cheats.checkCheatCodes(player, this);
    }

    /**
     * Returns an arraylist of possible moves for the player
     * @return Arraylist of possible moves
     */
    public ArrayList<String> getPossibleMoves() {
        return new ArrayList<String>(possibleMoves);
    }

    /**
     * Returns an arraylist of possible fight moves for the player
     * @return Arraylist of possible fight moves
     */
    public ArrayList<String> getFightMoves() {
        return new ArrayList<String>(fightMoves);
    }

    /**
     * Returns the player of this game object
     * @return The player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns if the player has fire magic
     * @return If the player has fire magic
     */
    public int getFireMagic() {
        return fireMagic;
    }

    /**
     * Returns if the player has ice magic
     * @return If the player has ice magic
     */
    public int getIceMagic() {
        return iceMagic;
    }

    /**
     * Returns the boolean determining if the player is dead or not
     * @return If the player is dead
     */
    public boolean notOver() {
        return !player.isDead();
    }

    /**
     * Returns the minibosses arraylist of the current game
     * @return
     */
    public ArrayList<MiniBoss> getMiniBosses() {
        return this.miniBosses;
    }

    /**
     * Allows the player to use their inventory and the items they have collected, calls helper methods
     */
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

    /**
     * Allows the player to interact with a door, depending on the door, this might mean they will engage in combat
     * or lose damage
     * @param currentMove
     */
    public void interactDoor(int currentMove) {
        ArrayList<Door> doors = this.player.getCurrentRoom().getDoors();
        if (currentMove < doors.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You stayed in the same room.");
                return;
            }
            Door currentDoor = doors.get(currentMove);
            currentDoor.engage(player, this);
        }
    }

    /**
     * Allows the player to interact with items and collect them
     * @param currentMove
     */
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

    /**
     * Allows the player to interact with npcs, might engage them in combat or allow them to trade/get healed
     * @param currentMove
     */
    public void interactNPC(int currentMove) {
        ArrayList<DungeonNpc> npcs = this.player.getCurrentRoom().getNPCs();
        if (currentMove < npcs.size() && currentMove > -2) {
            if (currentMove == -1) {
                System.out.println("You decided to leave them alone.");
                return;
            }
            DungeonNpc currentNPC = npcs.get(currentMove);
            currentNPC.engage(player, this);
        }
    }

    /**
     * The player has won the game, the game ends and the player is congratulated
     */
    public void winGame() {
        GUI.winGameMessage();
        System.exit(0);
    }

    /**
     * The player has died, the game is over and will be exited
     */
    public void gameOver() {
        GUI.gameOverMessage();
        System.exit(0);
    }

    /**
     * Allows the player to trade with an npc if they have enough gold
     * @param player
     * @param trader
     */
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

    /**
     * Allows the player to be healed if they have enough gold
     * @param player
     * @param healer
     */
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

    /**
     * Gives the player ice magic once they have defeated the blue wizard
     */
    public void addIceMagic() {
        for (String move: fightMoves) { if (move.equals("Ice Magic")) return; }
        System.out.println(TextColor.ANSI_YELLOW + "You gained ice magic and can now freeze enemies in combat!"
                + TextColor.ANSI_RESET);
        iceMagic = fightMoves.size();
        fightMoves.add("Ice Magic");
    }

    /**
     * Gives the player fire magic once they have defeated the red wizard
     */
    public void addFireMagic() {
        for (String move: fightMoves) { if (move.equals("Fire Magic")) return; }
        System.out.println(TextColor.ANSI_YELLOW + "You gained fire magic and can now burn enemies in combat!"
                + TextColor.ANSI_RESET);
        fireMagic = fightMoves.size();
        fightMoves.add("Fire Magic");
    }
}
