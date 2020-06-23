package nl.rug.oop.grapheditor;

import nl.rug.oop.grapheditor.controller.ConsoleController;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.ConsoleView;
import nl.rug.oop.grapheditor.view.frame.MainFrame;

/**
 * Graph editor main class
 */
public class GraphEditor {

    /**
     * Main function
     * @param args command line args
     */
    public static void main(String[] args) {
        GraphModel graphModel;
        System.out.println(args.length);
        if (args.length == 1) {
            System.out.println("Loading from file");
            graphModel = new GraphModel(args[0]);
        } else {
            System.out.println("Normal");
            graphModel = new GraphModel();
        }
        new MainFrame(graphModel);
        new ConsoleView(graphModel);
        ConsoleController consoleController = new ConsoleController(graphModel);
        while (true) {
            consoleController.showOptions();
            consoleController.executeOption();
        }
    }
}