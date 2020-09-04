package nl.rug.oop.cardgame;

import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

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
        MainMenu mainMenu = new MainMenu();
        MagicStoneFrame frame = new MagicStoneFrame(mainMenu);
        mainMenu.getGame().startGame();
    }
}
