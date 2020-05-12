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
        this.player = new Hero("Diego", 100, 0, 0, 1);
        this.ai = new AIHero("Felix", 10, 0, 0, 1);
    }

    /**
     * Increase mana each turn
     * @param hero Hero
     */
    public void incMana(Hero hero) {
        if(hero.getMana() < DefaultStats.MAX_MANA) hero.setMaxMana(hero.getMaxMana() + 1);
    }

    /**
     * Show the creatures on the battlefield for AI and Player
     */
    public void showBattlefield() {
        System.out.println("ENEMY HEALTH: " + this.getAi().getHeroHealth());
        System.out.println("ENEMY FIELD:");
        for(int i = 0; i < ai.getPlayedCreatures().size(); i++) {
            System.out.println(i + ") " + ai.getPlayedCreatures().get(i).getName() + ": Health = " +
                    ai.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                    ai.getPlayedCreatures().get(i).getAttack() + " : Tapped = "
                    + ai.getPlayedCreatures().get(i).isUsed());
        }
        System.out.println();
        System.out.println("YOUR HEALTH: " + this.getPlayer().getHeroHealth());
        System.out.println("YOUR FIELD:");
        for(int i = 0; i < player.getPlayedCreatures().size(); i++) {
            System.out.println(i + ") " + player.getPlayedCreatures().get(i).getName() + ": Health = " +
                    player.getPlayedCreatures().get(i).getHealth() + ": Attack = " +
                    player.getPlayedCreatures().get(i).getAttack() + " : Tapped = "
                    + player.getPlayedCreatures().get(i).isUsed());
        }
        System.out.println();
    }

}
