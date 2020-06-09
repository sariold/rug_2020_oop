package nl.rug.oop.grapheditor.view.panel;

import nl.rug.oop.grapheditor.controller.buttons.ButtonBar;
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
    private ButtonBar buttonBar;

    public GraphPanel(GraphModel graphModel) {
        super(new BorderLayout());
        setBackground(Color.GRAY);
        this.graphModel = graphModel;
//        this.buttonBar = new ButtonBar();
//        this.add(buttonBar, BorderLayout.PAGE_START);
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
            g.setColor(Color.BLACK);
            g.fillRect(coords.getCoordX(), coords.getCoordY(), size.getSizeX(), size.getSizeY());
            g.setColor(Color.WHITE);
            g.drawString(n.getName(), coords.getCoordX(), coords.getCoordY() + g.getFontMetrics().getHeight());
        }
    }

    /**
     * Paint the Nodes of the Graph model
     * @param g
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
