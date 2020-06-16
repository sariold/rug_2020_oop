package nl.rug.oop.grapheditor.util;

import nl.rug.oop.grapheditor.controller.menu.ResizeDialogue;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.view.frame.MainFrame;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Load Graph
 */
public class LoadGraph {

    private final GraphModel graphModel;
    private MainFrame frame;

    /**
     * Load a graph from a file
     */
    public LoadGraph(GraphModel graphModel, MainFrame frame) {
        this.frame = frame;
        this.graphModel = graphModel;
    }

    /**
     * Load file
     * @param fileName File Name
     */
    public GraphModel loadFile(String fileName) {
        graphModel.getNodes().clear();
        graphModel.getEdges().clear();
        try {
            fileReader(fileName);
            ResizeDialogue.loadingMessage(graphModel, frame);
        } catch (IOException e) {
            System.out.println("Cannot read from said file!");
        }
        graphModel.setLoading(false);
        graphModel.setSelected(null);
        return this.graphModel;
    }

    /**
     * File Reader
     * @param fileName File Name
     * @throws IOException input output exception
     */
    private void fileReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currentLine = reader.readLine();
        if(currentLine.length() != 0) {
            String [] graphInfo = currentLine.split(" ");
            int nodes = Integer.parseInt(graphInfo[0]);
            int edges = Integer.parseInt(graphInfo[1]);
            while(currentLine.length() != 0) {
                currentLine = reader.readLine();
                if(currentLine == null) break;
                if(nodes > 0) {
                    createNodes(currentLine);
                    nodes--;
                } else createEdges(currentLine);
            }
        }
        reader.close();
    }

    /**
     * Create a node from a string
     * @param nodeInfo Node Info String
     */
    private void createNodes(String nodeInfo) {
        String name;
        String [] node = nodeInfo.split(" ");
        NodeSize nodeSize = new NodeSize(Integer.parseInt(node[2]), Integer.parseInt(node[3]));
        NodeCoords nodeCoords = new NodeCoords(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
        name = node[4];
        System.out.println(node.length);
        if(node.length > 5) {
            int i = 5;
            while(true) {
                name = name + " " + node[i];
                System.out.println(name);
                if(node.length == i + 1) break;
                i++;
            }
        }
        Node newNode = new Node(name, nodeSize, nodeCoords);
        graphModel.addNode(newNode);
    }

    /**
     * Create an edge from a string
     * @param edgeInfo Edge Info String
     */
    private void createEdges(String edgeInfo) {
        String [] edge = edgeInfo.split(" ");
        Edge newEdge = new Edge(graphModel.getNodes().get(Integer.parseInt(edge[0])), graphModel.getNodes().get(Integer.parseInt(edge[1])));
        graphModel.addEdge(newEdge);
    }
}
