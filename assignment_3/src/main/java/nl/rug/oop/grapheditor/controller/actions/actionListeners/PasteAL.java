package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.controller.actions.CreateNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Paste Action
 */
public class PasteAL implements ActionListener {

    private final GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public PasteAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Node newNode = graphModel.getCopy().copy();
        newNode.setNodeCoords(new NodeCoords(graphModel.getConnectorCursor().getX(), graphModel.getConnectorCursor().getY()));
        CreateNodeAction createNodeAction = new CreateNodeAction(graphModel, newNode);
        graphModel.getUndoManager().addEdit(createNodeAction);
    }
}
