package nl.rug.oop.cardgame.view.panel;

import nl.rug.oop.cardgame.view.frame.MagicStoneFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Game over panel
 */
public class GameOverPanel  extends JPanel implements Observer {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private final boolean win;

    /**
     * Game over panel constructor
     * @param win who won
     * @param frame frame
     */
    public GameOverPanel(boolean win, MagicStoneFrame frame) {
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
        this.win = win;
    }

    /**
     * Paint if you won or lost
     * @param g graphics
     */
    private void paintText(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        String over = "YOU LOST!";
        if(win) {
            over = "YOU WON!";
            g.setColor(Color.BLUE);
        }
        else g.setColor(Color.RED);
        g.drawString(over, 50, 90);
    }

    /**
     * Paint component
     * @param g graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintText(g);
    }

    /**
     * Update function
     * @param o observer
     * @param arg arguments
     */
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
