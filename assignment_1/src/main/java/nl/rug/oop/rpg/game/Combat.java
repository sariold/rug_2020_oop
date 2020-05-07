package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.npcs.enemies.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Combat class to allow the player to engage in fights with the npcs
 */
public class Combat {

    /**
     * Forces the player to engage into combat with a specific enemy
     * @param player
     * @param enemy
     * @param game
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
                GUI.invalidInputMessage();
                continue;
            }
            if (move > game.getFightMoves().size()) {
                GUI.invalidInputMessage();
                continue;
            }
            if (move == 0){
                if (run(enemy)) {
                    return;
                }
                continue;
            }
            // Combat item use
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
     * checks if the enemy has statusimpairments and executes the enemy's attack
     * @param player
     * @param enemy
     * @param game
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
            loseFight(player, enemy, game);
        }
    }

    /**
     * checks the attack move of the player and executes it
     * @param move
     * @param player
     * @param enemy
     * @param game
     */
    private static void playerMove(int move, Player player, Enemy enemy, Game game) {
        if (move == 1){
            player.attack(enemy);
        } else if (move == game.getFireMagic()) {
            System.out.println(TextColor.ANSI_YELLOW + "You have burned " + enemy.getName() + "!"
                    + TextColor.ANSI_RESET);
            enemy.burn();
        } else if (move == game.getIceMagic()) {
            System.out.println(TextColor.ANSI_YELLOW + "You have frozen " + enemy.getName() + "!"
                    + TextColor.ANSI_RESET);
            enemy.freeze();
        }
    }

    /**
     * check and remove status impairments of the player
     * @param player
     * @param enemy
     * @param game
     * @return false if the player is frozen
     */
    private static boolean playerStatusImpairments(Player player, Enemy enemy, Game game) {
        player.checkStatusImpairments();
        if (player.isFrozen()) {
            enemy.interact(player);
            if (player.isDead()) {
                loseFight(player, enemy, game);
            }
            return false;
        }
        if (player.isBurned()) {
            player.reduceHitPoints(DefaultStats.BURN_DAMAGE);
            if (player.isDead()) {
                loseFight(player, enemy, game);
            }
        }
        return true;
    }

    /**
     * The player loses the fight and the game ends
     * @param player
     * @param enemy
     * @param game
     */
    private static void loseFight(Player player, Enemy enemy, Game game){
        System.out.println(TextColor.ANSI_RED + "You have been slain by " + enemy.getName() + "!"
                + TextColor.ANSI_RESET);
        game.gameOver();
    }

    /**
     * The player has won the fight, the dead npc is removed from the room, the player gets increased gold and health
     * @param player
     * @param enemy
     * @param game
     */
    private static void winFight(Player player, Enemy enemy, Game game) {
        System.out.println(TextColor.ANSI_YELLOW + "You have slain " + enemy.getName() + "!\nYou earned "
                + enemy.getGoldValue() + " gold." + TextColor.ANSI_RESET);
        enemy.die(game);
        player.getCurrentRoom().removeDeadNPC();
        player.increaseGold(enemy.getGoldValue());
        player.increaseHitPoints(enemy.getAttackPoints());
    }

    /**
     * run from a fight if it is not a boss fight
     * restores the enemy to full health
     * @param enemy
     * @return true if not boss fight
     */
    private static boolean run(Enemy enemy) {
        if (enemy instanceof MiniBoss || enemy instanceof Boss) {
            System.out.println("You cannot run from a boss fight!");
            return false;
        }
        System.out.println(TextColor.ANSI_YELLOW + "You ran from the fight. " + TextColor.ANSI_RED
                + enemy.getName() + TextColor.ANSI_YELLOW + " recovered to full health!"
                + TextColor.ANSI_RESET);
        enemy.increaseHitPoints(enemy.getMaxHitPoints());
        return true;
    }

    /**
     * allows the player to use an item in combat if there is one in the inventory
     * @param player
     * @return true if an item was used
     */
    private static boolean useCombatItem(Player player) {
        Scanner scanner = new Scanner(System.in);
        int move;
        GUI.displayCombatInventory(player);
        if (player.getCombatInventory().size() == 0) {
            System.out.println("You have no items that can be used in combat.");
            return false;
        }
        while (true) {
            try {
                move = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                GUI.invalidInputMessage();
            }
        }
        if (move == -1) return false;
        if (move < player.getCombatInventory().size() && move > -1) {
            player.getCombatInventory().get(move).use(player);
            return true;
        } else {
            GUI.invalidItemMessage();
            return false;
        }
    }

}
