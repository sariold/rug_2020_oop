package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.view.textures.CardBack;
import nl.rug.oop.cardgame.view.textures.CardBackTextures;
import nl.rug.oop.cardgame.view.textures.CardTextures;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
        Battlefield battlefield = magicStoneGame.getBattlefield();
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() / 2); // Red Half
        g.fillArc(540, -100, 200, 200, 180, 180); // AI Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 263);
        g.drawString("Deck", 1183, 263);
        g.drawString("Mana " + battlefield.getAi().getMana() + "/" + battlefield.getAi().getMaxMana(),
                (getWidth() / 2) - 40, 158);
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 90);
        g.drawString("Card 2", 267, 90);
        g.drawString("Card 3", 417, 90);
        g.drawString("Card 4", 795, 90);
        g.drawString("Card 5", 945, 90);
        g.drawString("Card 6", 1097, 90);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(Integer.toString(battlefield.getAi().getHealth()),
                640 - (g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getHealth()))/2), 50);
        g.setColor(Color.RED);
        g.drawRect(10, 180, 100, 150); // Discard
        g.drawRect(1159, 180, 100, 150); // Deck
        g.drawRect(100, 10, 100, 150); // Card 1
        g.drawRect(250, 10, 100, 150); // Card 2
        g.drawRect(400, 10, 100, 150); // Card 3
        g.drawRect(780, 10, 100, 150); // Card 4
        g.drawRect(930, 10, 100, 150); // Card 5
        g.drawRect(1080, 10, 100, 150); // Card 6
        g.drawRect(140, 200, 75, 112); // Played Card 1
        g.drawRect(370, 200, 75, 112); // Played Card 2
        g.drawRect(605, 200, 75, 112); // Played Card 3
        g.drawRect(830, 200, 75, 112); // Played Card 4
        g.drawRect(1058, 200, 75, 112); // Played Card 5

        g.setColor(Color.BLUE);
        g.drawRect(0, getHeight() / 2, getWidth() - 1, getHeight() / 2); // Blue Half
        g.fillArc(540, 590, 200, 200, 0, 180); // Player Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 445);
        g.drawString("Deck", 1183, 445);
        g.drawString("Mana " + battlefield.getPlayer().getMana() + "/" + battlefield.getPlayer().getMaxMana(),
                (getWidth() / 2) - 40, 555);
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 615);
        g.drawString("Card 2", 267, 615);
        g.drawString("Card 3", 417, 615);
        g.drawString("Card 4", 795, 615);
        g.drawString("Card 5", 945, 615);
        g.drawString("Card 6", 1097, 615);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString(Integer.toString(battlefield.getPlayer().getHealth()),
                640 - (g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getHealth()))/2), 670);
        g.setColor(Color.BLUE);
        g.drawRect(10, 360, 100, 150); // Discard
        g.drawRect(1159, 360, 100, 150); // Deck
        g.drawRect(100, 530, 100, 150); // Card 1
        g.drawRect(250, 530, 100, 150); // Card 2
        g.drawRect(400, 530, 100, 150); // Card 3
        g.drawRect(780, 530, 100, 150); // Card 4
        g.drawRect(930, 530, 100, 150); // Card 5
        g.drawRect(1080, 530, 100, 150); // Card 6
        g.drawRect(140, 380, 75, 112); // Played Card 1
        g.drawRect(370, 380, 75, 112); // Played Card 2
        g.drawRect(605, 380, 75, 112); // Played Card 3
        g.drawRect(830, 380, 75, 112); // Played Card 4
        g.drawRect(1058, 380, 75, 112); // Played Card 5
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
//        paintDiscardPile(g);
        paintDeck(g);
        paintHand(g);
    }

    /**
     * paint both players hands
     * @param g Graphics
     */
    private void paintHand(Graphics g) {
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        DeckHand aiHand = magicStoneGame.getBattlefield().getAi().getDeckHand();
        int playerHandSize = playerHand.getDeckHand().size();
        int aiHandSize = aiHand.getDeckHand().size();
        CardBack redBack = CardBack.RED_BACK;
        int xOffset = 150;
        int i = 0;
        for (Card c: playerHand.getDeckHand().values()) {
            EnumCard card = c.getEnumCard();
            if (i < 3) {
                g.drawImage(CardTextures.getTexture(card), 100+i*xOffset, 530, 100, 150, this);
            } else {
                g.drawImage(CardTextures.getTexture(card), 780+(i-3)*xOffset, 530, 100, 150, this);
            }
            i++;
        }
        i = 0;
        for (Card c: aiHand.getDeckHand().values()) {
            if (i < 3) {
                g.drawImage(CardBackTextures.getTexture(redBack), 100+i*xOffset, 10, 100, 150, this);
            } else {
                g.drawImage(CardBackTextures.getTexture(redBack), 780+(i-3)*xOffset, 10, 100, 150, this);
            }
            i++;
        }
    }

    /**
     * paints the deck for both players
     * @param g Graphics
     */
    private void paintDeck(Graphics g) {
        int playerCards = magicStoneGame.getBattlefield().getPlayer().getDeck().getDeckList().size();
        int aiCards = magicStoneGame.getBattlefield().getAi().getDeck().getDeckList().size();
        CardBack redBack = CardBack.RED_BACK;
        CardBack blueBack = CardBack.BLUE_BACK;
        for (int i = 0; i < playerCards; i++) {
            g.drawImage(CardBackTextures.getTexture(blueBack), 1159, 360, 100 ,150, this);
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(playerCards), 1195, 435);
        for (int i = 0; i < aiCards; i++) {
            g.drawImage(CardBackTextures.getTexture(redBack), 1159, 180, 100 ,150, this);
        }
        g.drawString(Integer.toString(aiCards), 1195, 255);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
}
