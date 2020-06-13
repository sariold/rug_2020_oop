package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Undoable Edit to create a Node
 */
public class CreateNodeAction extends AbstractUndoableEdit {

    private final GraphModel graphModel;
    private final Node node;

    /**
     * Create a new Action to create a node
     * @param graphModel Graph Model
     * @param node Node
     */
    public CreateNodeAction(GraphModel graphModel, Node node) {
        this.graphModel = graphModel;
        this.node = node;
        graphModel.addNode(node);
    }

    /**
     * Remove the Node from the graph Model
     * @throws CannotUndoException cannot undo
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        graphModel.removeNode(node);
    }

    /**
     * Add the node to the graph model
     * @throws CannotRedoException cannot redo
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        graphModel.addNode(node);
    }
}
