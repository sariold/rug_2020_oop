package nl.rug.oop.rpg;

public class HighPriest extends Healer{

    public HighPriest(String name) {
        super("God Shall bless you!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    @Override
    public void heal(Player player) {
        System.out.println("You have been fully healed.");
        player.increaseHitPoints(player.getMaxHitPoints());
    }

    @Override
    public String getSpecies() {
        return "High Priest";
    }
}
