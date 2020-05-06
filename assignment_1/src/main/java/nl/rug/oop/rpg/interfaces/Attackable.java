package nl.rug.oop.rpg.interfaces;

public interface Attackable {

    /**
     * checks whether an attackable object is frozen or burned
     */
    public void checkStatusImpairments();

    /**
     * returns the hit points of an attackable object
     * @return hit points
     */
    public int getHitPoints();

    /**
     * attacks an attackable object
     * @param attacked
     */
    public void attack(Attackable attacked);

    /**
     * reduces the hit points of this object by the given value
     * @param value
     */
    public void reduceHitPoints(int value);

    /**
     * increases the hit points of this object by the given value
     * @param value
     */
    public void increaseHitPoints(int value);

    /**
     * checks whether the object has more than 0 hit points
     * @return true if dead
     */
    public boolean isDead();
}
