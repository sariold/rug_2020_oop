package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by the Main Menu Button
 */
public class MainMenuAction extends AbstractAction {

    final MainMenu mainMenu;

    /**
     * Create new Main Menu Action
     * @param mainMenu Main Menu
     */
    public MainMenuAction(MainMenu mainMenu) {
        super("Back to the Main Menu");
        this.mainMenu = mainMenu;
    }

    /**
     * Switch back to Main Menu Panel
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.goBackToMenu();
    }
}
