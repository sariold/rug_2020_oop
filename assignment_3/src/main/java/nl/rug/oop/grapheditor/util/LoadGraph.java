package nl.rug.oop.grapheditor.util;

import nl.rug.oop.grapheditor.model.GraphModel;

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
    public void loadFile(String fileName) {
        try {
            fileReader(fileName);
        } catch (IOException e) {
            System.out.println("Cannot read from said file!");
        }
    }

    /**
     * File Reader
     * @param fileName File Name
     * @throws IOException
     */
    private void fileReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currentLine = reader.readLine();
        String [] graphInfo = currentLine.split(" ");
        nodes = Integer.parseInt(graphInfo[0]);
        edges = Integer.parseInt(graphInfo[1]);
        System.out.println(nodes + " " + edges);
//        while(currentLine != null) {
//
//        }
        reader.close();
    }

    private void createNodes(String nodeInfo) {

    }

    private void createEdges(String edgeInfo) {

    }
}
