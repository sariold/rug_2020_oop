package nl.rug.oop.grapheditor.controller.clicker;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Resize the nodes via the mouse
 */
public class ResizeController extends MouseInputAdapter {

    private final GraphModel graphModel;
    private Node moveNode;

    /**
     * Create clicker to resize a node
     * @param graphModel graph model
     * @param graphPanel graph panel
     */
    public ResizeController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.moveNode = null;
        graphPanel.addMouseListener(this);
        graphPanel.addMouseMotionListener(this);
    }

    /**
     * Resize a selected node
     * @param e Mouse Event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (graphModel.isResizing()) {
            this.moveNode = (Node) graphModel.getSelected();
            this.moveNode.setNodeSize(new NodeSize(x - moveNode.getNodeCoords().getCoordX(), y - moveNode.getNodeCoords().getCoordY()));
            graphModel.setResizing(true);
        }
    }

    /**
     * Reset the resized Node when the mouse is released
     * @param e Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        graphModel.setResizing(false);
    }
}
