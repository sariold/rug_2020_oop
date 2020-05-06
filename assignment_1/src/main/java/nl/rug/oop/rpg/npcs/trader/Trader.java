package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.Game;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.TextColor;
import nl.rug.oop.rpg.npcs.DungeonNpc;

import java.io.Serializable;

/**
 * abstract class Trader extends abstract class DungeonNpc increases stats or item power of a player in exchange for gold
 */
public abstract class Trader extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 33L;

    private String name;
    private int power;
    private int price;

    /**
     * Constructor for a trader
     * @param description
     * @param name
     * @param power
     * @param price
     */
    public Trader(String description, String name, int power, int price) {
        super(description, name);
        this.name = name;
        this.power = power;
        this.price = price;
        this.engaged = false;
    }

    /**
     * returns this traders power
     * @return power
     */
    public int getPower() {
        return this.power;
    }

    /**
     * returns this traders price
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * remove as much gold from the player as the price of this trader
     * print that the player has traded with this trader
     * @param player
     */
    public void trade(Player player) {
        player.decreaseGold(this.price);
        this.engaged = true;
        System.out.println("You traded with " + this.getName() + ". You have " + player.getGold() + " gold.");
        return;
    }

    /**
     * return the trade dialog of this trader
     * @return String
     */
    public String tradeDialog() {
        String toReturn = "I will trade for " + this.price + " Gold.";
        return toReturn;
    }

    /**
     * call this traders trade method
     * @param player
     */
    @Override
    public void interact(Player player) {
        trade(player);
    }

    /**
     * calls the tradeWith method from the
     * @param player
     * @param game
     */
    @Override
    public void engage(Player player, Game game) {
        game.tradeWith(player, this);
    }

    /**
     * returns the type of this trader
     * @return "Trader"
     */
    @Override
    public String getType() {
        return TextColor.ANSI_BLUE +  "Trader" + TextColor.ANSI_RESET;
    }
}
