package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.StartGameAction;
import nl.rug.oop.cardgame.controller.actions.TutorialAction;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class TutorialButton extends JButton{

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public TutorialButton(MainMenu mainMenu) {
        super(new TutorialAction(mainMenu));
        setButtonProperties();
    }
}
