package nl.rug.oop.grapheditor.model.node;

import lombok.Data;

/**
 * Class to store a Node's x and y coordinates
 */
@Data
public class NodeCoords {

    private int coordX;
    private int coordY;

    /**
     * Constructor to create node coordinates
     * @param coordX coordX
     * @param coordY coordY
     */
    public NodeCoords(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }
}
