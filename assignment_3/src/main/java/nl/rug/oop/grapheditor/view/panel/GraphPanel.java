package nl.rug.oop.grapheditor.view.panel;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GraphPanel extends JPanel implements Observer {

    private GraphModel graphModel;

    public GraphPanel(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    /**
     * Paint the Graphmodel
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintEdges(g);
        paintNodes(g);
    }

    /**
     * Paint the Edges of the Graph model
     * @param g Graphics
     */
    private void paintNodes(Graphics g) {
        NodeCoords coords;
        NodeSize size;
        for (Node n : graphModel.getNodes()) {
            coords = n.getNodeCoords();
            size = n.getNodeSize();
            g.fillRect(coords.getCoordX(), coords.getCoordY(), size.getSizeX(), size.getSizeY());
            g.drawString(n.getName(), coords.getCoordX(), coords.getCoordY());
        }
    }

    /**
     * Paint the Nodes of the Graph model
     * @param g
     */
    private void paintEdges(Graphics g) {
        NodeCoords startCoords;
        NodeCoords endCoords;
        for (Edge e : graphModel.getEdges()) {
            startCoords = e.getStart().getNodeCoords();
            endCoords = e.getEnd().getNodeCoords();
            g.drawLine(startCoords.getCoordX(), startCoords.getCoordY(), endCoords.getCoordX(), endCoords.getCoordY());
        }
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
        repaint();
    }
}
