package nl.rug.oop.rpg;

public class WeaponSmith extends Trader{

    public WeaponSmith(String name) {
        super("Better get them before they get you!", name, DefaultStats.WEAPONSMITH_POWER, DefaultStats.WEAPONSMITH_PRICE);
    }

    @Override
    public void trade(Player player) {
        player.increaseAttackPoints(this.getPower());
        System.out.println("Your attack increased by " + this.getPower() + ".");
        super.trade(player);
    }

    @Override
    public String getSpecies() {
        return "Weaponsmith";
    }

    @Override
    public String tradeDialog() {
        String toReturn = "I will increase your attack power by " + this.getPower() + " for " + this.getPrice() + " gold!";
        return toReturn;
    }
}