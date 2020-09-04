package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.controller.menu.EditNodeMenu;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Edit Node Action
 */
public class EditNodeAL implements ActionListener {

    private final GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public EditNodeAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.graphModel = graphModel;
        if(jMenuItem != null) jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (graphModel.getSelected() instanceof Node) {
            new EditNodeMenu((Node)graphModel.getSelected(), graphModel);
            graphModel.setSelected(null);
        }
    }
}
