package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.card.Card;

import java.util.Scanner;

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
        for(int i = 0; start; i++) {
            System.out.println("It's turn number " + (i -(i*(i%2))));
            battlefield.showBattlefield();
            if(i % 2 == 0)  {
                battlefield.incMana(player);
                player.setMana(player.getMaxMana());
                player.takeTurn(battlefield);
            }
            else {
                battlefield.incMana(ai);
                ai.setMana(ai.getMaxMana());
                ai.takeTurn(battlefield);
                battlefield.setPlayerTurn(true);
            }
        }
    }
}
