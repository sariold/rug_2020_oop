package nl.rug.oop.grapheditor.util;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class to save graph model to .sff file
 */
public class SaveGraph {

    private final GraphModel graphModel;

    /**
     * Create a save graph object
     * @param graphModel Graph Model
     */
    public SaveGraph(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    /**
     * Save file
     * @param fileName File Name
     */
    public void saveFile(String fileName) {
        if(!fileName.contains(".sff")) fileName = fileName + ".sff";
        int nodeAmount = graphModel.getNodes().size();
        int edgeAmount = graphModel.getEdges().size();
        String graphInfo = nodeAmount + " " + edgeAmount + "\n";
        inputText(graphInfo, fileName, false);
        for(int i = 0; i < graphModel.getNodes().size(); i++) {
            Node node = graphModel.getNodes().get(i);
            String nodeInfo = node.getNodeCoords().getCoordX() + " " + node.getNodeCoords().getCoordY() + " " +
                    node.getNodeSize().getSizeX() + " " + node.getNodeSize().getSizeY() + " " +
                    node.getName() + "\n";
            inputText(nodeInfo, fileName, true);
        }
        for(int i = 0; i < graphModel.getEdges().size(); i++) {
            Edge edge = graphModel.getEdges().get(i);
            String edgeInfo = edge.getStart().getNodeIndex() + " " + edge.getEnd().getNodeIndex() + "\n";
            System.out.println(edgeInfo);
            inputText(edgeInfo, fileName, true);
        }
    }

    /**
     * Text string to save
     * @param text Text
     * @param fileName File Name
     * @param append Append?
     */
    private void inputText(String text, String fileName, boolean append) {
        try {
            fileWriter(text, fileName, append);
        } catch (IOException e) {
            System.out.println("Could not write to said file!");
        }
    }

    /**
     * File writer
     * @param text Text string
     * @param fileName File Name
     * @param append Append?
     * @throws IOException input output exception
     */
    private void fileWriter(String text, String fileName, boolean append) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName, append);
        byte[] byteText = text.getBytes();
        fileOutputStream.write(byteText);
        fileOutputStream.close();
    }
}
