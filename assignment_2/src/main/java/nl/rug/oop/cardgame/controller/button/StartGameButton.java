package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.StartGameAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to start the game
 */
public class StartGameButton extends JButton{

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create a new Start Game Button
     * @param mainMenu Main Menu
     */
    public StartGameButton(MainMenu mainMenu) {
        super(new StartGameAction(mainMenu));
        setButtonProperties();
    }
}
