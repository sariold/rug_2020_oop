package nl.rug.oop.rpg.interfaces;

/**
 * Interface to make an object attackable it can enter combat
 */
public interface Attackable {

    /**
     * Checks whether an attackable object is frozen or burned
     */
    void checkStatusImpairments();

    /**
     * Returns the hit points of an attackable object
     * @return Hit points
     */
    int getHitPoints();

    /**
     * Returns the attack points
     * @return Attack points
     */
    int getAttackPoints();

    /**
     * Returns the bonus attack points
     * @return Bonus Attack Points
     */
    int getBonusAttackPoints();

    /**
     * Sets the hit points to the given value
     * @param value Value
     */
    void setHitPoints(int value);

    /**
     * Attacks an attackable object
     * @param attacked Attacked
     */
    void attack(Attackable attacked);

    /**
     * Checks whether the object has more than 0 hit points
     * @return True if dead
     */
    boolean isDead();

    /**
     * Returns if the object is frozen
     * @return If object is frozen
     */
    boolean isFrozen();

    /**
     * Sets the frozen value of this object
     * @param b Boolean value
     */
    void setFrozen(boolean b);

    /**
     * Returns if the object is burned
     * @return If object is burned
     */
    boolean isBurned();

    /**
     * Sets the burned value of this object
     * @param b Boolean
     */
    void setBurned(boolean b);

    /**
     * Returns the name of this object
     * @return Name of object
     */
    String getName();
}
