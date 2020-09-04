package nl.rug.oop.grapheditor.controller.menu;

import lombok.Data;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.view.frame.MainFrame;

import javax.swing.*;

/**
 * Let the user know if they need to resize window
 */
@Data
public class ResizeDialogue {

    private MainFrame frame;

    /**
     * Create new Resize Dialogue
     */
    public ResizeDialogue() {
        this.frame = null;
    }

    /**
     * Resize window!
     * @param graphModel graph model
     */
    public int[] loadingMessage(GraphModel graphModel) {
        int maxX = 0;
        int maxY = 0;
        if(this.frame != null) {
            maxX = frame.getWidth();
            maxY = frame.getHeight();
        }
        boolean changed = false;
        for(Node n: graphModel.getNodes()) {
            if (n.getNodeCoords().getCoordX()+n.getNodeSize().getSizeX() > maxX) {
                maxX = n.getNodeCoords().getCoordX()+n.getNodeSize().getSizeX();
                changed = true;
            }
            if (n.getNodeCoords().getCoordY()+n.getNodeSize().getSizeY() > maxY) {
                maxY = n.getNodeCoords().getCoordY()+n.getNodeSize().getSizeY();
                changed = true;
            }
        }
        if (changed) {

            if(JOptionPane.showConfirmDialog(null, "Would you like to resize the window to the graph size?", "Loading Graph Out Of Bounds!",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                return new int[]{maxX,maxY};
            }
        }
        return null;
    }
}
