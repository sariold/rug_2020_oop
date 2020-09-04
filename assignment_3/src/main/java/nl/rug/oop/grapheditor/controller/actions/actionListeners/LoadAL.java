package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.util.LoadGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Load Action
 */
public class LoadAL implements ActionListener {

    private final LoadGraph loadGraph;
    private final JFileChooser jFileChooser;
    private GraphModel graphModel;

    /**
     * Create a new paste action
     * @param jMenuItem JMenuItem
     * @param loadGraph Graph model
     * @param jFileChooser jFileChooser
     * @param graphModel graph model
     */
    public LoadAL(JMenuItem jMenuItem, LoadGraph loadGraph, JFileChooser jFileChooser, GraphModel graphModel) {
        this.loadGraph = loadGraph;
        this.jFileChooser = jFileChooser;
        this.graphModel = graphModel;
        jMenuItem.addActionListener(this);
    }

    /**
     * Action performed
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int load = jFileChooser.showOpenDialog(null);
        if(load == JFileChooser.APPROVE_OPTION) {
            graphModel = loadGraph.loadFile(jFileChooser.getSelectedFile().getAbsolutePath());
        }
    }
}
