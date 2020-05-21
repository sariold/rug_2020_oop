package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.Main;
import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TutorialAction extends AbstractAction {

    MainMenu mainMenu;

    public TutorialAction(MainMenu mainMenu) {
        super("Tutorial");
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startTutorial();
    }
}
