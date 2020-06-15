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
    private int startX;
    private int startY;

    /**
     * Create clicker to resize a node
     * @param graphModel graph model
     * @param graphPanel graph panel
     */
    public ResizeController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.moveNode = null;
        this.startX = this.startY = -1;
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
            if (startY < 0 && startX < 0) {
                startY = moveNode.getNodeCoords().getCoordY();
                startX = moveNode.getNodeCoords().getCoordX();
            }
            if (x > startX && y > startY) {
                moveNode.setNodeCoords(new NodeCoords(startX, startY));
                this.moveNode.setNodeSize(new NodeSize(x - moveNode.getNodeCoords().getCoordX(), y - moveNode.getNodeCoords().getCoordY()));
            } else if (x < startX && y > startY) {
                this.moveNode.setNodeSize(new NodeSize(startX - x, y - moveNode.getNodeCoords().getCoordY()));
                this.moveNode.getNodeCoords().setCoordX(x);
                this.moveNode.getNodeCoords().setCoordY(startY);
            } else if (x > startX && y < startY) {
                this.moveNode.setNodeSize(new NodeSize(x - moveNode.getNodeCoords().getCoordX(), startY - y));
                this.moveNode.getNodeCoords().setCoordY(y);
                this.moveNode.getNodeCoords().setCoordX(startX);
            } else if (x < startX && y < startY) {
                this.moveNode.setNodeSize(new NodeSize(startX - x, startY - y));
                this.moveNode.getNodeCoords().setCoordX(x);
                this.moveNode.getNodeCoords().setCoordY(y);
            }
            graphModel.setResizing(true);
            graphModel.getConnectorCursor().setX(x);
            graphModel.getConnectorCursor().setY(y);
        }
    }

    /**
     * Reset the resized Node when the mouse is released
     * @param e Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        graphModel.setResizing(false);
        startX = startY = -1;
    }
}
