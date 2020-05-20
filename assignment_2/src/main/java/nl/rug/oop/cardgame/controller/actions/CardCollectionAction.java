package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CardCollectionAction extends AbstractAction {

    MainMenu mainMenu;

    public CardCollectionAction(MainMenu mainMenu) {
        super("Card Collection");
        this.mainMenu = mainMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainMenu.startCollection();
    }

}
