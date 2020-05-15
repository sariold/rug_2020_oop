package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.card.SpellCard;
import nl.rug.oop.cardgame.model.deck.Deck;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.view.textures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 90);
        g.drawString("Card 2", 267, 90);
        g.drawString("Card 3", 417, 90);
        g.drawString("Card 4", 795, 90);
        g.drawString("Card 5", 945, 90);
        g.drawString("Card 6", 1097, 90);
        g.drawRect(10, 180, 100, 150); // Discard
        g.drawRect(1159, 180, 100, 150); // Deck
        g.drawRect(100, 10, 100, 150); // Card 1
        g.drawRect(250, 10, 100, 150); // Card 2
        g.drawRect(400, 10, 100, 150); // Card 3
        g.drawRect(780, 10, 100, 150); // Card 4
        g.drawRect(930, 10, 100, 150); // Card 5
        g.drawRect(1080, 10, 100, 150); // Card 6
        g.drawRect(140, 194, 90, 135); // Played Card 1
        g.drawRect(370, 194, 90, 135); // Played Card 2
        g.drawRect(600, 194, 90, 135); // Played Card 3
        g.drawRect(830, 194, 90, 135); // Played Card 4
        g.drawRect(1060, 194, 90, 135); // Played Card 5

        g.setColor(Color.WHITE);
        g.drawString("Card 1", 117, 615);
        g.drawString("Card 2", 267, 615);
        g.drawString("Card 3", 417, 615);
        g.drawString("Card 4", 795, 615);
        g.drawString("Card 5", 945, 615);
        g.drawString("Card 6", 1097, 615);

        g.drawRect(10, 360, 100, 150); // Discard
        g.drawRect(1159, 360, 100, 150); // Deck
        g.drawRect(100, 530, 100, 150); // Card 1
        g.drawRect(250, 530, 100, 150); // Card 2
        g.drawRect(400, 530, 100, 150); // Card 3
        g.drawRect(780, 530, 100, 150); // Card 4
        g.drawRect(930, 530, 100, 150); // Card 5
        g.drawRect(1080, 530, 100, 150); // Card 6
        g.drawRect(140, 360, 90, 135); // Played Card 1
        g.drawRect(370, 360, 90, 135); // Played Card 2
        g.drawRect(600, 360, 90, 135); // Played Card 3
        g.drawRect(830, 360, 90, 135); // Played Card 4
        g.drawRect(1060, 360, 90, 135); // Played Card 5
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintHeros(g);
//        paintDiscardPile(g);
        paintDeck(g);
        paintHand(g);
        paintBattlefield(g);
    }

    /**
     * Paints both heros on the battlefield
     * @param g Grpahics
     */
    private void paintHeros(Graphics g) {
        Battlefield battlefield = magicStoneGame.getBattlefield();
        g.setColor(Color.RED);
        g.drawRect(0, 0, getWidth() - 1, getHeight() / 2); // Red Half
        g.fillArc(540, -100, 200, 200, 180, 180); // AI Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.WHITE);
        g.drawString("Discard", 23, 263);
        g.drawString("Deck", 1183, 263);
        g.drawString("Mana " + battlefield.getAi().getMana() + "/" + battlefield.getAi().getMaxMana(),
                (getWidth() / 2) - 40, 158);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawImage(StatTextures.getTexture(StatEnum.BLOOD_DROP), 550, 0, 80, 88, this);
        g.drawString(Integer.toString(battlefield.getAi().getHealth()),
                590-g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getHealth()))/2, 48);
        g.drawImage(rotate(StatTextures.getTexture(StatEnum.SWORD), 315), 690, -18, 55, 110, this);
        g.drawString(Integer.toString(battlefield.getAi().getAttack()),
                710-g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getAttack()))/2, 48);

        g.setColor(Color.BLUE);
        g.drawRect(0, getHeight() / 2, getWidth() - 1, getHeight() / 2); // Blue Half
        g.fillArc(540, 590, 200, 200, 0, 180); // Player Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 445);
        g.drawString("Deck", 1183, 445);
        g.drawString("Mana " + battlefield.getPlayer().getMana() + "/" + battlefield.getPlayer().getMaxMana(),
                (getWidth() / 2) - 40, 565);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.drawImage(StatTextures.getTexture(StatEnum.BLOOD_DROP), 555, 620, 80, 88, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getHealth()),
                590-g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getHealth()))/2, 675);
        g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), 680, 620, 60, 67, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getAttack()),
                710-g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getAttack()))/2, 675);
    }

    /**
     * paint both players battlefields
     * @param g Grpahics
     */
    private void paintBattlefield(Graphics g) {
        ArrayList<CreatureCard> playerCreatures = magicStoneGame.getBattlefield().getPlayer().getPlayedCreatures();
        ArrayList<CreatureCard> aiCreatures = magicStoneGame.getBattlefield().getAi().getPlayedCreatures();
        int i = 0;
        int xOffset = 230;
        for (CreatureCard c : playerCreatures) {
            if (c == null) {
                i++;
                continue;
            }
            EnumCard card = c.getEnumCard();
            g.drawImage(CardTextures.getTexture(card), 140+i*xOffset, 360, 90, 135, this);
            String attackAndHealth = c.getAttack() + "/" + c.getHealth();
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            g.setColor(Color.BLACK);
            g.drawString(attackAndHealth, 140+i*xOffset+81-g.getFontMetrics().stringWidth(attackAndHealth),
                    512);
            i++;
        }
        i = 0;
        for (CreatureCard c : aiCreatures) {
            if (c == null)  {
                i++;
                continue;
            }
            EnumCard card = c.getEnumCard();
            g.drawImage(CardTextures.getTexture(card), 140+i*xOffset, 194, 90, 135, this);
            String attackAndHealth = c.getAttack() + "/" + c.getHealth();
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            g.setColor(Color.BLACK);
            g.drawString(attackAndHealth, 140+i*xOffset+81-g.getFontMetrics().stringWidth(attackAndHealth),
                    190);
            i++;
        }
    }

    /**
     * Ppaint both players hands
     * @param g Graphics
     */
    private void paintHand(Graphics g) {
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        DeckHand aiHand = magicStoneGame.getBattlefield().getAi().getDeckHand();
        CardBack redBack = CardBack.RED_BACK;
        int xOffset = 150;
        int i = 0;
        int x = 0;
        for (Card c: playerHand.getDeckHand().values()) {
            if (i == 6) break;
            EnumCard card = c.getEnumCard();
            if (i < 3) {
                x = 100+i*xOffset;
                g.drawImage(CardTextures.getTexture(card), x, 530, 100, 150, this);
            } else {
                x = 780+(i-3)*xOffset;
                g.drawImage(CardTextures.getTexture(card), x, 530, 100, 150, this);
            }
            if (c instanceof CreatureCard) {
                String attackAndHealth = ((CreatureCard)c).getAttack() + "/" + ((CreatureCard)c).getHealth();
                g.setFont(new Font("TimesRoman", Font.BOLD, 15));
                g.setColor(Color.BLACK);
                g.drawString(attackAndHealth, x+130-g.getFontMetrics().stringWidth(attackAndHealth),
                        670);
            } else {
                String value = String.valueOf(((SpellCard) c ).getEnumCard().getValue());
                g.drawString(value, x+130-g.getFontMetrics().stringWidth(value),
                        670);
            }
            i++;
        }
        i = 0;
        for (Card c: aiHand.getDeckHand().values()) {
            if (i == 6) break;
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
        g.drawString(Integer.toString(playerCards), 1190, 440);
        for (int i = 0; i < aiCards; i++) {
            g.drawImage(CardBackTextures.getTexture(redBack), 1159, 180, 100 ,150, this);
        }
        g.drawString(Integer.toString(aiCards), 1190, 260);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }
    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }

    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
}
