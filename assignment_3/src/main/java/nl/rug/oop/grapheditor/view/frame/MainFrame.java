package nl.rug.oop.grapheditor.view.frame;

import nl.rug.oop.grapheditor.controller.menu.MainMenuBar;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame
 */
public class MainFrame extends JFrame {

    final GraphPanel graphPanel;
    final GraphModel graphModel;

    MainMenuBar menuBar;

    /**
     * Main frame
     * @param graphModel graph model
     */
    public MainFrame(GraphModel graphModel){
        super("Graph Editor");
        this.graphModel = graphModel;
        this.graphPanel = new GraphPanel(graphModel);

        createMenuBar();
        setContentPane(graphPanel);
        if (graphModel.getMinimumSize() != null) {
            int[] size = graphModel.getMinimumSize();
            setPreferredSize(new Dimension(size[0], size[1]));
        } else {
            setPreferredSize(new Dimension(1280, 720));
        }
        setMinimumSize(new Dimension(900,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    /**
     * Create menu bar for editing nodes and edges
     */
    private void createMenuBar() {
        this.menuBar = new MainMenuBar(graphModel, this);
        this.setJMenuBar(menuBar);
    }
}
