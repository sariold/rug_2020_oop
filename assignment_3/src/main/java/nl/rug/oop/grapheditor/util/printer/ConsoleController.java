//package nl.rug.oop.grapheditor.util.printer;
//
//import nl.rug.oop.grapheditor.model.GraphModel;
//import nl.rug.oop.grapheditor.model.edge.Edge;
//import nl.rug.oop.grapheditor.model.node.Node;
//import nl.rug.oop.grapheditor.model.node.NodeCoords;
//import nl.rug.oop.grapheditor.model.node.NodeSize;
//import nl.rug.oop.grapheditor.util.LoadGraph;
//import nl.rug.oop.grapheditor.util.SaveGraph;
//
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
///**
// * A controller to edit a Graph through the console, keeping because this can be useful for debugging
// */
//public class ConsoleController {
//
//    GraphModel graphModel;
//    Scanner scanner;
//    SaveGraph saveGraph;
//    LoadGraph loadGraph;
//    JFileChooser jFileChooser;
//
//    /**
//     * Create a new controller using the console as input
//     * @param graphModel Graph model
//     */
//    public ConsoleController(GraphModel graphModel) {
//        this.graphModel = graphModel;
//        this.scanner = new Scanner(System.in);
//        this.saveGraph = new SaveGraph(this.graphModel);
//        this.loadGraph = new LoadGraph(graphModel);
//        this.jFileChooser = new JFileChooser();
//        jFileChooser.setAcceptAllFileFilterUsed(false);
//        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .sff files", "sff");
//        jFileChooser.addChoosableFileFilter(restrict);
//    }
//
//    /**
//     * Show options to edit the graph model
//     */
//    public void showOptions() {
//        System.out.println("What to do with the graph?");
//        System.out.println("0) Add Edge");
//        System.out.println("1) Remove Edge");
//        System.out.println("2) Add Node");
//        System.out.println("3) Remove Node");
//        System.out.println("4) Save to File");
//        System.out.println("5) Load File");
//        System.out.println("6) Print");
//        System.out.println("7) End Program");
//    }
//
//    /**
//     * Executes the option that is chosen to edit the graph
//     */
//    public void executeOption() {
//        int option = -1;
//        while (true) {
//            try {
//                option = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        switch (option) {
//            case 0:
//                addEdge();
//                break;
//            case 1:
//                removeEdge();
//                break;
//            case 2:
//                addNode();
//                break;
//            case 3:
//                removeNode();
//                break;
//            case 4:
//                int save = jFileChooser.showSaveDialog(null);
//                if(save == JFileChooser.APPROVE_OPTION) {
//                    saveGraph.saveFile(jFileChooser.getSelectedFile().getAbsolutePath());
//                }
//                break;
//            case 5:
//                int load = jFileChooser.showOpenDialog(null);
//                if(load == JFileChooser.APPROVE_OPTION) {
//                    graphModel = loadGraph.loadFile(jFileChooser.getSelectedFile().getAbsolutePath());
//                }
//                break;
//            case 6:
//                graphModel.notifyUpdate();
//                break;
//            case 7:
//                System.exit(0);
//                break;
//        }
//    }
//
//    /**
//     * Take input from the user and create the specified edge
//     */
//    private void addEdge() {
//        if (graphModel.getNodes().size() < 1) {
//            System.out.println("no nodes in the graph");
//            return;
//        }
//        System.out.println("What nodes to connect?");
//        for (int i = 0; i < graphModel.getNodes().size(); i++) {
//            System.out.println(i + ")");
//        }
//        int start;
//        while (true) {
//            try {
//                start = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        System.out.println("And?");
//        int end;
//        while (true) {
//            try {
//                end = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        graphModel.addEdge(new Edge(graphModel.getNodes().get(start), graphModel.getNodes().get(end)));
//    }
//
//    /**
//     * Take input from the user and remove the specified edge
//     */
//    private void removeEdge() {
//        if (graphModel.getEdges().size() < 1) {
//            System.out.println("no edges in the graph");
//            return;
//        }
//        System.out.println("What edge to remove?");
//        for (int i = 0; i < graphModel.getEdges().size(); i++) {
//            System.out.println(i + ")");
//        }
//        int remove;
//        while (true) {
//            try {
//                remove = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        graphModel.removeEdgeAtIndex(remove);
//    }
//
//    /**
//     * Take user input and add the create node to the graph
//     */
//    private void addNode() {
//        System.out.println("What name should the Node have?");
//        String name = scanner.nextLine();
//        System.out.println("What length should the Node have?");
//        int length;
//        while (true) {
//            try {
//                length = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        System.out.println("What height should the Node have?");
//        int height;
//        while (true) {
//            try {
//                height = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        System.out.println("What x-position should the Node have?");
//        int x;
//        while (true) {
//            try {
//                x = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        System.out.println("What y-position should the Node have?");
//        int y;
//        while (true) {
//            try {
//                y = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        graphModel.addNode(new Node(name, new NodeSize(length, height), new NodeCoords(x, y)));
//    }
//
//    /**
//     * Take user input and remove the specified node
//     */
//    private void removeNode() {
//        if (graphModel.getNodes().size() < 1) {
//            System.out.println("no nodes in the graph");
//            return;
//        }
//        System.out.println("What node to remove?");
//        for (int i = 0; i < graphModel.getNodes().size(); i++) {
//            System.out.println(i + ")");
//        }
//        int remove;
//        while (true) {
//            try {
//                remove = scanner.nextInt();
//                scanner.nextLine();
//                break;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input!");
//                scanner.nextLine();
//            }
//        }
//        graphModel.removeNodeAtIndex(remove);
//    }
//}
