package nl.rug.oop.grapheditor.view.panel;

import nl.rug.oop.grapheditor.controller.clicker.ResizeController;
import nl.rug.oop.grapheditor.controller.clicker.ResizeScreenController;
import nl.rug.oop.grapheditor.controller.clicker.SelectionController;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Graph Panel
 */
public class GraphPanel extends JPanel implements Observer {

    private final GraphModel graphModel;

    /**
     * Graph panel
     * @param graphModel graph model
     */
    public GraphPanel(GraphModel graphModel) {
        super(new BorderLayout());
        setBackground(Color.GRAY);
        this.graphModel = graphModel;
        this.graphModel.addObserver(this);
        new SelectionController(graphModel, this);
        new ResizeController(graphModel, this);
        new ResizeScreenController(graphModel, this);
    }

    /**
     * Paint the Graph model
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintNodeConnector(g);
        paintEdges(g);
        paintNodes(g);
    }


    /**
     * Draw a theoretical edge between the cursor and the selected node
     * @param g graph
     */
    private void paintNodeConnector(Graphics g) {
        if(graphModel.getSelected() != null && graphModel.getSelected() instanceof Node && !graphModel.isDragging() && !graphModel.isResizing()) {
            Node selected = (Node) graphModel.getSelected();
            g.drawLine(selected.getNodeCoords().getCoordX() + selected.getNodeSize().getSizeX()/2,
                    selected.getNodeCoords().getCoordY() + selected.getNodeSize().getSizeY()/2,
                    graphModel.getConnectorCursor().getX(),
                    graphModel.getConnectorCursor().getY());
        }
    }

    /**
     * Paint the Edges of the Graph model
     * @param g Graphics
     */
    private void paintNodes(Graphics g) {
//        System.out.println("graphing stuffs");
        NodeCoords coords;
        NodeSize size;
//        g.translate(getWidth()/2, getHeight()/2);
        for (Node n : graphModel.getNodes()) {
            coords = n.getNodeCoords();
            size = n.getNodeSize();
            if (n.equals(graphModel.getSelected()) && !graphModel.isResizing()) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.fillRect(coords.getCoordX(), coords.getCoordY(), size.getSizeX(), size.getSizeY());
            if(graphModel.isResizing() && n.equals(graphModel.getSelected())) {
                g.setColor(Color.RED);
                g.drawRect(coords.getCoordX(), coords.getCoordY(), n.getNodeSize().getSizeX(), n.getNodeSize().getSizeY());
            }
            g.setColor(Color.WHITE);
            g.drawString(n.getName(), coords.getCoordX() + (n.getNodeSize().getSizeX() / 3), coords.getCoordY() + (n.getNodeSize().getSizeY() / 2));
        }
    }

    /**
     * Paint the Nodes of the Graph model
     * @param g graphics
     */
    private void paintEdges(Graphics g) {
        NodeCoords startCoords;
        NodeSize startOffset;
        NodeCoords endCoords;
        NodeSize endOffset;
        g.setColor(Color.BLACK);
        for (Edge e : graphModel.getEdges()) {
            startCoords = e.getStart().getNodeCoords();
            endCoords = e.getEnd().getNodeCoords();
            startOffset = e.getStart().getNodeSize();
            endOffset = e.getEnd().getNodeSize();
            if (e.equals(graphModel.getSelected())) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine(startCoords.getCoordX() + startOffset.getSizeX()/2,
                    startCoords.getCoordY() + startOffset.getSizeY()/2,
                    endCoords.getCoordX() + endOffset.getSizeX()/2,
                    endCoords.getCoordY() + endOffset.getSizeY()/2);
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
