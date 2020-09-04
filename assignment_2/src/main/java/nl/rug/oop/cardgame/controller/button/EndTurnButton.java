package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.EndTurnAction;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to end the Turn
 */
public class EndTurnButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new End Turn button
     * @param magicStoneGame Game
     */
    public EndTurnButton(MagicStoneGame magicStoneGame) {
        super(new EndTurnAction(magicStoneGame));
        setButtonProperties();
    }

}