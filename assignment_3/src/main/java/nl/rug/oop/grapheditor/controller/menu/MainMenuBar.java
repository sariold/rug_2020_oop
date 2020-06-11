package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.CreateNodeAction;
import nl.rug.oop.grapheditor.controller.actions.RemoveEdgeAction;
import nl.rug.oop.grapheditor.controller.actions.RemoveNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.util.LoadGraph;
import nl.rug.oop.grapheditor.util.SaveGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MainMenuBar extends JMenuBar implements Observer {

    private JMenu fileMenu, edgeMenu, nodeMenu, editMenu;
    private JMenuItem save, load, newGraph, addNode, removeNode, editNode, addEdge, removeEdge, undo, redo;
    private GraphModel graphModel;
    private SaveGraph saveGraph;
    private LoadGraph loadGraph;
    private JFileChooser jFileChooser;

    /**
     * Create a new Menu Bar for the graph editor
     */
    public MainMenuBar(GraphModel graphModel){
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
        this.addEdge = new JMenuItem("Add Edge");
        this.removeEdge = new JMenuItem("Remove Edge");
        this.undo = new JMenuItem("Undo");
        this.redo = new JMenuItem("Redo");
        addFunctionality();
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
        this.fileMenu.add(newGraph);
        this.fileMenu.add(save);
        this.fileMenu.add(load);
        this.nodeMenu.add(addNode);
        this.nodeMenu.add(removeNode);
        this.nodeMenu.add(editNode);
        this.edgeMenu.add(addEdge);
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
        addNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateNodeMenu(graphModel);
            }
        });
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
    }

    /**
     * Adds save and load functionality to the menu bar
     */
    private void addSaveAndLoadAndNew() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int save = jFileChooser.showSaveDialog(null);
                if(save == JFileChooser.APPROVE_OPTION) {
                    saveGraph.saveFile(jFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int load = jFileChooser.showOpenDialog(null);
                if(load == JFileChooser.APPROVE_OPTION) {
                    graphModel = loadGraph.loadFile(jFileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
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
     * Enables or disables Buttons when a change in the Graph model occured
     * @param o Graph Model
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        removeNode.setEnabled(graphModel.getSelected() instanceof Node);
        editNode.setEnabled(graphModel.getSelected() instanceof Node);
        removeEdge.setEnabled(graphModel.getSelected() instanceof Edge);
    }
}
