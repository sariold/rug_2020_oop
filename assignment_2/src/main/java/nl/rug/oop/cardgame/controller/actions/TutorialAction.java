package nl.rug.oop.cardgame.controller.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TutorialAction extends AbstractAction {

    public TutorialAction() {
        super("Tutorial");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Tutorial");
    }
}
