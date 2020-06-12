package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;

/**
 * Undoable Edit to remove a Node
 */
public class RemoveNodeAction extends AbstractUndoableEdit {

    private GraphModel graphModel;
    private Node node;
    private ArrayList<Edge> edges;

    /**
     * Create a new Action to create a node
     * @param graphModel Graph Model
     * @param node Node
     */
    public RemoveNodeAction(GraphModel graphModel, Node node) {
        this.graphModel = graphModel;
        this.node = node;
        this.edges = new ArrayList<Edge>();
        for (Edge e: graphModel.getEdges()) {
            if (e.getStart().equals(node)||e.getEnd().equals(node)) edges.add(e);
        }
    }

    /**
     * Remove the Node from the graph Model
     * @throws CannotUndoException cannot undo
     */
    @Override
    public void undo() throws CannotUndoException {
        graphModel.addNode(node);
        for (Edge e: this.edges) {
            graphModel.addEdge(e);
        }
    }

    /**
     * Add the node to the graph model
     * @throws CannotRedoException cannot redo
     */
    @Override
    public void redo() throws CannotRedoException {
        graphModel.removeNode(node);
    }
}
