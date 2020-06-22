package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.EditNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

/**
 * Option Pane to edit a selected node
 */
public class EditNodeMenu extends JOptionPane {

    private final Node node;
    private final GraphModel graphModel;
    private final NumberFormatter formatter;

    /**
     * Create a new Menu to edit a node
     * @param node Node
     */
    public EditNodeMenu(Node node, GraphModel graphModel) {
        super("Edit Node Menu");
        this.node = node;
        this.graphModel = graphModel;
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        editNode();
    }

    /**
     * Edit a Node
     */
    private void editNode() {
        JTextField nameField = new JTextField();
        JFormattedTextField widthField = new JFormattedTextField(formatter);
        JFormattedTextField heightField = new JFormattedTextField(formatter);
        JFormattedTextField xField = new JFormattedTextField(formatter);
        JFormattedTextField yField = new JFormattedTextField(formatter);
        Object[] objects = {"New Name:", nameField,
                "New Width:", widthField,
                "New Height:", heightField,
                "New X-Position:", xField,
                "New Y-Position:", yField};
        int option = showConfirmDialog(null, objects, "Editing a Node", OK_CANCEL_OPTION);
        if (option == YES_OPTION) {
            String name = nameField.getText().isEmpty()?node.getName():nameField.getText();
            int width = widthField.getText().isEmpty()?node.getNodeSize().getSizeX():Integer.parseInt(widthField.getText());
            int height = heightField.getText().isEmpty()?node.getNodeSize().getSizeY():Integer.parseInt(heightField.getText());
            int x = xField.getText().isEmpty()?node.getNodeCoords().getCoordX():Integer.parseInt(xField.getText());
            int y = yField.getText().isEmpty()?node.getNodeCoords().getCoordY():Integer.parseInt(yField.getText());
            EditNodeAction editNodeAction = new EditNodeAction(node, name, new NodeCoords(x,y),
                    new NodeSize(width, height));
            graphModel.getUndoManager().addEdit(editNodeAction);
        }
    }
}
