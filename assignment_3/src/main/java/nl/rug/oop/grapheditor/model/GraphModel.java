package nl.rug.oop.grapheditor.model;

import lombok.Data;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;

import java.util.ArrayList;
import java.util.Observable;

/**
 * A graph model keeps track of the nodes and edges in the graph
 */
@Data
public class GraphModel extends Observable {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    /**
     * Create new graph model with given nodes and edges
     * @param nodes Nodes
     * @param edges Edges
     */
    public GraphModel(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    /**
     * Create new graph model with empty edges and nodes
     */
    public GraphModel() {
        this(new ArrayList<Node>(), new ArrayList<Edge>());
    }

    /**
     * Add an edge to the graph
     * @param edge Edge
     */
    public void addEdge(Edge edge) {
        this.edges.add(edge);
        notifyUpdate();
    }

    /**
     * Remove an edge at the indicated index from the graph
     * @param edge Index of edge
     */
    public void removeEdgeAtIndex(int edge) {
        this.edges.remove(edge);
        notifyUpdate();
    }

    /**
     * Remove an edge from the graph
     * @param edge Edge
     */
    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
        notifyUpdate();
    }

    /**
     * Add a node to the graph
     * @param node Node
     */
    public void addNode(Node node) {
        this.nodes.add(node);
        notifyUpdate();
    }

    /**
     * Remove a Node at a given index and all edges connecting the node from the graph
     * @param node Index of node
     */
    public void removeNodeAtIndex(int node) {
        Node toRemove = this.nodes.get(node);
        for (int i = this.edges.size() - 1; i >= 0; i--) {
            Edge e = this.edges.get(i);
            if (e.connects(toRemove)) {
                removeEdgeAtIndex(i);
            }
        }
        this.nodes.remove(node);
        notifyUpdate();
    }

    /**
     * Remove a Node and all edges connecting the node from the graph
     * @param node Node
     */
    public void removeNode(Node node) {
        for (int i = this.edges.size() - 1; i >= 0; i--) {
            Edge e = this.edges.get(i);
            if (e.connects(node)) {
                removeEdgeAtIndex(i);
            }
        }
        this.nodes.remove(node);
        notifyUpdate();
    }

    /**
     * Notify observers of a change in this object
     */
    public void notifyUpdate() {
        setChanged();
        notifyObservers();
    }

}
