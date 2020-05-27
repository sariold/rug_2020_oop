package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.controller.ConsoleController;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        GraphModel graphModel = new GraphModel();
        ConsoleView consoleView  = new ConsoleView(graphModel);
        ConsoleController consoleController = new ConsoleController(graphModel);
        while (true) {
            consoleController.showOptions();
            consoleController.executeOption();
        }
    }
}
