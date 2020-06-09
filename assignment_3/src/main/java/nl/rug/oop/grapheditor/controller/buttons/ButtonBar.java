package nl.rug.oop.grapheditor.controller.buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonBar extends JToolBar {

    AddNodeButton addNodeButton;
    RemoveNodeButton removeNodeButton;
    AddEdgeButton addEdgeButton;
    RemoveEdgeButton removeEdgeButton;
    UndoButton undoButton;
    RedoButton redoButton;
    SaveButton saveButton;
    LoadButton loadButton;

    public ButtonBar(){
        super("ButtonBar");
        addButtons();
        setPreferredSize(new Dimension(1280, 20));
        this.setFloatable(false);
    }

    /**
     * Create the buttons for this toolbar
     */
    private void addButtons() {
        this.addNodeButton = new AddNodeButton();
        this.removeNodeButton = new RemoveNodeButton();
        this.addEdgeButton = new AddEdgeButton();
        this.removeEdgeButton = new RemoveEdgeButton();
        this.undoButton = new UndoButton();
        this.redoButton = new RedoButton();
        this.saveButton = new SaveButton();
        this.loadButton = new LoadButton();
        this.add(addNodeButton);
        this.add(removeNodeButton);
        this.add(addEdgeButton);
        this.add(removeEdgeButton);
        this.add(undoButton);
        this.add(redoButton);
        this.add(saveButton);
        this.add(loadButton);
    }
}
