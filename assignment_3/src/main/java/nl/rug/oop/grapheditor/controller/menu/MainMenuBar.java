package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.actionListeners.*;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.util.LoadGraph;
import nl.rug.oop.grapheditor.util.SaveGraph;
import nl.rug.oop.grapheditor.view.frame.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Main menu bar for graph editor options
 */
public class MainMenuBar extends JMenuBar implements Observer {

    private final JMenuItem save;
    private final JMenuItem load;
    private final JMenuItem newGraph;
    private final JMenuItem addNode;
    private final JMenuItem removeNode;
    private final JMenuItem editNode;
    private final JMenuItem resizeNode;
    private final JMenuItem removeEdge;
    private final JMenuItem undo;
    private final JMenuItem redo;
    private final JMenuItem copy;
    private final JMenuItem paste;
    private final GraphModel graphModel;
    private final SaveGraph saveGraph;
    private final LoadGraph loadGraph;
    private final JFileChooser jFileChooser;

    /**
     * Create a new Menu Bar for the graph editor
     * @param graphModel graph model
     */
    public MainMenuBar(GraphModel graphModel, MainFrame frame) {
        super();
        this.graphModel = graphModel;
        this.saveGraph = new SaveGraph(this.graphModel);
        this.loadGraph = new LoadGraph(graphModel, frame);
        this.jFileChooser = new JFileChooser();
        jFileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .sff files", "sff");
        jFileChooser.addChoosableFileFilter(restrict);
        graphModel.addObserver(this);
        JMenu fileMenu = new JMenu("File");
        JMenu edgeMenu = new JMenu("Edges");
        JMenu nodeMenu = new JMenu("Nodes");
        JMenu editMenu = new JMenu("Edit");
        this.save = new JMenuItem("Save");
        this.load = new JMenuItem("Load");
        this.newGraph = new JMenuItem("New");
        this.addNode = new JMenuItem("Add Node");
        this.removeNode = new JMenuItem("Remove Node");
        this.resizeNode = new JMenuItem("Resize Node");
        this.editNode = new JMenuItem("Edit Node");
        this.removeEdge = new JMenuItem("Remove Edge");
        this.undo = new JMenuItem("Undo");
        this.redo = new JMenuItem("Redo");
        this.copy = new JMenuItem("Copy Node");
        this.paste = new JMenuItem("Paste Node");
        addFunctionality();
        undo.setEnabled(graphModel.getUndoManager().canUndo());
        redo.setEnabled(graphModel.getUndoManager().canRedo());
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        resizeNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        copy.setEnabled(graphModel.getSelected() instanceof Node);
        paste.setEnabled(graphModel.getCopy() != null);
        fileMenu.add(newGraph);
        fileMenu.add(save);
        fileMenu.add(load);
        nodeMenu.add(addNode);
        nodeMenu.add(removeNode);
        nodeMenu.add(editNode);
        nodeMenu.add(resizeNode);
        nodeMenu.add(copy);
        nodeMenu.add(paste);
        edgeMenu.add(removeEdge);
        editMenu.add(undo);
        editMenu.add(redo);
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
        KeyStroke keyStrokeToResize = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        resizeNode.setAccelerator(keyStrokeToResize);
        new ResizeAL(resizeNode, graphModel);
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
        resizeNode.setEnabled(graphModel.getSelected() instanceof Node);
        undo.setEnabled(graphModel.getUndoManager().canUndo());
        redo.setEnabled(graphModel.getUndoManager().canRedo());
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        copy.setEnabled(graphModel.getSelected() instanceof Node);
        paste.setEnabled(graphModel.getCopy() != null);
    }
}
