package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Undo Action
 */
public class UndoAL implements ActionListener {

    private final GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param graphModel Graph model
     */
    public UndoAL(JMenuItem jMenuItem, GraphModel graphModel) {
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            graphModel.getUndoManager().undo();
            graphModel.setSelected(null);
        } catch (CannotUndoException ex) {
            System.out.println("CANT UNDO");
        }
    }
}
