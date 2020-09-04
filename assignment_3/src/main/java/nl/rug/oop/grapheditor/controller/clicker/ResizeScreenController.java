package nl.rug.oop.grapheditor.controller.clicker;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Controller to adjust coordinates of nodes when the screen size is adjusted
 */
public class ResizeScreenController implements ComponentListener {

    private final GraphModel graphModel;
    private final GraphPanel graphPanel;

    /**
     * Create new Resize Screen Controller
     * @param graphModel Graph Model
     * @param graphPanel Graph Panel
     */
    public ResizeScreenController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.graphPanel = graphPanel;
        graphPanel.addComponentListener(this);
    }

    /**
     * Component resize aka window resize
     * @param e Component Event
     */
    @Override
    public void componentResized(ComponentEvent e) {
        System.out.println("Width:" + graphPanel.getWidth() + " Height:" + graphPanel.getHeight());
        for (Node n: graphModel.getNodes()) {
            // Node leaves the screen on the right
            if (n.getNodeCoords().getCoordX() + n.getNodeSize().getSizeX() > graphPanel.getWidth()) {
                // set X coordinate to widthPanel - widthNode
                n.setNodeCoords(new NodeCoords(graphPanel.getWidth()-n.getNodeSize().getSizeX(),
                        n.getNodeCoords().getCoordY()));
            }
            // Node leaves the screen on the left
            if (n.getNodeCoords().getCoordX()< 0) {
                // set X coordinate to zero
                n.setNodeCoords(new NodeCoords(0, n.getNodeCoords().getCoordY()));
            }
            // Node leaves the screen on the top
            // the highest point of the body of the node is less than zero
            if (n.getNodeCoords().getCoordY()< 0) {
                // set Y coordiante to zero
                System.out.println(n.getNodeCoords().getCoordY());
                n.setNodeCoords(new NodeCoords(n.getNodeCoords().getCoordX(),0));
            }
            // Node leaves the screen on the bottom
            // the lowest point of the body of the node is more than the size of the panel
            if (n.getNodeCoords().getCoordY() + n.getNodeSize().getSizeY() > graphPanel.getHeight()) {
                // set Y coordinate to heightPanel-HeightNode
                n.setNodeCoords(new NodeCoords(n.getNodeCoords().getCoordX(),
                        graphPanel.getHeight()-n.getNodeSize().getSizeY()));
            }

        }
        graphModel.notifyUpdate();
    }

    /**
     * Override to remove functionality
     * @param e event
     */
    @Override
    public void componentMoved(ComponentEvent e) {

    }
    /**
     * Override to remove functionality
     * @param e event
     */
    @Override
    public void componentShown(ComponentEvent e) {

    }
    /**
     * Override to remove functionality
     * @param e event
     */
    @Override
    public void componentHidden(ComponentEvent e) {

    }

}
