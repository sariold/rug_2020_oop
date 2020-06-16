package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

/**
 * Action that handles the edit of a Node
 */
public class EditNodeAction extends AbstractUndoableEdit {

    private final Node node;
    private final String newName;
    private final NodeCoords newCoords;
    private final NodeSize newSize;
    private final String oldName;
    private NodeCoords oldCoords;
    private NodeSize oldSize;

    /**
     * Create a Edit node action given a new name new coordinates and a new size
     * @param node Node
     * @param newName Name
     * @param newCoords Coordinates
     * @param newSize Size
     */
    public EditNodeAction(Node node, String newName, NodeCoords newCoords, NodeSize newSize) {
        this.node = node;
        this.newName = newName;
        this.newCoords = newCoords;
        this.newSize = newSize;
        this.oldName = node.getName();
        this.oldCoords = node.getNodeCoords();
        this.oldSize = node.getNodeSize();
        node.setName(newName);
        node.setNodeCoords(newCoords);
        node.setNodeSize(newSize);
    }

    /**
     * Create a edit node action given new coordinates
     * @param node Node
     * @param newCoords New Coordinates
     * @param oldCoords Old Coordinates
     */
    public EditNodeAction(Node node, NodeCoords newCoords, NodeCoords oldCoords, NodeSize old) {
        this(node, node.getName(), newCoords, node.getNodeSize());
        System.out.println("old size:" + old.toString() + " new size:" + newSize.toString());
        this.oldCoords = oldCoords;
        this.oldSize = old;
//        this.newSize = node.getNodeSize();
        node.setName(newName);
        node.setNodeCoords(newCoords);
        node.setNodeSize(newSize);
    }

    /**
     * Change the Node back to before the edit
     * @throws CannotUndoException cannot undo
     */
    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        node.setName(oldName);
        node.setNodeCoords(oldCoords);
        node.setNodeSize(oldSize);
    }

    /**
     * Edit the node
     * @throws CannotRedoException cannot redo
     */
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        node.setName(newName);
        node.setNodeCoords(newCoords);
        node.setNodeSize(newSize);
    }
}
