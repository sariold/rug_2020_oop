package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.AttackPhaseAction;
import nl.rug.oop.cardgame.controller.clicker.CardClicker;
import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button to engage attack phase
 */
public class AttackPhaseButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    /**
     * Create new attack phase button
     * @param magicStoneGame Game
     * @param clicker Card Clicker
     */
    public AttackPhaseButton(MagicStoneGame magicStoneGame, CardClicker clicker) {
        super(new AttackPhaseAction(magicStoneGame, clicker));
        setButtonProperties();
    }

}