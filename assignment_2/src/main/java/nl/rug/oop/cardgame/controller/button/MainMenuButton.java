package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.MainMenuAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to go to the main menu
 */
public class MainMenuButton extends JButton{

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new Main Menu Button
     * @param mainMenu Main Menu
     */
    public MainMenuButton(MainMenu mainMenu) {
        super(new MainMenuAction(mainMenu));
        setButtonProperties();
    }
}
