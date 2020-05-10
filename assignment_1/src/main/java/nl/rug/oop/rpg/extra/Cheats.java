package nl.rug.oop.rpg.extra;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;

/**
 * Class to check if the user has inputted a specific name that might be a cheat code
 */
public class Cheats {

    /**
     * Checks if the player name is a specific name which means it might be a cheat code, thus giving the player
     * Increases health or stats or gold
     * @param player Player
     * @param game Game
     */
    public static void checkCheatCodes(Player player, Game game) {
        if (player.getName().equals("John Wick")) {
            System.out.println(TextColor.ANSI_YELLOW + "Check your stats Mr. Wick." + TextColor.ANSI_RESET);
            player.setGold(100);
            player.setMaxHitPoints(100);
            player.setHitPoints(100);
            player.setAttackPoints(100);
            game.addIceMagic();
            game.addFireMagic();
        }
        if (player.getName().equals("The Hulk")) {
            System.out.println(TextColor.ANSI_YELLOW + "HULK SMASH!!" + TextColor.ANSI_RESET);
            player.setAttackPoints(15);
        }
        if (player.getName().equals("King Midas")) {
            System.out.println(TextColor.ANSI_YELLOW + "Everything you touch turns to gold." + TextColor.ANSI_RESET);
            player.setGold(1000);
        }
        if (player.getName().equals("Harry Potter")) {
            System.out.println(TextColor.ANSI_YELLOW + "You're a wizard Harry." + TextColor.ANSI_RESET);
            game.addIceMagic();
            game.addFireMagic();
        }
    }
}
