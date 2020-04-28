package nl.rug.oop.rpg;

public class GoldNugget extends Item{

    private int value;

    public GoldNugget() {
        super("This nugget is worth some money.");
        this.value = DefaultStats.GOLD_NUGGET_VALUE;
    }

    @Override
    public void use(Player player) {
        player.increaseGold(this.value);
        System.out.println(TextColor.ANSI_YELLOW + "You gained " + this.value + " gold." + TextColor.ANSI_RESET);
        super.use(player);
    }

    @Override
    public String toString() {
        return "Gold Nugget";
    }
}