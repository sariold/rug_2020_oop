package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.controller.button.StartGameButton;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

public class StartGameAction extends AbstractAction {

    MainMenu mainMenu;

    public StartGameAction(MainMenu mainMenu) {
        super("Start Game");
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startGame();
    }
}
