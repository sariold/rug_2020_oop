package nl.rug.oop.grapheditor.view.frame;

import nl.rug.oop.grapheditor.controller.menu.MainMenuBar;
import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Main Frame
 */
public class MainFrame extends JFrame implements Observer {

    final GraphPanel graphPanel;
    final GraphModel graphModel;
    MainMenuBar menuBar;

    /**
     * Main frame
     * @param graphModel graph model
     */
    public MainFrame(GraphModel graphModel){
        super("Graph Editor");
        graphModel.addObserver(this);
        this.graphModel = graphModel;
        this.graphPanel = new GraphPanel(graphModel);
        createMenuBar();
        setContentPane(graphPanel);
        setPreferredSize(new Dimension(1280, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void createMenuBar() {
        this.menuBar = new MainMenuBar(graphModel);
        this.setJMenuBar(menuBar);
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}
