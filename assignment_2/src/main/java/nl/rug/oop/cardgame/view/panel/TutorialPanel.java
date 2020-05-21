package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.model.menu.MainMenu;
import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class TutorialPanel extends JPanel implements Observer {

    private MainMenu mainMenu;
    private MagicStoneFrame frame;

    public TutorialPanel(MainMenu mainMenu, MagicStoneFrame frame) {
        this.mainMenu = mainMenu;
        this.frame = frame;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!mainMenu.isInTutorial()) frame.changeToMainMenuPanel();
        paintTutorial(g);
    }

    private void paintTutorial(Graphics g) {
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
