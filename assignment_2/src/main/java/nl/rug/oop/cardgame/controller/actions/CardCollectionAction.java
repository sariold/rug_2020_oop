package nl.rug.oop.cardgame.controller.actions;

import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CardCollectionAction extends AbstractAction {

    MagicStoneFrame frame;

    public CardCollectionAction(MagicStoneFrame frame) {
        super("Card Collection");
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.changeToCardCollectionPanel();
    }

}
