package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

/**
 * Main class to call start game
 */
public class Main {

    /**
     * Main method
     * @param args Args
     */
    public static void main(String[] args) {
        MagicStoneGame magicStoneGame = new MagicStoneGame();
        new MagicStoneFrame(magicStoneGame);

//        StartGame startGame = new StartGame();

    }
}
