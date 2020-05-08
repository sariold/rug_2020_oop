package nl.rug.oop.rpg.game;

import nl.rug.oop.rpg.Player.Player;
import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.npcs.enemies.Enemy;

import java.util.Random;

/**
 * Class to handle Player or Enemy attacking a player/npc
 */
public class AttackMethods {

    /**
     * Enemy attacking the player, deals damage to the player with a random critical hit damage amount
     * @param enemy Enemy
     * @param attacked Attacked
     */
    public static void enemyAttacker(Enemy enemy, Attackable attacked) {
        System.out.println(TextColor.ANSI_RED + "You are attacked by " + enemy.getName() + TextColor.ANSI_RESET);
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 16) {
            System.out.println(TextColor.ANSI_RED + "Critical Hit!" + TextColor.ANSI_RESET);
            attacked.reduceHitPoints(2 * enemy.getAttackPoints());
        } else {
            attacked.reduceHitPoints(enemy.getAttackPoints());
        }
        System.out.println(TextColor.ANSI_RED + "You are at " +  attacked.getHitPoints() + " health!"
                + TextColor.ANSI_RESET);
    }

    /**
     * Checking the current battle impairments on player/enemy npc
     * @param attacked Attacked
     */
    public static void checkImpairments(Attackable attacked) {
        Random r = new Random();
        int chance;
        if (attacked.isFrozen()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.FREEZE_CHANCE) {
                System.out.println(TextColor.ANSI_RED + attacked.getName() + " is frozen solid."
                        + TextColor.ANSI_RESET);
            } else {
                System.out.println(TextColor.ANSI_BLUE + attacked.getName() + " is no longer frozen!"
                        + TextColor.ANSI_RESET);
                attacked.setFrozen(false);
            }
        }
        if (attacked.isBurned()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.BURN_CHANCE) {
                System.out.println(TextColor.ANSI_RED + attacked.getName() + " is burned and takes "
                        + DefaultStats.BURN_DAMAGE
                        + " damage." +TextColor.ANSI_RESET);
                attacked.reduceHitPoints(DefaultStats.BURN_DAMAGE);
            } else {
                System.out.println(TextColor.ANSI_BLUE + attacked.getName() + " does no longer burn!"
                        + TextColor.ANSI_RESET);
                attacked.setBurned(false);
            }
        }
    }

    /**
     * Player deals damage to an enemy npc, checks random critical hit for more damage
     * @param attacked Attacked
     * @param player Player
     */
    public static void playerAttacker(Attackable attacked, Player player) {
        int damage = player.getAttackPoints();
        System.out.println(TextColor.ANSI_YELLOW + "You attack " + attacked.getName()
                + TextColor.ANSI_RESET);
        Random r = new Random();
        int critical = r.nextInt(101);
        if (critical < 21) {
            System.out.println(TextColor.ANSI_YELLOW + "Critical Hit!" + TextColor.ANSI_RESET);
            damage *= 2;
        }
        System.out.println(TextColor.ANSI_YELLOW + attacked.getName() + " takes " + damage
                + " damage!" + TextColor.ANSI_RESET);
        attacked.reduceHitPoints(damage);
    }

}
