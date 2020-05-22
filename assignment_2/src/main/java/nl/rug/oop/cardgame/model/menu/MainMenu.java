package nl.rug.oop.cardgame.model.menu;

import lombok.Data;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.deck.CollectionDeck;

import java.util.Observable;

/**
 * Main Menu from into which the game launches
 */
@Data
public class MainMenu extends Observable {

    private MagicStoneGame game;
    private CollectionDeck collectionDeck;
    private boolean inGame;
    private boolean inCollection;
    private boolean inTutorial;

    /**
     * Create new Main Menu
     */
    public MainMenu() {
        this.game = new MagicStoneGame();
        this.collectionDeck = new CollectionDeck();
        this.inGame = this.inCollection = this.inTutorial = false;
    }

    /**
     * Switches to game from main menu and notifies observers
     */
    public void startGame() {
        this.inGame = true;
        this.inCollection = this.inTutorial = false;
        notifyUpdate();
    }

    /**
     * Switches to card collection from main menu and notifies observers
     */
    public void startCollection() {
        this.inCollection = true;
        this.inGame = this.inTutorial = false;
        notifyUpdate();
    }

    /**
     * Switches to tutorial from main menu and notifies observers
     */
    public void startTutorial() {
        this.inTutorial = true;
        this.inGame = this.inCollection = false;
        notifyUpdate();
    }

    /**
     * Changes the first card shown in the card collection and notifies observers
     */
    public void changeCollection(int dir) {
        if (dir == 0) this.getCollectionDeck().increaseStartingCard();
        else this.getCollectionDeck().decreaseStartingCard();
        notifyUpdate();
    }

    /**
     * Switches to main menu and notifies observers
     */
    public void goBackToMenu() {
        this.inGame = this.inCollection = this.inTutorial = false;
        notifyUpdate();
    }

    /**
     * notifies observers of change
     */
    private void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

}
