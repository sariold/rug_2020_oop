package nl.rug.oop.grapheditor.view.frame;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

    GraphModel graphModel;
    GraphPanel graphPanel;
    JMenuBar jMenuBar;
    JMenu fileMenu, edgeMenu, nodeMenu;
    JMenuItem save, load, addNode, removeNode, addEdge, removeEdge;

    public MainFrame(GraphModel graphModel){
        super("Graph Editor");
        graphModel.addObserver(this);
        this.graphPanel = new GraphPanel(graphModel);
        createMenuBar();
        setContentPane(graphPanel);
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void createMenuBar() {
        this.jMenuBar = new JMenuBar();
        this.fileMenu = new JMenu("File");
        this.edgeMenu = new JMenu("Edges");
        this.nodeMenu = new JMenu("Nodes");
        this.save = new JMenuItem("Save");
        this.load = new JMenuItem("Load");
        this.addNode = new JMenuItem("Add Node");
        this.removeNode = new JMenuItem("Remove Node");
        this.addEdge = new JMenuItem("Add Edge");
        this.removeEdge = new JMenuItem("Remove Edge");
        this.fileMenu.add(save);
        this.fileMenu.add(load);
        this.nodeMenu.add(addNode);
        this.nodeMenu.add(removeNode);
        this.edgeMenu.add(addEdge);
        this.edgeMenu.add(removeEdge);
        this.jMenuBar.add(fileMenu);
        this.jMenuBar.add(nodeMenu);
        this.jMenuBar.add(edgeMenu);
        this.setJMenuBar(jMenuBar);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
