package nl.rug.oop.grapheditor.model.node;

import lombok.Data;

import java.util.Objects;

/**
 * A node connected to other nodes by edges.
 */
@Data
public class Node {

    private String name;
    private int[] size;
    private int[] location;

    /**
     * Create a new node with a name, size and location.
     * @param name Name
     * @param size Size
     * @param location Location
     */
    public Node(String name, int[] size, int[] location) {
        this.name = name;
        this.size = size;
        this.location = location;
    }

    /**
     * Create a node with given name and standard size and location
     * @param name Name
     */
    public Node(String name) {
        this(name,  new int[]{10,10}, new int[]{0,0});
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
                this.size[0] == node.getSize()[0] &&
                this.size[1] == node.getSize()[1] &&
                this.location[0] == node.getLocation()[0] &&
                this.location[1] == node.getLocation()[1] );

    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, size, location);
    }

    /**
     * Return this Node as a String.
     * @return string representing this node.
     */
    @Override
    public String toString() {
        return ("Name:" + this.name + "; Size:" + this.size[0] + ", " + this.size[1] +
                "; Location:" + this.location[0] + ", " + this.location[1]);
    }
}
