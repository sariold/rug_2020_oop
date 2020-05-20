package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.MainMenuAction;
import nl.rug.oop.cardgame.controller.actions.PageAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class PageButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public PageButton(MainMenu mainMenu, String dir) {
        super(new PageAction(mainMenu, dir));
        setButtonProperties();
    }
}
