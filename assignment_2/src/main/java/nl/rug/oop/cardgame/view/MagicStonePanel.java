package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.MagicStoneGame;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MagicStonePanel extends JPanel implements Observer {

    private MagicStoneGame magicStoneGame;
    private static final Color BACKGROUND_COLOR = new Color(0xa3, 0xa3, 0xa3);

    public MagicStonePanel(MagicStoneGame magicStoneGame) {
        this.magicStoneGame = magicStoneGame;
        magicStoneGame.addObserver(this);
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
    }

    private void paintAreas(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() / 2); // Red Half
        g.fillArc(540, -100, 200, 200, 180, 180); // AI Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 90);
        g.drawString("Deck", 1183, 90);
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 267, 90);
        g.drawString("Card 2", 417, 90);
        g.drawString("Card 3", 795, 90);
        g.drawString("Card 4", 945, 90);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("5", (getWidth() / 2) - 5, 50);
        g.setColor(Color.RED);
        g.drawRect(10, 10, 100, 150); // Discard
        g.drawRect(1159, 10, 100, 150); // Deck
        g.drawRect(250, 10, 100, 150); // Card 1
        g.drawRect(400, 10, 100, 150); // Card 2
        g.drawRect(780, 10, 100, 150); // Card 3
        g.drawRect(930, 10, 100, 150); // Card 4
        g.drawRect(140, 200, 75, 112); // Played Card 1
        g.drawRect(337, 200, 75, 112); // Played Card 2
        g.drawRect(605, 200, 75, 112); // Played Card 3
        g.drawRect(870, 200, 75, 112); // Played Card 4
        g.drawRect(1058, 200, 75, 112); // Played Card 5

        g.setColor(Color.BLUE);
        g.drawRect(0, getHeight() / 2, getWidth() - 1, getHeight() / 2); // Blue Half
        g.fillArc(540, 590, 200, 200, 0, 180); // Player Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 615);
        g.drawString("Deck", 1183, 615);
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 267, 615);
        g.drawString("Card 2", 417, 615);
        g.drawString("Card 3", 795, 615);
        g.drawString("Card 4", 945, 615);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("10", (getWidth() / 2) - 17, 670);
        g.setColor(Color.RED);
        g.drawRect(10, 530, 100, 150); // Discard
        g.drawRect(1159, 530, 100, 150); // Deck
        g.drawRect(250, 530, 100, 150); // Card 1
        g.drawRect(400, 530, 100, 150); // Card 2
        g.drawRect(780, 530, 100, 150); // Card 3
        g.drawRect(930, 530, 100, 150); // Card 4
        g.drawRect(140, 380, 75, 112); // Played Card 1
        g.drawRect(337, 380, 75, 112); // Played Card 2
        g.drawRect(605, 380, 75, 112); // Played Card 3
        g.drawRect(870, 380, 75, 112); // Played Card 4
        g.drawRect(1058, 380, 75, 112); // Played Card 5
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
//        paintDiscardPile(g);
//        paintDeck(g);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
