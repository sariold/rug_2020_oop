package nl.rug.oop.rpg.Player;

import nl.rug.oop.rpg.extra.TextColor;

public class PlayerStats {

    /**
     * Setter method to increase the max health points a player has
     * @param value Value
     * @param player Player
     */
    public static void increaseMaxHitPoints(int value, Player player) {
        System.out.println(TextColor.ANSI_YELLOW + "Your health increased by " + value + "." + TextColor.ANSI_RESET);
        player.setMaxHitPoints(player.getMaxHitPoints() + value);
        player.setAttackPoints(player.getHitPoints() + value);
    }

    /**
     * Increases the attack dmamage of a player
     * @param value Value
     * @param player Player
     */
    public static void increaseAttackPoints(int value, Player player) {
        System.out.println(TextColor.ANSI_YELLOW + "Your attack increased by " + value + "." + TextColor.ANSI_RESET);
        player.setAttackPoints(player.getAttackPoints() + value);
    }

    /**
     * Increases the player's gold value
     * @param value Value
     * @param player Player
     */
    public static void increaseGold(int value, Player player) { player.setGold(player.getGold() + value); }

    /**
     * Decreases the player's gold value
     * @param value Value
     * @param player Player
     */
    public static void decreaseGold(int value, Player player) { player.setGold(player.getGold() - value); }

    /**
     * Increases the health of a player
     * @param value Value
     * @param player Player
     * */
    public static void increasePlayerHitPoints(int value, Player player) {
        System.out.println(TextColor.ANSI_GREEN + "You have been healed " + value + " hit points!"
                + TextColor.ANSI_RESET);
        player.setHitPoints(player.getHitPoints() + value);
        if (player.getHitPoints() > player.getMaxHitPoints()) player.setHitPoints(player.getMaxHitPoints());
    }
}
