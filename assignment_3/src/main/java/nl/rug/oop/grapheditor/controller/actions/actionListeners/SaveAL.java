package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.util.SaveGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Save Action
 */
public class SaveAL implements ActionListener {

    private final SaveGraph saveGraph;
    private final JFileChooser jFileChooser;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param saveGraph Graph model
     * @param jFileChooser jFileChooser
     */
    public SaveAL(JMenuItem jMenuItem, SaveGraph saveGraph, JFileChooser jFileChooser) {
        this.saveGraph = saveGraph;
        this.jFileChooser = jFileChooser;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int save = jFileChooser.showSaveDialog(null);
        if(save == JFileChooser.APPROVE_OPTION) {
            saveGraph.saveFile(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
