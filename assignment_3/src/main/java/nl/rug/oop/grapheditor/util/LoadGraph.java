package nl.rug.oop.grapheditor.util;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Load Graph
 */
public class LoadGraph {

    private GraphModel graphModel;
    private int nodes;
    private int edges;

    /**
     * Load a graph from a file
     */
    public LoadGraph() {
        this.graphModel = new GraphModel();
    }

    /**
     * Load file
     * @param fileName File Name
     */
    public GraphModel loadFile(String fileName) {
        try {
            fileReader(fileName);
        } catch (IOException e) {
            System.out.println("Cannot read from said file!");
        }
        return this.graphModel;
    }

    /**
     * File Reader
     * @param fileName File Name
     * @throws IOException
     */
    private void fileReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currentLine = reader.readLine();
        if(currentLine.length() != 0) {
            String [] graphInfo = currentLine.split(" ");
            nodes = Integer.parseInt(graphInfo[0]);
            edges = Integer.parseInt(graphInfo[1]);
            while(currentLine != null && currentLine.length() != 0) {
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
        String [] node = nodeInfo.split(" ");
        NodeSize nodeSize = new NodeSize(Integer.parseInt(node[2]), Integer.parseInt(node[3]));
        NodeCoords nodeCoords = new NodeCoords(Integer.parseInt(node[0]), Integer.parseInt(node[1]));
        Node newNode = new Node(node[4], nodeSize, nodeCoords);
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
