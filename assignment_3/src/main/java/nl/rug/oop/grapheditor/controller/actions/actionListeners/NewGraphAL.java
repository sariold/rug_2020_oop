package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * New Graph Action
 */
public class NewGraphAL implements ActionListener {

    JMenuItem jMenuItem;
    GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public NewGraphAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.jMenuItem = jMenuItem;
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        graphModel.getNodes().clear();
        graphModel.getEdges().clear();
        graphModel.notifyUpdate();
    }
}
