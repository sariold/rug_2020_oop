package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.AttackPhaseAction;
import nl.rug.oop.cardgame.controller.actions.CardClicker;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;
import nl.rug.oop.cardgame.view.panel.MagicStonePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class AttackPhaseButton extends JButton {

    /**
     * Initialise the properties of this button
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

    public AttackPhaseButton(MagicStoneGame magicStoneGame, MagicStoneFrame frame, MagicStonePanel panel, CardClicker clicker) {
        super(new AttackPhaseAction(magicStoneGame, frame, panel, clicker));
        setButtonProperties();
    }

}