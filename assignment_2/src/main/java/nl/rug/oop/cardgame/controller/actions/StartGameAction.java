package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.controller.button.StartGameButton;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class StartGameAction extends AbstractAction {

    public StartGameAction() {
        super("Start Game");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Start game");
    }
}
