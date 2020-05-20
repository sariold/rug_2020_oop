package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.EndTurnAction;
import nl.rug.oop.cardgame.controller.actions.MainMenuAction;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MainMenuButton extends JButton{

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public MainMenuButton(MagicStoneFrame frame) {
        super(new MainMenuAction(frame));
        setButtonProperties();
    }
}
