package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by the Card Collection Button
 */
public class CardCollectionAction extends AbstractAction {

    final MainMenu mainMenu;

    /**
     * Create new Card Collection Action
     * @param mainMenu Main Menu
     */
    public CardCollectionAction(MainMenu mainMenu) {
        super("Card Collection");
        this.mainMenu = mainMenu;
    }

    /**
     * Switch from Main menu panel to Card Collection Panel
     * @param e Action Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startCollection();
    }

}
