package nl.rug.oop.rpg.game.combat;

import nl.rug.oop.rpg.game.GameInteract;
import nl.rug.oop.rpg.gui.GUI;
import nl.rug.oop.rpg.gui.GUIMessages;
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
                if (run(player, enemy)) {
                    return;
                }
                continue;
            }
            if (move == 2) {
                if (!useCombatItem(player)) {
                    continue;
                }
            }
            AttackMethods.playerAttack(move, player, enemy, game);
            AttackMethods.enemyAttack(player, enemy, game);
        }
    }

    /**
     * The player loses the fight and the game ends
     * @param enemy Enemy
     * @param game Game
     */
    public static void loseFight(Enemy enemy, Game game){
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
    public static void winFight(Player player, Enemy enemy, Game game) {
        System.out.println(TextColor.ANSI_YELLOW + "You have slain " + enemy.getName() + "!\nYou earned "
                + enemy.getGoldValue() + " gold." + TextColor.ANSI_RESET);
        enemy.die(game);
        player.setBonusAttackPoints(0);
        player.getCurrentRoom().removeDeadNPC();
        PlayerStats.increaseGold(enemy.getGoldValue(), player);
        int healValue = GameInteract.getHealValue(player.getHitPoints(), player.getMaxHitPoints(), enemy.getAttackPoints());
        player.setHitPoints(player.getHitPoints() + healValue);
        GUIMessages.playerHealedMessage(healValue);
    }

    /**
     * Run from a fight if it is not a boss fight
     * Restores the enemy to full health
     * @param enemy Enemy
     * @return True if not boss fight
     */
    private static boolean run(Player player, Enemy enemy) {
        if (enemy instanceof MiniBoss || enemy instanceof Boss) {
            System.out.println("You cannot run from a boss fight!");
            return false;
        }
        GUIMessages.runFromFightMessage(enemy.getName());
        player.setBonusAttackPoints(0);
        enemy.setHitPoints(enemy.getMaxHitPoints());
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
