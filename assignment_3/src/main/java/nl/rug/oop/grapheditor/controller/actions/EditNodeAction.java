package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.controller.menu.EditNodeMenu;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class EditNodeAction extends AbstractUndoableEdit {

    private Node node;
    private String newName;
    private NodeCoords newCoords;
    private NodeSize newSize;
    private String oldName;
    private NodeCoords oldCoords;
    private NodeSize oldSize;

    public EditNodeAction(Node node, String newName, NodeCoords newCoords, NodeSize newSize) {
        this.node = node;
        this.newName = newName;
        this.newCoords = newCoords;
        this.newSize = newSize;
        this.oldName = node.getName();
        this.oldCoords = node.getNodeCoords();
        this.oldSize = node.getNodeSize();
    }

    public EditNodeAction(Node node, NodeCoords newCoords, NodeCoords oldCoords) {
        this(node, node.getName(), newCoords, node.getNodeSize());
        this.oldCoords = oldCoords;
    }

    @Override
    public void undo() throws CannotUndoException {
        node.setName(oldName);
        node.setNodeCoords(oldCoords);
        node.setNodeSize(oldSize);
    }

    @Override
    public void redo() throws CannotRedoException {
        node.setName(newName);
        node.setNodeCoords(newCoords);
        node.setNodeSize(newSize);
    }
}
