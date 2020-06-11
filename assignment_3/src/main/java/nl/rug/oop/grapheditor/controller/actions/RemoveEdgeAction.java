package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Action to remove an edge
 */
public class RemoveEdgeAction extends AbstractUndoableEdit {

    private GraphModel graphModel;
    private Edge edge;

    /**
     * Create a new remove edge action
     * @param graphModel Graph Model
     * @param edge Edge
     */
    public RemoveEdgeAction(GraphModel graphModel, Edge edge) {
        this.edge = edge;
        this.graphModel = graphModel;
    }

    /**
     * Add the edge again
     * @throws CannotUndoException
     */
    @Override
    public void undo() throws CannotUndoException {
        graphModel.addEdge(edge);
    }

    /**
     * Remove the edge
     * @throws CannotRedoException
     */
    @Override
    public void redo() throws CannotRedoException {
        graphModel.removeEdge(edge);
    }
}
