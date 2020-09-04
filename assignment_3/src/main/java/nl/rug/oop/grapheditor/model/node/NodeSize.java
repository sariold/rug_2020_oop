package nl.rug.oop.grapheditor.model.node;

import lombok.Data;

/**
 * Class to store height and width of a node
 */
@Data
public class NodeSize {

    private int sizeX;
    private int sizeY;

    /**
     * Create a new NodeSize object
     * @param sizeX sizeX
     * @param sizeY sizeY
     */
    public NodeSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

}
