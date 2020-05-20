package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PageAction extends AbstractAction {

    MainMenu mainMenu;
    int direction;

    public PageAction(MainMenu mainMenu, String dir) {
        super(dir);
        if (dir.equals("Next Page")) direction = 0;
        else direction = 1;
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.changeCollection(direction);
    }
}
