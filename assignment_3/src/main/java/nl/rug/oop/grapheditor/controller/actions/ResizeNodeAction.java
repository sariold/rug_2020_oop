package nl.rug.oop.grapheditor.controller.actions;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;

import javax.swing.undo.AbstractUndoableEdit;

public class ResizeNodeAction extends AbstractUndoableEdit {

    private GraphModel graphModel;
    private Node node;

    public ResizeNodeAction(GraphModel graphModel) {
        this.graphModel = graphModel;
        this.node = null;
    }
}
