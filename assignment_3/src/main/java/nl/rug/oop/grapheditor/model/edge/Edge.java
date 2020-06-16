package nl.rug.oop.grapheditor.model.edge;

import lombok.Data;
import nl.rug.oop.grapheditor.model.GraphComponent;
import nl.rug.oop.grapheditor.model.node.Node;

import java.util.Objects;

/**
 * An edge connecting two nodes
 */
@Data
public class Edge extends GraphComponent {

    private Node start;
    private Node end;

    /**
     * Create a new edge given the nodes to connect
     * @param start Starting node
     * @param end Ending node
     */
    public Edge(Node start, Node end) {
        this.start = start;
        this.end = end;
        start.getEdges().add(this);
        end.getEdges().add(this);
    }

    /**
     * Checks if this edge connects a given node to another one
     * @param node Node
     * @return True if node is being connected by this edge, false otherwise
     */
    public boolean connects(Node node) {
        return (start.equals(node) || end.equals(node));
    }

    /**
     * Check if an edge is equal to given object.
     * @param o Object to compare
     * @return true if object is the same edge, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return (this.start.equals(edge.getStart()) &&
                this.end.equals(edge.getEnd()));

    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    /**
     * Return this Edge as a String.
     * @return string representing this edge.
     */
    @Override
    public String toString() {
        return ("Starting:" + this.start.getName() + "; End:" + this.end.getName());
    }
}
