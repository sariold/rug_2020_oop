package nl.rug.oop.grapheditor.controller.clicker;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.edge.Edge;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

/**
 * Clicker that allows to select a node by clicking on it.
 */
public class SelectionController extends MouseInputAdapter {

    private GraphModel graphModel;
    private GraphPanel graphPanel;
    private Node moveNode;

    /**
     * Create clicker to select a node
     * @param graphModel
     * @param graphPanel
     */
    public SelectionController(GraphModel graphModel, GraphPanel graphPanel) {
        this.graphModel = graphModel;
        this.graphPanel = graphPanel;
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
        for (Node n: graphModel.getNodes()) {
            NodeCoords coords = n.getNodeCoords();
            NodeSize size = n.getNodeSize();
            if (x >= coords.getCoordX() && x <= coords.getCoordX()+size.getSizeX() &&
                    y >= coords.getCoordY() && y <= coords.getCoordY()+size.getSizeY()) {
                if (graphModel.getSelected() == null) {
                    graphModel.setSelected(n);
                }
                else if (!n.equals(graphModel.getSelected())) {
                    graphModel.addEdge(new Edge((Node) graphModel.getSelected(), n));
                    graphModel.setSelected(null);
                }
                graphModel.notifyUpdate();
                System.out.println("NODE Interaction");
                return;
            }
        }
        graphModel.setSelected(null);
        graphModel.notifyUpdate();
        System.out.println("Not selected");
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
                if (x >= coords.getCoordX()-x && x <= coords.getCoordX()+size.getSizeX() &&
                        y >= coords.getCoordY()-y && y <= coords.getCoordY()+size.getSizeY()) {
                        this.moveNode = n;
                        break;
                }
            }
        }
        this.moveNode.setNodeCoords(new NodeCoords(x,y));
        graphModel.notifyUpdate();
    }

    /**
     * Reset the dragged Node when the mouse is released
     * @param e Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        this.moveNode = null;
    }
}
