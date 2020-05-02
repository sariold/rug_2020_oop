package nl.rug.oop.rpg;

import nl.rug.oop.rpg.npcs.Enemy;

public class GUI {

    public static void displayCombatInterface(Player player, Enemy enemy, Game game) {
        String playerHealthBar = "";
        String enemyHealthBar = "";
        String indentStringName = player.getName() + ": " + Integer.toString(player.getHitPoints()) + "/" + Integer.toString(player.getMaxHitPoints());
        int indentName = 30 - indentStringName.length();
        int indentBar = 19;
        double playerPercent = (double)player.getHitPoints()/player.getMaxHitPoints();
        double enemyPercent = (double)enemy.getHitPoints()/enemy.getMaxHitPoints();
        for (double i = playerPercent; i > 0; i -= 0.1) {playerHealthBar = playerHealthBar.concat("=");}
        for (double i = enemyPercent; i > 0; i -= 0.1) {enemyHealthBar = enemyHealthBar.concat("=");}
        String playerColor = playerPercent > 0.64 ? TextColor.ANSI_GREEN : (playerPercent > 0.32 ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        String enemyColor = enemyPercent > 0.64 ? TextColor.ANSI_GREEN : (enemyPercent > 0.32 ? TextColor.ANSI_YELLOW : TextColor.ANSI_RED);
        System.out.printf("%s%s: %d/%d %s", TextColor.ANSI_YELLOW, player.getName(), player.getHitPoints(), player.getMaxHitPoints(),TextColor.ANSI_RESET);
        for (int i = 0; i < indentName; i++){ System.out.print(" ");}
        System.out.printf("%s%s%s\n", TextColor.ANSI_RED, enemy.getName(),TextColor.ANSI_RESET);
        System.out.printf("[%s%-10s%s]", playerColor, playerHealthBar, TextColor.ANSI_RESET);
        for (int i = 0; i < indentBar; i++){ System.out.print(" ");}
        System.out.printf("[%s%-10s%s]\n", enemyColor, enemyHealthBar, TextColor.ANSI_RESET);
        System.out.println("What will you do?");
        for(int i = 0; i < game.getFightMoves().size(); i++) {
            System.out.println("\t (" + i + ") " + game.getFightMoves().get(i));
        }
    }
}
