package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.controller.menu.CreateNodeMenu;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Add Node Action
 */
public class AddNodeAL implements ActionListener {

    private final GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public AddNodeAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new CreateNodeMenu(graphModel);
    }
}
