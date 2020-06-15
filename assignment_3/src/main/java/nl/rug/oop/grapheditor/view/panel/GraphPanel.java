package nl.rug.oop.grapheditor.view.panel;

import nl.rug.oop.grapheditor.controller.clicker.ResizeController;
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
        setBackground(graphModel.getSettings().getBackgroundColor());
        this.graphModel = graphModel;
        this.graphModel.addObserver(this);
        SelectionController selectionController = new SelectionController(graphModel, this);
        ResizeController resizeController = new ResizeController(graphModel, this);
    }

    /**
     * Paint the Graph model
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(graphModel.getSettings().getBackgroundColor());
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
            g.setColor(graphModel.getSettings().getEdgeColor());
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
        NodeCoords coords;
        NodeSize size;
        for (Node n : graphModel.getNodes()) {
            coords = n.getNodeCoords();
            size = n.getNodeSize();
            if (n.equals(graphModel.getSelected()) && !graphModel.isResizing()) {
                g.setColor(graphModel.getSettings().getHighlightColor());
            } else {
                g.setColor(graphModel.getSettings().getNodeColor());
            }
            g.fillRect(coords.getCoordX(), coords.getCoordY(), size.getSizeX(), size.getSizeY());
            if(graphModel.isResizing() && n.equals(graphModel.getSelected())) {
                g.setColor(Color.RED);
                g.fillRect(coords.getCoordX() + size.getSizeX(), coords.getCoordY() + size.getSizeY(), 10, 10);
            }
            g.setColor(graphModel.getSettings().getTextColor());
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
        for (Edge e : graphModel.getEdges()) {
            startCoords = e.getStart().getNodeCoords();
            endCoords = e.getEnd().getNodeCoords();
            startOffset = e.getStart().getNodeSize();
            endOffset = e.getEnd().getNodeSize();
            if (e.equals(graphModel.getSelected())) {
                g.setColor(graphModel.getSettings().getHighlightColor());
            } else {
                g.setColor(graphModel.getSettings().getEdgeColor());
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
