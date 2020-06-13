package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Action to remove an edge
 */
public class CreateEdgeAction extends AbstractUndoableEdit {

    private final GraphModel graphModel;
    private final Edge edge;

    /**
     * Create a new add edge action
     * @param graphModel Graph Model
     * @param edge Edge
     */
    public CreateEdgeAction(GraphModel graphModel, Edge edge) {
        this.edge = edge;
        this.graphModel = graphModel;
        graphModel.addEdge(edge);
    }

    /**
     * Remove the edge again
     * @throws CannotUndoException cannot undo
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graphModel.removeEdge(edge);
    }

    /**
     * Add the edge
     * @throws CannotRedoException cannot redo
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graphModel.addEdge(edge);
    }
}
