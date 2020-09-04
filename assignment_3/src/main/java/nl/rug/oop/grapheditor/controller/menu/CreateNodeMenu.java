package nl.rug.oop.grapheditor.controller.menu;

import nl.rug.oop.grapheditor.controller.actions.CreateNodeAction;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.model.node.Node;
import nl.rug.oop.grapheditor.model.node.NodeCoords;
import nl.rug.oop.grapheditor.model.node.NodeSize;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

/**
 * Menu to add a node to a Graph model given the appropriate input
 */
public class CreateNodeMenu extends JOptionPane {

    private final GraphModel graphModel;
    private final NumberFormatter formatter;

    /**
     * Create a new menu to create a node in a graph model
     * @param graphModel Graph Model
     */
    public CreateNodeMenu(GraphModel graphModel) {
        super("Create Node Menu");
        this.graphModel = graphModel;
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        createNode();
    }

    /**
     * Creates a node in the graph model with the given input
     */
    private void createNode() {
        JTextField nameField = new JTextField();
        JFormattedTextField widthField = new JFormattedTextField(formatter);
        JFormattedTextField heightField = new JFormattedTextField(formatter);
        JFormattedTextField xField = new JFormattedTextField(formatter);
        JFormattedTextField yField = new JFormattedTextField(formatter);
        Object[] objects = {"Name:", nameField,
                            "Width:", widthField,
                            "Height:", heightField,
                            "X-Position:", xField,
                            "Y-Position:", yField};
        int option = showConfirmDialog(null, objects, "Creating a Node", OK_CANCEL_OPTION);
        String name = (nameField.getText().isEmpty()?"Generic Node":nameField.getText());
        int width = (widthField.getText().isEmpty()?30:Integer.parseInt(widthField.getText()));
        int height = (heightField.getText().isEmpty()?30:Integer.parseInt(heightField.getText()));
        int x = (xField.getText().isEmpty()?0:Integer.parseInt(xField.getText()));
        int y = (yField.getText().isEmpty()?0:Integer.parseInt(yField.getText()));
        if (option == YES_OPTION) {
            Node newNode = new Node(name, new NodeSize(width, height), new NodeCoords(x,y));
            CreateNodeAction createNodeAction = new CreateNodeAction(graphModel, newNode);
            graphModel.getUndoManager().addEdit(createNodeAction);
        }
    }
}
