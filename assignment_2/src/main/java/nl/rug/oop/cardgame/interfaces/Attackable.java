package nl.rug.oop.cardgame.interfaces;

/**
 * Objects that can be attacked
 */
public interface Attackable {

    /**
     * Set health
     * @param health Health
     */
    void setHealth(int health);

    /**
     * Get health
     * @return Health
     */
    int getHealth();

    /**
     * Attack method
     * @param attackable Attackable
     */
    void attack(Attackable attackable);

    /**
     * Get attack
     * @return Attack
     */
    int getAttack();

    /**
     * Set attack
     * @param attack Attack
     */
    void setAttack(int attack);
}
