package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by Tutorial Button
 */
public class TutorialAction extends AbstractAction {

    MainMenu mainMenu;

    /**
     * Create new Tutorial Action
     * @param mainMenu Main Menu
     */
    public TutorialAction(MainMenu mainMenu) {
        super("Tutorial");
        this.mainMenu = mainMenu;
    }

    /**
     * Switch from Main Menu Panel to Tutorial Panel
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startTutorial();
    }
}
