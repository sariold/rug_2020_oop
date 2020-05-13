package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.hero.Hero;

/**
 * Start Game
 */
public class StartGame {

    /**
     * Creates a new Battlefield and starts the game
     */
    public StartGame() {
        Battlefield battlefield = new Battlefield();
        startGame(battlefield);
    }

    /**
     * Starts the actual turn based game
     * @param battlefield Playing board
     */
    public void startGame(Battlefield battlefield) {
       turnRotation(battlefield);
    }

    /**
     * Rotates the turns
     * @param battlefield Playing board
     */
    public void turnRotation(Battlefield battlefield) {
        Hero player = battlefield.getPlayer();
        Hero ai = battlefield.getAi();
        boolean start = true;
        for(int i = 1; start; i++) {
            if(battlefield.isPlayerDead(player)) {
                System.out.println("GAME OVER!");
                start = false;
            }
            System.out.println();
            System.out.println("It's turn number " + ((i+(i%2))/2));
            if(i % 2 == 1)  {
                resetUsedCreatures(player);
                battlefield.showBattlefield();
                battlefield.incMana(player);
                player.setMana(player.getMaxMana());
                player.takeTurn(battlefield);
            }
            else {
                resetUsedCreatures(ai);
                battlefield.incMana(ai);
                ai.setMana(ai.getMaxMana());
                ai.takeTurn(battlefield);
                battlefield.setPlayerTurn(true);
            }
        }
    }

    /**
     * Resets the param used for each played creature
     * @param hero Hero
     */
    public void resetUsedCreatures(Hero hero) {
        for(int i = hero.getPlayedCreatures().size() - 1; i >= 0; i--) {
            hero.getPlayedCreatures().get(i).setUsed(false);
        }
    }
}
