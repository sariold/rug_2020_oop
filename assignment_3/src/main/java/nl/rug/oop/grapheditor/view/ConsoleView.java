package nl.rug.oop.grapheditor.view;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;

import java.util.Observable;
import java.util.Observer;

/**
 * View to show a graph model in the console
 * Keeping this class as it can be useful for debugging and using graph model via terminal
 */
public class ConsoleView implements Observer {

    private final GraphModel graphModel;

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
        System.out.println("Amount of nodes: " + graphModel.getNodes().size() +
                " || Amount of edges: " + graphModel.getEdges().size());
        System.out.println("Nodes:");
        int i = 0;
        for (Node n : graphModel.getNodes()) {
            System.out.println("#" + i + " " + n.toString());
            i++;
        }
        System.out.println("Edges:");
        i = 0;
        for (Edge e : graphModel.getEdges()) {
            System.out.println("#" + i + " " + e.toString());
            i++;
        }
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
