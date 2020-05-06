package nl.rug.oop.rpg.npcs.enemies;

import nl.rug.oop.rpg.Game;

import java.io.Serializable;

public abstract class Boss extends Enemy implements Serializable {

    private static final long serialVersionUID = 16L;

    public Boss(String description, String name, int hitPoints, int attackPoints, int goldValue){
        super(description, name, hitPoints, attackPoints, goldValue);
    }

    @Override
    public void die(Game game) {
        game.winGame();
        super.die(game);
    }
}
