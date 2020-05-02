package nl.rug.oop.rpg.interfaces;

import nl.rug.oop.rpg.Player;

public interface Collectable {

    public void collect(Player player);

    public void use(Player player);
}
