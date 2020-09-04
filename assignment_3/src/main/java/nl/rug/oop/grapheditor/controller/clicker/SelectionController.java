package nl.rug.oop.grapheditor.controller.clicker;

import nl.rug.oop.grapheditor.controller.actions.CreateEdgeAction;
import nl.rug.oop.grapheditor.controller.actions.EditNodeAction;
import nl.rug.oop.grapheditor.controller.menu.EditNodeMenu;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.util.printer.PrintMouseInfo;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

/**
 * Clicker that allows to select a node or edge by clicking on it.
 */
public class SelectionController extends MouseInputAdapter {

    private final GraphModel graphModel;
    private final GraphPanel graphPanel;
    private Node moveNode;
    private NodeCoords startDragging;
    private boolean mouseClicked;

    /**
     * Create clicker to select a node or edge
     * @param graphModel graph model
     * @param graphPanel graph panel
     */
    public SelectionController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.moveNode = null;
        this.graphPanel = graphPanel;
        graphPanel.addMouseListener(this);
        graphPanel.addMouseMotionListener(this);
    }

    /**
     * Select graph component
     * @param e Mouse event
     */
    private void selectComponent(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        graphModel.getConnectorCursor().setX(x);
        graphModel.getConnectorCursor().setY(y);
        if(e.getButton() == MouseEvent.BUTTON1) {
            // check if a node was selected
            if (nodeSelection(x,y)) {
                if(e.getClickCount() == 2) {
                    new EditNodeMenu((Node)graphModel.getSelected(), graphModel);
                    graphModel.setSelected(null);
                }
                mouseClicked = true;
                return;
            }
            // check if an edge was selected
            if (edgeSelection(x,y)) {
                mouseClicked = true;
                return;
            }
        }
        graphModel.setSelected(null);
        System.out.println("Not selected");
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
        PrintMouseInfo.MouseClicked(e);
        selectComponent(e);
    }

    /**
     * Mouse Pressed event for dragging nodes
     * @param e Mouse Event E
     */
    @Override
    public void mousePressed(MouseEvent e) {
        PrintMouseInfo.MousePressed(e);
        selectComponent(e);
    }

    /**
     * Mouse movement so edge should follow mouse cursor
     * @param e Mouse even
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        PrintMouseInfo.MouseMoved(e);
        int x = e.getX();
        int y = e.getY();
        if(graphModel.getSelected() instanceof Node && !graphModel.isResizing()) {
            graphModel.getConnectorCursor().setX(x);
            graphModel.getConnectorCursor().setY(y);
            graphModel.isSelected();
        }
    }

    /**
     * Move a selected node
     * @param e Mouse Event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        PrintMouseInfo.MouseDragged(e);
        if(graphModel.isResizing()) return;
        int x = e.getX();
        int y = e.getY();
        if(mouseClicked) {
        if (this.moveNode == null) {
            for (Node n:graphModel.getNodes()) {
                NodeCoords coords = n.getNodeCoords();
                NodeSize size = n.getNodeSize();
                if (x >= coords.getCoordX() && x <= coords.getCoordX()+size.getSizeX() &&
                        y >= coords.getCoordY() && y <= coords.getCoordY()+size.getSizeY()) {
                        this.moveNode = n;
                        graphModel.setSelected(n);
                        graphModel.getConnectorCursor().setX(x);
                        graphModel.getConnectorCursor().setY(y);
                        this.startDragging = n.getNodeCoords();
                        graphModel.setDragging(true);
                    break;
                }
            }
            return;
        }
        dragNode(x,y);
        }
    }

    /**
     * Move the selected node to the position of the dragged mouse
     * @param x X-Position of mouse
     * @param y Y-Position of mouse
     */
    private void dragNode(int x, int y) {
        graphModel.getConnectorCursor().setX(x);
        graphModel.getConnectorCursor().setY(y);
        int xOffset;
        int yOffset;
        if (x - moveNode.getNodeSize().getSizeX() / 2 < 0) {
            xOffset = 0;
        } else if (x + moveNode.getNodeSize().getSizeX() / 2 > graphPanel.getWidth()) {
            xOffset = moveNode.getNodeCoords().getCoordX();
        } else {
            xOffset = x - moveNode.getNodeSize().getSizeX() / 2;
        }
        if (y-moveNode.getNodeSize().getSizeY()/2 < 0) {
            yOffset = 0;
        } else if (y+moveNode.getNodeSize().getSizeY()/2 > graphPanel.getHeight()) {
            yOffset = moveNode.getNodeCoords().getCoordY();
        }
        else {
            yOffset = y-moveNode.getNodeSize().getSizeY()/2;
        }
        this.moveNode.setNodeCoords(new NodeCoords(xOffset, yOffset));
        graphModel.setDragging(true);
    }

    /**
     * Reset the dragged Node when the mouse is released
     * @param e Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = false;
        PrintMouseInfo.MouseReleased(e);
        if(moveNode != null && !graphModel.isResizing()) {
            EditNodeAction editNodeAction = new EditNodeAction(moveNode, moveNode.getNodeCoords(), this.startDragging, moveNode.getNodeSize());
            graphModel.getUndoManager().addEdit(editNodeAction);
        }
        this.moveNode = null;
        graphModel.setSelected(null);
        graphModel.setDragging(false);
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
                    if(!n.shareEdge((Node) graphModel.getSelected())) {
                        System.out.println("NO SHARED EDGE!");
                        CreateEdgeAction createEdgeAction = new CreateEdgeAction(graphModel,
                                new Edge((Node) graphModel.getSelected(), n));
                        graphModel.getUndoManager().addEdit(createEdgeAction);
                    }
                    System.out.println("SHARED EDGE!");
                    graphModel.setSelected(null);
                }
                System.out.println("NODE Interaction");
                return true;
            }
        }
        return false;
    }
}
