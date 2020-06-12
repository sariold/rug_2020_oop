package nl.rug.oop.grapheditor.controller.clicker;

import nl.rug.oop.grapheditor.controller.actions.CreateEdgeAction;
import nl.rug.oop.grapheditor.controller.actions.EditNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Clicker that allows to select a node by clicking on it.
 */
public class SelectionController extends MouseInputAdapter {

    private GraphModel graphModel;
    private Node moveNode;
    private NodeCoords startDragging;

    /**
     * Create clicker to select a node
     * @param graphModel
     * @param graphPanel
     */
    public SelectionController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.moveNode = null;
        graphPanel.addMouseListener(this);
        graphPanel.addMouseMotionListener(this);
    }

    /**
     * Select a node
     * If no node is selected before select the node that is clicked on
     * If there is a selected node already create an edge between the selected and the one clicked on
     * If clicked on no node set the selected node to null
     * @param e Mouse Event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        graphModel.getConnectorCursor().setX(x);
        graphModel.getConnectorCursor().setY(y);
        if(e.getButton() == MouseEvent.BUTTON1) {
            // check if a node was selected
            if (nodeSelection(x,y)) {
                graphModel.notifyUpdate();
                return;
            }
            // check if an edge was selected
            if (edgeSelection(x,y)) {
                graphModel.notifyUpdate();
                return;
            }
        }
        graphModel.setSelected(null);
        graphModel.notifyUpdate();
        System.out.println("Not selected");
    }

    /**
     * Handle selection of edges
     * @param x X position of the mouse
     * @param y Y Position of the mouse
     * @return True if an edge was selected
     */
    private boolean edgeSelection(int x, int y) {
        NodeCoords startCoords, endCoords;
        NodeSize startSize, endSize;
        int startX, startY, endX, endY;
        double distance;
        for (Edge e: graphModel.getEdges()) {
            startCoords = e.getStart().getNodeCoords();
            endCoords = e.getEnd().getNodeCoords();
            startSize = e.getStart().getNodeSize();
            endSize = e.getEnd().getNodeSize();
            startX = startCoords.getCoordX()+startSize.getSizeX()/2;
            startY = startCoords.getCoordY()+startSize.getSizeY()/2;
            endX = endCoords.getCoordX()+endSize.getSizeX()/2;
            endY = endCoords.getCoordY()+endSize.getSizeY()/2;
            distance = Line2D.ptSegDist(startX, startY, endX, endY, x, y);
            // check if the cursor is on the current edge
            if (distance < 8 && !(x >= startCoords.getCoordX() && x <= startCoords.getCoordX()+startSize.getSizeX() &&
                    y >= startCoords.getCoordY() && y <= startCoords.getCoordY()+startSize.getSizeY())&&
            !(x >= endCoords.getCoordX() && x <= endCoords.getCoordX()+endSize.getSizeX() &&
                    y >= endCoords.getCoordY() && y <= endCoords.getCoordY()+endSize.getSizeY())) {
                graphModel.setSelected(e);
                return true;
            }
        }
        return false;
    }

    /**
     * Handle selection of nodes
     * @param x X position of the mouse
     * @param y Y Position of the mouse
     * @return True if a node was selected
     */
    private boolean nodeSelection(int x, int y) {
        for (Node n: graphModel.getNodes()) {
            NodeCoords coords = n.getNodeCoords();
            NodeSize size = n.getNodeSize();
            if (x >= coords.getCoordX() && x <= coords.getCoordX()+size.getSizeX() &&
                    y >= coords.getCoordY() && y <= coords.getCoordY()+size.getSizeY()) {
                if (graphModel.getSelected() == null) {
                    graphModel.setSelected(n);
                }
                else if (!n.equals(graphModel.getSelected()) && graphModel.getSelected() instanceof Node) {
                    System.out.println("new node selected");
                    CreateEdgeAction createEdgeAction = new CreateEdgeAction(graphModel,
                            new Edge((Node) graphModel.getSelected(), n));
                    createEdgeAction.redo();
                    graphModel.getUndoManager().addEdit(createEdgeAction);
                    graphModel.setSelected(null);
                }
                graphModel.notifyUpdate();
                System.out.println("NODE Interaction");
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(graphModel.getSelected() instanceof Node) {
            graphModel.getConnectorCursor().setX(x);
            graphModel.getConnectorCursor().setY(y);
            graphModel.notifyUpdate();
        }
    }

    /**
     * Move a selected node
     * @param e Mouse Event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (this.moveNode == null) {
            for (Node n:graphModel.getNodes()) {
                NodeCoords coords = n.getNodeCoords();
                NodeSize size = n.getNodeSize();
                if (x >= coords.getCoordX() && x <= coords.getCoordX()+size.getSizeX() &&
                        y >= coords.getCoordY() && y <= coords.getCoordY()+size.getSizeY()) {
                        this.moveNode = n;
                        graphModel.setDragging(true);
                        graphModel.setSelected(n);
                        graphModel.getConnectorCursor().setX(x);
                        graphModel.getConnectorCursor().setY(y);
                        graphModel.notifyUpdate();
                        this.startDragging = n.getNodeCoords();
                        break;
                }
            }
            return;
        }
        graphModel.getConnectorCursor().setX(x);
        graphModel.getConnectorCursor().setY(y);
        this.moveNode.setNodeCoords(new NodeCoords(x-moveNode.getNodeSize().getSizeX()/2,
                                                    y-moveNode.getNodeSize().getSizeY()/2));
        graphModel.notifyUpdate();
    }

    /**
     * Reset the dragged Node when the mouse is released
     * @param e Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if(moveNode != null) {
            EditNodeAction editNodeAction = new EditNodeAction(moveNode, moveNode.getNodeCoords(), this.startDragging);
            graphModel.getUndoManager().addEdit(editNodeAction);
        }
        this.moveNode = null;
        graphModel.setDragging(false);
        graphModel.notifyUpdate();
    }
}
