package nl.rug.oop.rpg;

/**
 * Main method which calls the startGame class to start the game
 */
public class Main {

    /**
     * Starts the game by calling a startgame method
     * @param args
     */
    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        startGame.startGameOption();
    }
}
