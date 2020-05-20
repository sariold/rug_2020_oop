package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.controller.button.StartGameButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

public class StartGameAction extends AbstractAction {

    MagicStoneGame magicStoneGame;
    MagicStoneFrame frame;

    public StartGameAction(MagicStoneGame magicStoneGame, MagicStoneFrame frame) {
        super("Start Game");
        this.magicStoneGame = magicStoneGame;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Start game");
        frame.changeToGamePanel();
    }
}
