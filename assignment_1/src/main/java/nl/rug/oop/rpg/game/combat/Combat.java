package nl.rug.oop.rpg.game.combat;

import nl.rug.oop.rpg.GUI.GUI;
import nl.rug.oop.rpg.GUI.GUIMessages;
import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.InventoryMethods;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.player.PlayerStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.npcs.enemies.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Combat class to allow the player to engage in fights with the npcs
 */
public class Combat {

    /**
     * Forces the player to engage into combat with a specific enemy
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    public static void engageFight(Player player, Enemy enemy, Game game) {
        int move;
        Scanner scanner = new Scanner(System.in);
        System.out.println("You engaged a fight with " + enemy.getName());
        while (enemy.getHitPoints() > 0 && player.getHitPoints() > 0) {
            GUI.displayCombatInterface(player, enemy, game);
            try {
                move = scanner.nextInt();
            } catch (InputMismatchException e) {
                GUIMessages.invalidInputMessage();
                continue;
            }
            if (move > game.getFightMoves().size()) {
                GUIMessages.invalidInputMessage();
                continue;
            }
            if (move == 0){
                if (run(enemy)) {
                    return;
                }
                continue;
            }
            if (move == 2) {
                if (!useCombatItem(player)) {
                    continue;
                }
            }
            if (!playerStatusImpairments(player, enemy, game)) {
                continue;
            }
            playerMove(move, player, enemy, game);
            enemyMove(player, enemy, game);
        }
    }

    /**
     * Checks if the enemy has statusimpairments and executes the enemy's attack
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    private static void enemyMove(Player player, Enemy enemy, Game game) {
        enemy.checkStatusImpairments();
        if (enemy.isDead()) {
            winFight(player, enemy, game);
            return;
        }
        if (!enemy.isFrozen()) {
            enemy.interact(player);
        }
        if (player.isDead()) {
            loseFight(enemy, game);
        }
    }

    /**
     * Checks the attack move of the player and executes it
     * @param move Move
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    private static void playerMove(int move, Player player, Enemy enemy, Game game) {
        if (move == 1){
            player.attack(enemy);
        } else if (move == game.getFireMagic()) {
            System.out.println(TextColor.ANSI_YELLOW + "You have burned " + enemy.getName() + "!"
                    + TextColor.ANSI_RESET);
            enemy.setBurned(true);
        } else if (move == game.getIceMagic()) {
            System.out.println(TextColor.ANSI_YELLOW + "You have frozen " + enemy.getName() + "!"
                    + TextColor.ANSI_RESET);
            enemy.setFrozen(true);
        }
    }

    /**
     * Check and remove status impairments of the player
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     * @return False if the player is frozen
     */
    private static boolean playerStatusImpairments(Player player, Enemy enemy, Game game) {
        player.checkStatusImpairments();
        if (player.isFrozen()) {
            enemy.interact(player);
            if (player.isDead()) {
                loseFight(enemy, game);
            }
            return false;
        }
        if (player.isDead()) {
            loseFight(enemy, game);
        }
        return true;
    }

    /**
     * The player loses the fight and the game ends
     * @param enemy Enemy
     * @param game Game
     */
    private static void loseFight(Enemy enemy, Game game){
        System.out.println(TextColor.ANSI_RED + "You have been slain by " + enemy.getName() + "!"
                + TextColor.ANSI_RESET);
        game.gameOver();
    }

    /**
     * The player has won the fight, the dead npc is removed from the room, the player gets increased gold and health
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    private static void winFight(Player player, Enemy enemy, Game game) {
        System.out.println(TextColor.ANSI_YELLOW + "You have slain " + enemy.getName() + "!\nYou earned "
                + enemy.getGoldValue() + " gold." + TextColor.ANSI_RESET);
        enemy.die(game);
        player.getCurrentRoom().removeDeadNPC();
        PlayerStats.increaseGold(enemy.getGoldValue(), player);
        player.increaseHitPoints(enemy.getAttackPoints());
    }

    /**
     * Run from a fight if it is not a boss fight
     * Restores the enemy to full health
     * @param enemy Enemy
     * @return True if not boss fight
     */
    private static boolean run(Enemy enemy) {
        if (enemy instanceof MiniBoss || enemy instanceof Boss) {
            System.out.println("You cannot run from a boss fight!");
            return false;
        }
        GUIMessages.runFromFightMessage(enemy.getName());
        enemy.increaseHitPoints(enemy.getMaxHitPoints());
        return true;
    }

    /**
     * Allows the player to use an item in combat if there is one in the inventory
     * @param player Player
     * @return True if an item was used
     */
    private static boolean useCombatItem(Player player) {
        ArrayList<Collectable> combatInventory = InventoryMethods.getCombatInventory(player);
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.displayCombatInventory(player);
        if (combatInventory.size() == 0) {
            System.out.println("You have no items that can be used in combat.");
            return false;
        }
        while (true) {
            try {
                move = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUIMessages.invalidInputMessage();
            }
        }
        if (move == -1) return false;
        if (move < combatInventory.size() && move > -1) {
            combatInventory.get(move).use(player);
            return true;
        } else {
            GUIMessages.invalidItemMessage();
            return false;
        }
    }
}
