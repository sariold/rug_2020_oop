package nl.rug.oop.rpg;

import nl.rug.oop.rpg.npcs.enemies.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Combat {

    public static void engageFight(Player player, Enemy enemy, Game game) {
        int move;
        int damageToEnemy = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("You engaged a fight with " + enemy.getName());
        while (enemy.getHitPoints() > 0 && player.getHitPoints() > 0) {
            GUI.displayCombatInterface(player, enemy, game);
            try {
                move = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That is not a valid input!");
                continue;
            }
            if (move > game.getFightMoves().size()) {
                System.out.println("That is not a valid input!");
                continue;
            }
            if (move == 0){
                if (enemy instanceof MiniBoss || enemy instanceof Boss) {
                    System.out.println("You cannot run from a boss fight!");
                    continue;
                }
                System.out.println(TextColor.ANSI_YELLOW + "You ran from the fight. " + TextColor.ANSI_RED + enemy.getName() + TextColor.ANSI_YELLOW + " recovered to full health!" + TextColor.ANSI_RESET);
                enemy.increaseHitPoints(damageToEnemy);
                return;
            }
            // Combat item use
            if (move == 2) {
                // if you use an item in getCOmbatItems it should be uset in the inventory
                GUI.displayCombatInventory(player);
                if (player.getCombatInventory().size() == 0) continue;
                try {
                    move = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("That is not a valid input!");
                    continue;
                }
                if (move == -1) return;
                if (move < player.getCombatInventory().size() && move > -1) {
                    player.getCombatInventory().get(move).use(player);
                } else {
                    System.out.println("That is not a valid item!");
                    continue;
                }
            }
            player.checkStatusImpairments();
            if (player.isFrozen()) {
                enemy.interact(player);
                if (player.isDead()) {
                    loseFight(player, enemy, game);
                }
                continue;
            }
            if (player.isBurned()) {
                player.reduceHitPoints(DefaultStats.BURN_DAMAGE);
                if (player.isDead()) {
                    loseFight(player, enemy, game);
                }
            }
            if (move == 1){
                System.out.println(TextColor.ANSI_YELLOW + "You attack " + enemy.getName() + TextColor.ANSI_RESET);
                System.out.println(TextColor.ANSI_YELLOW + enemy.getName() + " takes " + player.getAttackPoints() + " damage!" + TextColor.ANSI_RESET);
                player.attack(enemy);
                damageToEnemy += player.getAttackPoints();
            } else if (move == game.getFireMagic()) {
                System.out.println(TextColor.ANSI_YELLOW + "You have burned " + enemy.getName() + "!" + TextColor.ANSI_RESET);
                enemy.burn();
            } else if (move == game.getIceMagic()) {
                System.out.println(TextColor.ANSI_YELLOW + "You have frozen " + enemy.getName() + "!" + TextColor.ANSI_RESET);
                enemy.freeze();
            }
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
    }

    private static void loseFight(Player player, Enemy enemy, Game game){
        System.out.println(TextColor.ANSI_RED + "You have been slain by " + enemy.getName() + "!" + TextColor.ANSI_RESET);
        game.gameOver();
    }

    private static void winFight(Player player, Enemy enemy, Game game) {
        System.out.println(TextColor.ANSI_YELLOW + "You have slain " + enemy.getName() + "!\nYou earned " + enemy.getGoldValue() + " gold." + TextColor.ANSI_RESET);
        enemy.die(game);
        player.getCurrentRoom().removeDeadNPC();
        player.increaseGold(enemy.getGoldValue());
        player.increaseHitPoints(enemy.getAttackPoints());
    }


}
