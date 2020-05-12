package nl.rug.oop.cardgame;

import lombok.Data;

/**
 * Battlefield Game board
 */
@Data
public class Battlefield {

    private Hero player;
    private Hero ai;
    private boolean playerTurn = true;

    /**
     * Create a new battlefield
     */
    public Battlefield() {
        this.player = new Hero("Diego", 10, 0, 10);
        this.ai = new Hero("Felix", 10, 0, 10);
    }

    /**
     * Increase mana each turn
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        if(hero.getMana() < hero.getMaxMana()) hero.setMana(hero.getMana() + 1);
    }


}
