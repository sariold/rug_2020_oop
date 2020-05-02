package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.Player;

import java.io.Serializable;

public abstract class Trader extends DungeonNpc implements Serializable {

    private static final long serialVersionUID = 33L;

    private String name;
    private int power;
    private int price;
    private boolean hasTraded;

    public Trader(String description, String name, int power, int price) {
        super(description);
        this.name = name;
        this.power = power;
        this.price = price;
        this.hasTraded = false;
    }

    public Trader(String description) {
        this(description, "a Trader", 1, 1);
    }

    public int getPower() {
        return this.power;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return this.name;
    }

    public boolean getTradeStatus() {
        return this.hasTraded;
    }

    public void trade(Player player) {
        player.decreaseGold(this.price);
        this.hasTraded = true;
        return;
    }

    public String tradeDialog() {
        String toReturn = "I will trade for " + this.price + " Gold.";
        return toReturn;
    }

    @Override
    public void interact(Player player) {
        trade(player);
    }

    @Override
    public String getType() {
        return "Trader";
    }

    @Override
    public String toString() {
        return this.name;
    }
}
