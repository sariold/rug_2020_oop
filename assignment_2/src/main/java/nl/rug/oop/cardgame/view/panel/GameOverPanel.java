package nl.rug.oop.cardgame.view.panel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GameOverPanel  extends JPanel implements Observer {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private boolean win;

    public GameOverPanel(boolean win) {
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
        this.win = win;
    }

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintText(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
