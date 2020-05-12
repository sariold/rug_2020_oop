package nl.rug.oop.cardgame.model;

import java.util.Observable;
import java.util.Observer;

public class MagicStoneGame extends Observable implements Observer {

    public MagicStoneGame() {
    }

    @Override
    public void update(Observable observable, Object message) {
        setChanged();
        notifyObservers();
    }
}
