package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.view.frame.MainFrame;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

public class ResizeDialogue {

    public static void loadingMessage(GraphModel graphModel, MainFrame frame) {
        int maxX = frame.getWidth();
        int maxY = frame.getHeight();
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

            if(JOptionPane.showConfirmDialog(null, "The graph you are loading has nodes outside of" +
                    " your screen size!\nWould you like to adjust the screen size?", "Loading Graph Out Of Bounds!",
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                frame.setSize(maxX, maxY);
            }

        }
    }
}
