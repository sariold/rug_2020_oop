package nl.rug.oop.cardgame.controller.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CardCollectionAction extends AbstractAction {

    public CardCollectionAction() {
        super("Card Collection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Card Collection");
    }

}
