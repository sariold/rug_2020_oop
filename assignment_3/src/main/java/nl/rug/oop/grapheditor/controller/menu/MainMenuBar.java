package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.CreateNodeAction;
import nl.rug.oop.grapheditor.controller.actions.RemoveEdgeAction;
import nl.rug.oop.grapheditor.controller.actions.RemoveNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.util.LoadGraph;
import nl.rug.oop.grapheditor.util.SaveGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
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
        KeyStroke keyStrokeToEdit = KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        editNode.setAccelerator(keyStrokeToEdit);
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
        redo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    graphModel.getUndoManager().redo();
                    graphModel.notifyUpdate();
                } catch (CannotRedoException ex) {
                    System.out.println("CANT REDO");
                }
            }
        });
        KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
        undo.setAccelerator(keyStrokeToUndo);
        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    graphModel.getUndoManager().undo();
                    graphModel.notifyUpdate();
                } catch (CannotUndoException ex) {
                    System.out.println("CANT UNDO");
                }
            }
        });
    }

    /**
     * Adds the funcitonality to add and remove edges
     */
    private void addEdgeFunctionality() {
        KeyStroke keyStrokeToDeleteEdge = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
        removeEdge.setAccelerator(keyStrokeToDeleteEdge);
        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Edge) {
                    RemoveEdgeAction removeEdgeAction = new RemoveEdgeAction(graphModel, (Edge)graphModel.getSelected());
                    removeEdgeAction.redo();
                    graphModel.getUndoManager().addEdit(removeEdgeAction);
                }
            }
        });
    }

    /**
     * Adds the functionality to add and remove nodes
     */
    private void addNodeFunctionality() {
        KeyStroke keyStrokeToAddNode = KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
        addNode.setAccelerator(keyStrokeToAddNode);
        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNodeMenu(graphModel);
            }
        });
        KeyStroke keyStrokeToDeleteNode = KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
        removeNode.setAccelerator(keyStrokeToDeleteNode);
        removeNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Node) {
                    RemoveNodeAction removeNodeAction = new RemoveNodeAction(graphModel, (Node)graphModel.getSelected());
                    removeNodeAction.redo();
                    graphModel.getUndoManager().addEdit(removeNodeAction);
                }
            }
        });
        editNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Node) {
                    new EditNodeMenu((Node)graphModel.getSelected(), graphModel);
                    graphModel.notifyUpdate();
                }
            }
        });
        KeyStroke keyStrokeToCopyNode = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
        copy.setAccelerator(keyStrokeToCopyNode);
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Node) {
                    graphModel.setCopy((Node)graphModel.getSelected());
                }
            }
        });
        KeyStroke keyStrokeToPasteNode = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
        paste.setAccelerator(keyStrokeToPasteNode);
        paste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Node newNode = graphModel.getCopy().copy();
//                newNode.move(5,5);

                newNode.setNodeCoords(new NodeCoords(graphModel.getConnectorCursor().getX(), graphModel.getConnectorCursor().getY()));
                CreateNodeAction createNodeAction = new CreateNodeAction(graphModel, newNode);
                createNodeAction.redo();
                graphModel.getUndoManager().addEdit(createNodeAction);
            }
        });
    }

    /**
     * Adds save and load functionality to the menu bar
     */
    private void addSaveAndLoadAndNew() {
        KeyStroke keyStrokeToSave = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        save.setAccelerator(keyStrokeToSave);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int save = jFileChooser.showSaveDialog(null);
                if(save == JFileChooser.APPROVE_OPTION) {
                    saveGraph.saveFile(jFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
        load.setAccelerator(keyStrokeToOpen);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int load = jFileChooser.showOpenDialog(null);
                if(load == JFileChooser.APPROVE_OPTION) {
                    graphModel = loadGraph.loadFile(jFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        KeyStroke keyStrokeToNew = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        newGraph.setAccelerator(keyStrokeToNew);
        newGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphModel.getNodes().clear();
                graphModel.getEdges().clear();
                graphModel.notifyUpdate();
            }
        });
    }

    /**
     * Enables or disables Buttons when a change in the Graph model occurred
     * @param o Graph Model
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        copy.setEnabled(graphModel.getSelected() instanceof Node);
        paste.setEnabled(graphModel.getCopy() != null);
    }
}
