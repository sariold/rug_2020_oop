package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.gui.GUI;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.extra.Cheats;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.npcs.enemies.MiniBoss;
import nl.rug.oop.rpg.objects.*;
import nl.rug.oop.rpg.objects.doors.Door;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Game object which creates the rpg map and all of the npcs and items inside it
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Player player;
    private final ArrayList<String> possibleMoves;
    private final ArrayList<String> fightMoves;
    private final ArrayList<MiniBoss> miniBosses;
    private int iceMagic;
    private int fireMagic;


    /**
     * Creates a new game and loads in the default map, items, and npcs
     * @param name Name
     */
    public Game(String name) {
        ArrayList<Room> totalRooms = new ArrayList<>();
        ArrayList<Door> totalDoors = new ArrayList<>();
        this.possibleMoves = new ArrayList<>();
        this.fightMoves = new ArrayList<>();
        this.miniBosses = new ArrayList<>();

        GameOptions.createGame(totalRooms, totalDoors, possibleMoves, fightMoves, miniBosses);
        player = new Player(name, totalRooms.get(0), DefaultStats.PLAYER_HIT_POINTS,
                DefaultStats.PLAYER_ATTACK_POINTS, DefaultStats.PLAYER_HIT_POINTS);
        Cheats.checkCheatCodes(player, this);

    }

    /**
     * Returns an arraylist of possible moves for the player
     * @return Arraylist of possible moves
     */
    public ArrayList<String> getPossibleMoves() {
        return new ArrayList<>(possibleMoves);
    }

    /**
     * Returns an arraylist of possible fight moves for the player
     * @return Arraylist of possible fight moves
     */
    public ArrayList<String> getFightMoves() {
        return new ArrayList<>(fightMoves);
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
     * Returns the minibosses arraylist of the current game
     * @return Mini boss array
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
            GUIMessages.invalidInputMessage();
            return;
        }
        if (move == -1) return;
        if (move < player.getInventory().size() && move > -1) {
            if (!player.getInventory().get(move).hasNonCombatUse()) {
                if(player.getInventory().get(move).toString().equals("Monster Key")) {
                    GUIMessages.onlyMonsterDoorUsage();
                    return;
                }
                GUIMessages.onlyCombatItemMessage();
                return;
            }
            player.getInventory().get(move).use(player);
        } else GUIMessages.invalidItemMessage();
    }

    /**
     * The player has won the game, the game ends and the player is congratulated
     */
    public void winGame() {
        GUIMessages.winGameMessage();
        System.exit(0);
    }

    /**
     * The player has died, the game is over and will be exited
     */
    public void gameOver() {
        GUIMessages.gameOverMessage();
        System.exit(0);
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
