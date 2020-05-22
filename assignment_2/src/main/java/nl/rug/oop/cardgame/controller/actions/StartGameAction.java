package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action performed by Start Game Button
 */
public class StartGameAction extends AbstractAction {

    MainMenu mainMenu;

    /**
     * Create new Start Game Action
     * @param mainMenu Main Menu
     */
    public StartGameAction(MainMenu mainMenu) {
        super("Start Game");
        this.mainMenu = mainMenu;
    }

    /**
     * Switch from Main Menu Panel to Game Panel
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startGame();
    }
}
