package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;

import java.util.Observable;
import java.util.Observer;

/**
 * View to show a graph model in the console
 */
public class ConsoleView implements Observer {

    GraphModel graphModel;

    /**
     * Create a new console view for a graph model
     * @param g Graph Model
     */
    public ConsoleView(GraphModel g) {
        this.graphModel = g;
        g.addObserver(this);
    }

    /**
     * Prints the current status of the graph model to the console
     */
    private void printGraphModel() {

    }

    /**
     * This method is called whenever the observed object is changed.
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        printGraphModel();
    }
}
