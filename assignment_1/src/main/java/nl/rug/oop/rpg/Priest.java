package nl.rug.oop.rpg;

public class Priest extends Healer {

    public Priest(String name) {
        super("May the divine spirits look upon your soul!", name, DefaultStats.PRIEST_HEAL_POWER);
    }

    @Override
    public void heal(Player player) {
//        System.out.println(TextColor.ANSI_GREEN + "You have been healed for " + this.getHealPower() + "." + TextColor.ANSI_RESET);
        super.heal(player);
    }

    @Override
    public String getSpecies() {
        return "Priest";
    }
}
