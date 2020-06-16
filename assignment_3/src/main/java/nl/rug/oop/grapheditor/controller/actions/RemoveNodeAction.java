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

    private final GraphModel graphModel;
    private final Node node;
    private final ArrayList<Edge> edges;

    /**
     * Create a new Action to create a node
     * @param graphModel Graph Model
     * @param node Node
     */
    public RemoveNodeAction(GraphModel graphModel, Node node) {
        this.graphModel = graphModel;
        this.node = node;
        this.edges = new ArrayList<>();
        for (Edge e: graphModel.getEdges()) {
            if (e.getStart().equals(node)|| e.getEnd().equals(node)) edges.add(e);
        }
        graphModel.removeNode(node);
    }

    /**
     * Remove the Node from the graph Model
     * @throws CannotUndoException cannot undo
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
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
        super.redo();
        graphModel.removeNode(node);
    }
}
