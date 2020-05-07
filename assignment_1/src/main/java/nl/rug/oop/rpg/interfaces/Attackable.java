package nl.rug.oop.rpg.interfaces;

/**
 * Interface to make an object attackable it can enter combat
 */
public interface Attackable {

    /**
     * Checks whether an attackable object is frozen or burned
     */
    public void checkStatusImpairments();

    /**
     * Returns the hit points of an attackable object
     * @return Hit points
     */
    public int getHitPoints();

    /**
     * Attacks an attackable object
     * @param attacked
     */
    public void attack(Attackable attacked);

    /**
     * Reduces the hit points of this object by the given value
     * @param value
     */
    public void reduceHitPoints(int value);

    /**
     * Increases the hit points of this object by the given value
     * @param value
     */
    public void increaseHitPoints(int value);

    /**
     * Checks whether the object has more than 0 hit points
     * @return True if dead
     */
    public boolean isDead();
}
