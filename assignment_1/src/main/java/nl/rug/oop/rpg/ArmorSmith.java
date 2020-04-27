package nl.rug.oop.rpg;

public class ArmorSmith extends Trader{

    public ArmorSmith(String name) {
        super("Shields and chestplates have saved my life!", name, DefaultStats.ARMORSMITH_POWER, DefaultStats.ARMORSMITH_PRICE);
    }

    @Override
    public void trade(Player player) {
        player.increaseMaxHitPoints(this.getPower());
        super.trade(player);
    }

    @Override
    public String getSpecies() {
        return "Armorsmith";
    }
}
