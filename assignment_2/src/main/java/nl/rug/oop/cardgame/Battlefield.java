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
        this.player = new Hero("Diego", 10, 0, 0);
        this.ai = new Hero("Felix", 10, 0, 0);
    }

    /**
     * Increase mana each turn
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        if(hero.getMana() < DefaultStats.MAX_MANA) hero.setMaxMana(hero.getMaxMana() + 1);
    }

    public void showBattlefield() {
        for(int i = 0; i < ai.getPlayedCreatures().size(); i++) {
            System.out.println(i + ") " + ai.getPlayedCreatures().get(i).getName() + ": Health = " +
                    ai.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                    ai.getPlayedCreatures().get(i).getAttack());
        }
        for(int i = 0; i < player.getPlayedCreatures().size(); i++) {
            System.out.println(i + ") " + player.getPlayedCreatures().get(i).getName() + ": Health = " +
                    player.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                    player.getPlayedCreatures().get(i).getAttack());
        }
    }

}
