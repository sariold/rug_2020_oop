package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.controller.button.EndTurnButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

/**
 * Main class to call start game
 */
public class Main {

    /**
     * Main method
     *
     * @param args Args
     */
    public static void main(String[] args) {
        MagicStoneGame magicStoneGame = new MagicStoneGame();
        EndTurnButton endTurnButton = new EndTurnButton(magicStoneGame);
        MagicStoneFrame frame = new MagicStoneFrame(magicStoneGame, endTurnButton);
        magicStoneGame.setFrame(frame);
        magicStoneGame.startGame(magicStoneGame.getBattlefield(), frame, endTurnButton);
    }
}
