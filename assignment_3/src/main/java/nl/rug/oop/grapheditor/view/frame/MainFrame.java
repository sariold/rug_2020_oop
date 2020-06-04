package nl.rug.oop.grapheditor.view.frame;

import nl.rug.oop.grapheditor.model.GraphModel;
import nl.rug.oop.grapheditor.view.panel.GraphPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JFrame implements Observer {

    GraphModel graphModel;
    GraphPanel graphPanel;

    public MainFrame(GraphModel graphModel){
        super("Graph Editor");
        graphModel.addObserver(this);
        this.graphPanel = new GraphPanel(graphModel);
        graphModel.addObserver(graphPanel);
        setContentPane(graphPanel);
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
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
