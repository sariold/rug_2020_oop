package nl.rug.oop.grapheditor.model.node;

import lombok.Data;

import java.util.Objects;

/**
 * A node connected to other nodes by edges.
 */
@Data
public class Node {

    private String name;
    private NodeSize nodeSize;
    private NodeCoords nodeCoords;
    private int nodeIndex;

    /**
     * Create a new node with a name, size and location.
     * @param name Name
     * @param nodeSize NodeSize
     * @param nodeCoords NodeeCoords
     */
    public Node(String name, NodeSize nodeSize, NodeCoords nodeCoords) {
        this.name = name;
        this.nodeSize = nodeSize;
        this.nodeCoords = nodeCoords;
    }

    /**
     * Create a node with given name and standard size and location
     * @param name Name
     */
    public Node(String name) {
        this(name,  new NodeSize(10, 10), new NodeCoords(0, 0));
    }

    /**
     * Create a new node with standard name, size and location.
     */
    public Node() {
        this("Name");
    }

    /**
     * Check if a node is equal to given object.
     * @param o Object to compare
     * @return true if object is the same node, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return (this.name.equals(node.getName()) &&
                this.nodeSize.getSizeX() == node.getNodeSize().getSizeX() &&
                this.nodeSize.getSizeY() == node.getNodeSize().getSizeY() &&
                this.nodeCoords.getCoordX() == node.getNodeCoords().getCoordX() &&
                this.nodeCoords.getCoordY() == node.getNodeCoords().getCoordY() );

    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, nodeSize, nodeCoords);
    }

    /**
     * Return this Node as a String.
     * @return string representing this node.
     */
    @Override
    public String toString() {
        return ("Name:" + this.name + "; Size:" + this.nodeSize.getSizeX() + ", " + this.nodeSize.getSizeY() +
                "; Location:" + this.nodeCoords.getCoordX() + ", " + this.nodeCoords.getCoordY());
    }
}
