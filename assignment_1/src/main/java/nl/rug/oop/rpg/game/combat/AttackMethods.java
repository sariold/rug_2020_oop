package nl.rug.oop.rpg.game.combat;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.interfaces.Attackable;
import nl.rug.oop.rpg.npcs.enemies.Enemy;

import java.util.Random;

/**
 * Class to handle Player or Enemy attacking a player/npc
 */
public class AttackMethods {

    /**
     * Executes the attack of one attackable onto another
     * @param attacker Attacker
     * @param attacked Attacked
     */
    public static void attackMove(Attackable attacker, Attackable attacked) {
        int damage = attacker.getAttackPoints() + attacker.getBonusAttackPoints();
        GUIMessages.attackedMessage(attacker, attacked);
        if (criticalHit()) {
            GUIMessages.criticalHitMessage();
            damage *= 2;
        }
        attacked.setHitPoints(attacked.getHitPoints() - damage);
        GUIMessages.takesDamageMessage(attacked, damage);
    }

    /**
     * determines whether an attack is a critical hit
     * @return True if critical hit
     */
    private static boolean criticalHit() {
        Random r = new Random();
        int critical = r.nextInt(101);
        return critical < DefaultStats.CRITICAL_HIT_CHANCE;
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
                GUIMessages.isFrozenMessage(attacked);
            } else {
                GUIMessages.noLongerFrozenMessage(attacked);
                attacked.setFrozen(false);
            }
        }
        if (attacked.isBurned()) {
            chance = r.nextInt(101);
            if (chance < DefaultStats.BURN_CHANCE) {
                GUIMessages.burnDamageMessage(attacked);
                attacked.setHitPoints(attacked.getHitPoints() - DefaultStats.BURN_DAMAGE);
            } else {
                GUIMessages.noLongerBurnedMessage(attacked);
                attacked.setBurned(false);
            }
        }
    }

    /**
     * Checks if the enemy has statusimpairments and executes the enemy's attack
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    static void enemyAttack(Player player, Enemy enemy, Game game) {
        enemy.checkStatusImpairments();
        if (enemy.isDead()) {
            Combat.winFight(player, enemy, game);
            return;
        }
        if (!enemy.isFrozen()) {
            enemy.interact(player);
        }
        if (player.isDead()) {
            Combat.loseFight(enemy, game);
        }
    }

    /**
     * Checks the attack move of the player and executes it
     * @param move Move
     * @param player Player
     * @param enemy Enemy
     * @param game Game
     */
    static void playerAttack(int move, Player player, Enemy enemy, Game game) {
        player.checkStatusImpairments();
        if (player.isFrozen()) {
            GUIMessages.isFrozenMessage(player);
            return;
        }
        if (move == 1){
            player.attack(enemy);
        } else if (move == game.getFireMagic()) {
            GUIMessages.hasBeenBurnedMessage(enemy);
            enemy.setBurned(true);
        } else if (move == game.getIceMagic()) {
            GUIMessages.hasBeenFrozenMessage(enemy);
            enemy.setFrozen(true);
        }
    }
}
