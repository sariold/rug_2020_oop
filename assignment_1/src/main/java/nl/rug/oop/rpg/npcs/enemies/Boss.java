package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.Game;

import java.io.Serializable;

/**
 * abstract class Boss defeating this enemy wins the game
 */
public abstract class Boss extends Enemy implements Serializable {

    private static final long serialVersionUID = 16L;

    /**
     * Constructor for a boss
     * @param description
     * @param name
     * @param hitPoints
     * @param attackPoints
     * @param goldValue
     */
    public Boss(String description, String name, int hitPoints, int attackPoints, int goldValue){
        super(description, name, hitPoints, attackPoints, goldValue);
    }

    /**
     * if a boss dies the game is won
     * @param game
     */
    @Override
    public void die(Game game) {
        game.winGame();
        super.die(game);
    }
}
