package nl.rug.oop.rpg;

public class MagicOrb extends Item {

    private Room to;

    public MagicOrb(String description, Room to) {
        super(description);
        this.to = to;
    }

    @Override
    public void collect(Player player) {

    }

    @Override
    public void use(Player pLayer) {

    }

    @Override
    public String toString() {
        return "Magic Orb";
    }
}
