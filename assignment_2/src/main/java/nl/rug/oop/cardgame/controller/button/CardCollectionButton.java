package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.CardCollectionAction;
import nl.rug.oop.cardgame.controller.actions.TutorialAction;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class CardCollectionButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public CardCollectionButton(MagicStoneFrame frame) {
        super(new CardCollectionAction(frame));
        setButtonProperties();
    }
}
