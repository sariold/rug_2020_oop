package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.PageAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to switch pages in the card collection
 */
public class PageButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new Page Button
     * @param mainMenu Main Menu
     * @param dir Direction
     */
    public PageButton(MainMenu mainMenu, String dir) {
        super(new PageAction(mainMenu, dir));
        setButtonProperties();
    }
}
