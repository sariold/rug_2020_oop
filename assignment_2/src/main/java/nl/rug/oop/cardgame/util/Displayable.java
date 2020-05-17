package nl.rug.oop.cardgame.util;

import nl.rug.oop.cardgame.view.MagicStonePanel;

import java.awt.*;

public interface Displayable {

    /**
     * Displays an object in a panel
     * @param g Graphics
     * @param panel Game Panel
     */
    void display(Graphics g, MagicStonePanel panel);
}
