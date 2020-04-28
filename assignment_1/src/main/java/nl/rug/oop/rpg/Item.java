package nl.rug.oop.rpg;

public abstract class Item extends DungeonObjects implements Collectable{

    private boolean used;

    public Item(String description) {
        super(description);
        this.used = false;
    }

    @Override
    public void collect(Player player) {
        player.addCollectable(this);
    }

    @Override
    public void use(Player player) {
        this.used = true;
        player.removeUsedItem();
    }

    public boolean getUsed() {
        return this.used;
    }

    public void setUsed(boolean b) {
        this.used = b;
    }
}
