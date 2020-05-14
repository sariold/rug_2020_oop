package nl.rug.oop.cardgame.util;

/**
 * Interface that checks if a deck is empty
 */
public interface Emptiable {

    /**
     * Check if there are any items in this Emptiable.
     */
    boolean isEmpty();

    /**
     * Make this structure empty such that isEmpty() return true
     */
    void empty();

}
