package nl.rug.oop.grapheditor.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.util.LoadGraph;

import javax.swing.undo.UndoManager;
import java.util.ArrayList;
import java.util.Observable;

/**
 * A graph model keeps track of the nodes and edges in the graph
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GraphModel extends Observable {

    private int[] minimumSize;
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private GraphComponent selected;
    private Node copy;
    private CursorCoords connectorCursor;
    private boolean dragging;
    private boolean resizing;
    private boolean loading;
    private UndoManager undoManager;


    /**
     * Create new graph model with given nodes and edges
     * @param nodes Nodes
     * @param edges Edges
     */
    public GraphModel(ArrayList<Node> nodes, ArrayList<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
        selected = null;
        copy = null;
        this.connectorCursor = new CursorCoords();
        this.undoManager = new UndoManager();
    }

    /**
     * Create new graph model from a file
     * @param filePath File Path
     */
    public GraphModel(String filePath) {
        this(new ArrayList<>(), new ArrayList<>());
        LoadGraph loadGraph = new LoadGraph(this, null);
        loadGraph.loadFile(filePath);
        minimumSize = loadGraph.getFrameSize();
    }

    /**
     * Create new graph model with empty edges and nodes
     */
    public GraphModel() {
        this(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Set loading boolean
     * @param loading is it loading?
     */
    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyUpdate();
    }

    /**
     * Set dragging boolean
     * @param dragging boolean
     */
    public void setDragging(boolean dragging) {
        this.dragging = dragging;
        notifyUpdate();
    }

    /**
     * Set resizing boolean
     * @param resizing boolean
     */
    public void setResizing(boolean resizing) {
        this.resizing = resizing;
        notifyUpdate();
    }

    /**
     * Check if a graph component is selected
     * @return boolean selected
     */
    public boolean isSelected() {
        notifyUpdate();
        return this.getSelected() != null;
    }

    /**
     * Set selected
     * @param selected graph component
     */
    public void setSelected(GraphComponent selected) {
        this.selected = selected;
        notifyUpdate();
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
        Edge edgeFromIndex = this.edges.get(edge);
        edgeFromIndex.getStart().getEdges().remove(edgeFromIndex);
        edgeFromIndex.getEnd().getEdges().remove(edgeFromIndex);
        this.edges.remove(edge);
        notifyUpdate();
    }

    /**
     * Remove an edge from the graph
     * @param edge Edge
     */
    public void removeEdge(Edge edge) {
        edge.getStart().getEdges().remove(edge);
        edge.getEnd().getEdges().remove(edge);
        this.edges.remove(edge);
        notifyUpdate();
    }

    /**
     * Add a node to the graph
     * @param node Node
     */
    public void addNode(Node node) {
        this.nodes.add(node);
        node.setNodeIndex(nodes.size() -1);
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
        this.selected = null;
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
        this.selected = null;
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
