package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.controller.actions.RemoveEdgeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Remove Edge Action
 */
public class RemoveEdgeAL implements ActionListener {

    private final GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public RemoveEdgeAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graphModel.getSelected() instanceof Edge) {
            RemoveEdgeAction removeEdgeAction = new RemoveEdgeAction(graphModel, (Edge)graphModel.getSelected());
            graphModel.getUndoManager().addEdit(removeEdgeAction);
        }
    }
}
