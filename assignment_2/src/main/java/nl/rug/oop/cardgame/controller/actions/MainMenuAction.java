package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainMenuAction extends AbstractAction {

    MagicStoneGame magicStoneGame;
    MagicStoneFrame frame;

    public MainMenuAction(MagicStoneFrame frame) {
        super("Back to the Main Menu");
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changeToMainMenuPanel();
    }
}
