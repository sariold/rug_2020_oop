package nl.rug.oop.cardgame.util;

/**
 * Default coordinates of each hitbox on game panel
 */
public class DefaultCoordinates {

    /**
     * Player hand coords
     */
    public static final int[][] PLAYER_HAND = {{100, 530, 100, 150},{250, 530, 100, 150},
            {400, 530, 100, 150},{780, 530, 100, 150},{930, 530, 100, 150},{1080, 530, 100, 150}};

    /**
     * Player battlefield corods
     */
    public static final int[][] PLAYER_BATTLEFIELD = {{140, 360, 90, 135},{340, 360, 90, 135},
            {540, 360, 90, 135},{740, 360, 90, 135},{940, 360, 90, 135},};

    /**
     * Player deck coords
     */
    public static final int[] PLAYER_DECK = {1159, 360, 100, 150};

    /**
     * Player discard deck coords
     */
    public static final int[] PLAYER_DISCARD_PILE = {10, 360, 100, 150};

    /**
     * Ai hand coords
     */
    public static final int[][] AI_HAND = {{100, 10, 100, 150},{250, 10, 100, 150},
            {400, 10, 100, 150},{780, 10, 100, 150},{930, 10, 100, 150},{1080, 10, 100, 150}};

    /**
     * Ai battlefield coords
     */
    public static final int[][] AI_BATTLEFIELD = {{140, 194, 90, 135},{340, 194, 90, 135},
            {540, 194, 90, 135},{740, 194, 90, 135},{940, 194, 90, 135},};

    /**
     * Ai deck coords
     */
    public static final int[] AI_DECK = {1159, 180, 100, 150};

    /**
     * AI discard deck coords
     */
    public static final int[] AI_DISCARD_PILE = {10, 180, 100, 150};


}
