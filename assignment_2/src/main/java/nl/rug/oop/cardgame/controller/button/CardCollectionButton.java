package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.CardCollectionAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to go to the Card Collection
 */
public class CardCollectionButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new Card Collection Button
     * @param mainMenu Main Menu
     */
    public CardCollectionButton(MainMenu mainMenu) {
        super(new CardCollectionAction(mainMenu));
        setButtonProperties();
    }
}
