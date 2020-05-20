package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.EndTurnAction;
import nl.rug.oop.cardgame.controller.actions.StartGameAction;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class StartGameButton extends JButton{
    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public StartGameButton() {
        super(new StartGameAction());
        setButtonProperties();
    }
}
