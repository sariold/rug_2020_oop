package nl.rug.oop.cardgame.controller.button;

import nl.rug.oop.cardgame.controller.actions.EndTurnAction;
import nl.rug.oop.cardgame.controller.actions.StartGameAction;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

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

    public StartGameButton(MainMenu mainMenu) {
        super(new StartGameAction(mainMenu));
        setButtonProperties();
    }
}
