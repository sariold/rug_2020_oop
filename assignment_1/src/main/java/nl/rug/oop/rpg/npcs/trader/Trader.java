package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.game.Game;
import nl.rug.oop.rpg.game.GameInteract;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.extra.TextColor;
import nl.rug.oop.rpg.player.PlayerStats;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;

/**
 * Abstract class Trader extends abstract class DungeonNpc increases stats or item power of a player in exchange for
 * gold
 */
public abstract class Trader extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 33L;

    private final int power;
    private final int price;

    /**
     * Constructor for a trader
     * @param description Description
     * @param name Name
     * @param power Power
     * @param price Price
     */
    public Trader(String description, String name, int power, int price) {
        super(description, name);
        this.power = power;
        this.price = price;
        this.engaged = false;
    }

    /**
     * Returns this traders power
     * @return Power
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Returns this traders price
     * @return Price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Remove as much gold from the player as the price of this trader
     * Print that the player has traded with this trader
     * @param player Player
     */
    public void trade(Player player) {
        PlayerStats.decreaseGold(this.price, player);
        this.engaged = true;
        System.out.println("You traded with " + this.getName() + ". You have " + player.getGold() + " gold.");
    }

    /**
     * Return the trade dialog of this trader
     * @return String
     */
    public String tradeDialog() {
        return "I will trade for " + this.price + " Gold.";
    }

    /**
     * Call this traders trade method
     * @param player Player
     */
    @Override
    public void interact(Player player) {
        trade(player);
    }

    /**
     * Calls the tradeWith method from the
     * @param player Player
     * @param game Game
     */
    @Override
    public void engage(Player player, Game game) {
        GameInteract.tradeWith(player, this);
    }

    /**
     * Returns the type of this trader
     * @return "Trader"
     */
    @Override
    public String getType() {
        return TextColor.ANSI_BLUE +  "Trader" + TextColor.ANSI_RESET;
    }
}
