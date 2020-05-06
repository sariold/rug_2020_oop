package nl.rug.oop.rpg.interfaces;

import nl.rug.oop.rpg.Player;

public interface Collectable {

    /**
     * adds this collectable to the players inventory
     * @param player
     */
    public void collect(Player player);

    /**
     * uses this collectable and removes it from the players inventory
     * @param player
     */
    public void use(Player player);

    /**
     * checks whether this collectable can be used in combat
     * @return true if it has combat use
     */
    public boolean hasCombatUse();

    /**
     * checks whether this collectable can be used outside of combat
     * @return true if it has non combat use
     */
    public boolean hasNonCombatUse();
}
