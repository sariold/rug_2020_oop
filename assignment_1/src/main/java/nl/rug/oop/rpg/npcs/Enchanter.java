package nl.rug.oop.rpg.npcs;

import nl.rug.oop.rpg.DefaultStats;
import nl.rug.oop.rpg.GUI;
import nl.rug.oop.rpg.Player;
import nl.rug.oop.rpg.interfaces.Collectable;
import nl.rug.oop.rpg.objects.EnchantItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Enchanter extends Trader{

    private static final long serialVersionUID = 14L;

    public Enchanter(String name) {
        super("I will enchant one of your items for a little price!", name, DefaultStats.ENCHANTER_POWER, DefaultStats.ENCHANTER_PRICE);
    }

    @Override
    public void trade(Player player) {
        int currentMove;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Collectable> items = player.getEnchantableInventory();
        if (items.size() == 0) {
            System.out.println("You have no enchantable items you fool!");
            return;
        }
        System.out.println("Which item shall I enchant? (-1 to not enchant an item)");
        GUI.displayItems(items);
        try{
            currentMove = scanner.nextInt();
        } catch (InputMismatchException e) {
            GUI.invalidInputMessage();
            scanner.nextLine();
            return;
        }
        if (currentMove == -1){
            System.out.println("You did not enchant anything!");
            return;
        }
        if (currentMove < items.size() && currentMove > -1) ((EnchantItem)items.get(currentMove)).enchant(this.getPower());
        else {
            GUI.invalidItemMessage();
            return;
        }
        super.trade(player);
    }

    @Override
    public String getSpecies() {
        return "Enchanter";
    }

    @Override
    public String tradeDialog() {
        String toReturn = "I will increase the power of one of your items by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}
