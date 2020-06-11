package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.SaveAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.util.LoadGraph;
import nl.rug.oop.grapheditor.util.SaveGraph;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class MainMenuBar extends JMenuBar implements Observer {

    private JFrame frame;
    private JMenu fileMenu, edgeMenu, nodeMenu;
    private JMenuItem save, load, newGraph, addNode, removeNode, editNode, addEdge, removeEdge;
    private GraphModel graphModel;
    private SaveGraph saveGraph;
    private LoadGraph loadGraph;
    private JFileChooser jFileChooser;

    /**
     * Create a new Menu Bar for the graph editor
     */
    public MainMenuBar(GraphModel graphModel, JFrame frame){
        super();
        this.frame = frame;
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
        this.save = new JMenuItem("Save");
        this.load = new JMenuItem("Load");
        this.newGraph = new JMenuItem("New");
        this.addNode = new JMenuItem("Add Node");
        this.removeNode = new JMenuItem("Remove Node");
        this.editNode = new JMenuItem("Edit Node");
        this.addEdge = new JMenuItem("Add Edge");
        this.removeEdge = new JMenuItem("Remove Edge");
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
        this.add(fileMenu);
        this.add(nodeMenu);
        this.add(edgeMenu);
    }

    /**
     * Sets actions to all Menu Items in this menu
     */
    private void addFunctionality() {
        addSaveAndLoadAndNew();
        addNodeFunctionality();
        addEdgeFunctionality();
    }

    /**
     * Adds the funcitonality to add and remove edges
     */
    private void addEdgeFunctionality() {
        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Edge) {
                    graphModel.removeEdge((Edge)graphModel.getSelected());
                } else {
                    JOptionPane.showMessageDialog(frame, "No Edge is selected!");
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
                    graphModel.removeNode((Node)graphModel.getSelected());
                } else {
                    JOptionPane.showMessageDialog(frame, "No Node is selected!");
                }
            }
        });
        editNode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graphModel.getSelected() instanceof Node) {
                    new EditNodeMenu((Node)graphModel.getSelected());
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
