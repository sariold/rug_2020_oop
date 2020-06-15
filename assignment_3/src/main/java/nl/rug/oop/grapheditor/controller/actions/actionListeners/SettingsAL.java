package nl.rug.oop.grapheditor.controller.actions.actionListeners;

import nl.rug.oop.grapheditor.controller.menu.SettingsMenu;
import nl.rug.oop.grapheditor.controller.settings.Settings;
import nl.rug.oop.grapheditor.model.GraphModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsAL implements ActionListener {

    private GraphModel graphModel;

    /**
     * Create a new Settings Action
     * @param item JMenuItem
     * @param graphModel Graph Model
     */
    public SettingsAL(JMenuItem item, GraphModel graphModel) {
        this.graphModel = graphModel;
        item.addActionListener(this);
    }

    /**
     * Action performed
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new SettingsMenu(graphModel.getSettings());
    }
}
