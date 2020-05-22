package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by the Page button
 */
public class PageAction extends AbstractAction {

    MainMenu mainMenu;
    int direction;

    /**
     * Create new Page Action
     * @param mainMenu Main Menu
     * @param dir Direciton
     */
    public PageAction(MainMenu mainMenu, String dir) {
        super(dir);
        if (dir.equals("Next Page")) direction = 0;
        else direction = 1;
        this.mainMenu = mainMenu;
    }

    /**
     * Change the displayed Cards in the Card collection in the given direction
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.changeCollection(direction);
    }
}
