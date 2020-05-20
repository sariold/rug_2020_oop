package nl.rug.oop.cardgame.model.menu;

import lombok.Data;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.deck.CollectionDeck;

import java.util.Observable;

@Data
public class MainMenu extends Observable {

    private MagicStoneGame game;
    private CollectionDeck collectionDeck;
    private boolean inGame;
    private boolean inCollection;
    private boolean inTutorial;


    public MainMenu() {
        this.game = new MagicStoneGame();
        this.collectionDeck = new CollectionDeck();
        this.inGame = this.inCollection = this.inTutorial = false;
    }

    public void startGame() {
        this.inGame = true;
        this.inCollection = this.inTutorial = false;
        notifyUpdate();
    }

    public void startCollection() {
        this.inCollection = true;
        this.inGame = this.inTutorial = false;
        notifyUpdate();
    }

    public void changeCollection(int dir) {
        if (dir == 0) this.getCollectionDeck().increaseStartingCard();
        else this.getCollectionDeck().decreaseStartingCard();
        notifyUpdate();
    }

    public void goBackToMenu() {
        this.inGame = this.inCollection = this.inTutorial = false;
        notifyUpdate();
    }

    private void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

}
