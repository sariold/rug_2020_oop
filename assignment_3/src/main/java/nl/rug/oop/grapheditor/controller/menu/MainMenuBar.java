package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.actionListeners.*;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.util.LoadGraph;
import nl.rug.oop.grapheditor.util.SaveGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Main menu bar for graph editor options
 */
public class MainMenuBar extends JMenuBar implements Observer {

    private JMenu fileMenu, edgeMenu, nodeMenu, editMenu;
    private JMenuItem save, load, newGraph, addNode, removeNode, editNode, removeEdge, undo, redo, copy, paste;
    private GraphModel graphModel;
    private SaveGraph saveGraph;
    private LoadGraph loadGraph;
    private JFileChooser jFileChooser;

    /**
     * Create a new Menu Bar for the graph editor
     * @param graphModel graph model
     */
    public MainMenuBar(GraphModel graphModel) {
        super();
        this.graphModel = graphModel;
        this.saveGraph = new SaveGraph(this.graphModel);
        this.loadGraph = new LoadGraph(graphModel);
        this.jFileChooser = new JFileChooser();
        jFileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .sff files", "sff");
        jFileChooser.addChoosableFileFilter(restrict);
        graphModel.addObserver(this);
        this.fileMenu = new JMenu("File");
        this.edgeMenu = new JMenu("Edges");
        this.nodeMenu = new JMenu("Nodes");
        this.editMenu = new JMenu("Edit");
        this.save = new JMenuItem("Save");
        this.load = new JMenuItem("Load");
        this.newGraph = new JMenuItem("New");
        this.addNode = new JMenuItem("Add Node");
        this.removeNode = new JMenuItem("Remove Node");
        this.editNode = new JMenuItem("Edit Node");
        this.removeEdge = new JMenuItem("Remove Edge");
        this.undo = new JMenuItem("Undo");
        this.redo = new JMenuItem("Redo");
        this.copy = new JMenuItem("Copy Node");
        this.paste = new JMenuItem("Paste Node");
        addFunctionality();
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        copy.setEnabled(graphModel.getSelected() instanceof Node);
        paste.setEnabled(graphModel.getCopy() != null);
        this.fileMenu.add(newGraph);
        this.fileMenu.add(save);
        this.fileMenu.add(load);
        this.nodeMenu.add(addNode);
        this.nodeMenu.add(removeNode);
        this.nodeMenu.add(editNode);
        this.nodeMenu.add(copy);
        this.nodeMenu.add(paste);
        this.edgeMenu.add(removeEdge);
        this.editMenu.add(undo);
        this.editMenu.add(redo);
        this.add(fileMenu);
        this.add(nodeMenu);
        this.add(edgeMenu);
        this.add(editMenu);
    }

    /**
     * Sets actions to all Menu Items in this menu
     */
    private void addFunctionality() {
        addSaveAndLoadAndNew();
        addNodeFunctionality();
        addEdgeFunctionality();
        addEditFunctionality();
    }

    /**
     * Adds the possibility to undo and redo actions
     */
    private void addEditFunctionality() {
        KeyStroke keyStrokeToRedo = KeyStroke.getKeyStroke("shift control Z");
        redo.setAccelerator(keyStrokeToRedo);
        new RedoAL(redo, graphModel);
        KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        undo.setAccelerator(keyStrokeToUndo);
        new UndoAL(undo, graphModel);
    }

    /**
     * Adds the functionality to add and remove edges
     */
    private void addEdgeFunctionality() {
        KeyStroke keyStrokeToDeleteEdge = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
        removeEdge.setAccelerator(keyStrokeToDeleteEdge);
        new RemoveEdgeAL(removeEdge, graphModel);
    }

    /**
     * Adds the functionality to add and remove nodes
     */
    private void addNodeFunctionality() {
        KeyStroke keyStrokeToAddNode = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
        addNode.setAccelerator(keyStrokeToAddNode);
        new AddNodeAL(addNode, graphModel);
        KeyStroke keyStrokeToDeleteNode = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
        removeNode.setAccelerator(keyStrokeToDeleteNode);
        new RemoveNodeAL(removeNode, graphModel);
        KeyStroke keyStrokeToEdit = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        editNode.setAccelerator(keyStrokeToEdit);
        new EditNodeAL(editNode, graphModel);
        KeyStroke keyStrokeToCopyNode = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        copy.setAccelerator(keyStrokeToCopyNode);
        new CopyAL(copy, graphModel);
        KeyStroke keyStrokeToPasteNode = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
        paste.setAccelerator(keyStrokeToPasteNode);
        new PasteAL(paste, graphModel);
    }

    /**
     * Adds save and load functionality to the menu bar
     */
    private void addSaveAndLoadAndNew() {
        KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        save.setAccelerator(keyStrokeToSave);
        new SaveAL(save, saveGraph, jFileChooser);
        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        load.setAccelerator(keyStrokeToOpen);
        new LoadAL(load, loadGraph, jFileChooser, graphModel);
        KeyStroke keyStrokeToNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        newGraph.setAccelerator(keyStrokeToNew);
        new NewGraphAL(newGraph, graphModel);
    }

    /**
     * Enables or disables Buttons when a change in the Graph model occurred
     * @param o Graph Model
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println(graphModel.getUndoManager().canUndo() + " " + graphModel.getUndoManager().canRedo());
//        undo.setEnabled(graphModel.getUndoManager().canUndo());
//        redo.setEnabled(graphModel.getUndoManager().canRedo());
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        copy.setEnabled(graphModel.getSelected() instanceof Node);
        paste.setEnabled(graphModel.getCopy() != null);
    }
}
