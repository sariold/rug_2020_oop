package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.MagicStoneFrame;

public class Main {

    public static void main(String[] args) {
        MagicStoneGame magicStoneGame = new MagicStoneGame();
        new MagicStoneFrame(magicStoneGame);
    }
}
