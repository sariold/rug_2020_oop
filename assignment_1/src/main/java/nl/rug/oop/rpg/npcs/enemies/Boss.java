package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.game.Game;

import java.io.Serializable;

/**
 * Abstract class Boss defeating this enemy wins the game
 */
public abstract class Boss extends Enemy implements Serializable {

    private static final long serialVersionUID = 16L;

    /**
     * Constructor for a boss
     * @param description Description
     * @param name Name
     * @param hitPoints Hitpoints
     * @param attackPoints Attackpoints
     * @param goldValue Goldvalue
     */
    public Boss(String description, String name, int hitPoints, int attackPoints, int goldValue){
        super(description, name, hitPoints, attackPoints, goldValue);
    }

    /**
     * If a boss dies the game is won
     * @param game Game
     */
    @Override
    public void die(Game game) {
        game.winGame();
        super.die(game);
    }
}
