package nl.rug.oop.rpg.npcs.trader;

import nl.rug.oop.rpg.gui.GUIMessages;
import nl.rug.oop.rpg.extra.DefaultStats;
import nl.rug.oop.rpg.gui.GUI;
import nl.rug.oop.rpg.game.InventoryMethods;
import nl.rug.oop.rpg.player.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.items.EnchantItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Enchanter extends abstract class Trader increases the power of an enchantable item for gold
 */
public class Enchanter extends Trader{

    private static final long serialVersionUID = 35L;

    /**
     * Constructor for an enchanter
     * Power and price are set to default values
     * @param name Name
     */
    public Enchanter(String name) {
        super("I will enchant one of your items for a little price!", name, DefaultStats.ENCHANTER_POWER,
                DefaultStats.ENCHANTER_PRICE);
    }

    /**
     * Trading with an enchanter lists all enchantable items in the player inventory
     * The chosen item will increase in power
     * @param player Player
     */
    @Override
    public void trade(Player player) {
        int currentMove;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Collectable> items = InventoryMethods.getEnchantableInventory(player);
        if (items.size() == 0) {
            System.out.println("You have no enchantable items you fool!");
            return;
        }
        System.out.println("Which item shall I enchant? (-1 to not enchant an item)");
        GUI.displayItems(items);
        try{
            currentMove = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUIMessages.invalidInputMessage();
            scanner.nextLine();
            return;
        }
        if (currentMove == -1){
            System.out.println("You did not enchant anything!");
            return;
        }
        if (currentMove < items.size() && currentMove > -1) ((EnchantItem)items.get(currentMove))
                .enchant(this.getPower());
        else {
            GUIMessages.invalidItemMessage();
            return;
        }
        super.trade(player);
    }

    /**
     * Returns the species of this trader
     * @return "Enchanter"
     */
    @Override
    public String getSpecies() {
        return "Enchanter";
    }

    /**
     * Return the trade dialog for an enchanter
     * @return String
     */
    @Override
    public String tradeDialog() {
        return "I will increase the power of one of your items by " + this.getPower()
                + " for " + this.getPrice() + " gold!";
    }
}
