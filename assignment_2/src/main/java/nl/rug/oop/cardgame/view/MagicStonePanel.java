package nl.rug.oop.cardgame.view;

import nl.rug.oop.cardgame.model.Battlefield;
import nl.rug.oop.cardgame.model.MagicStoneGame;
import nl.rug.oop.cardgame.model.card.Card;
import nl.rug.oop.cardgame.model.card.CreatureCard;
import nl.rug.oop.cardgame.model.card.EnumCard;
import nl.rug.oop.cardgame.model.deck.DeckHand;
import nl.rug.oop.cardgame.model.hero.Hero;
import nl.rug.oop.cardgame.view.textures.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MagicStonePanel extends JPanel implements Observer {

    private final MagicStoneGame magicStoneGame;
    private static final Color BACKGROUND_COLOR = new Color(0xa3, 0xa3, 0xa3);

    public MagicStonePanel(MagicStoneGame magicStoneGame) {
        this.magicStoneGame = magicStoneGame;
        magicStoneGame.addObserver(this);
        setBackground(BACKGROUND_COLOR);
        setVisible(true);
        setOpaque(true);
        this.setLayout(null);
    }

    private void paintAreas(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
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
        g.drawRect(340, 194, 90, 135); // Played Card 2
        g.drawRect(540, 194, 90, 135); // Played Card 3
        g.drawRect(740, 194, 90, 135); // Played Card 4
        g.drawRect(940, 194, 90, 135); // Played Card 5

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
        g.drawRect(340, 360, 90, 135); // Played Card 2
        g.drawRect(540, 360, 90, 135); // Played Card 3
        g.drawRect(740, 360, 90, 135); // Played Card 4
        g.drawRect(940, 360, 90, 135); // Played Card 5
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintAreas(g);
        paintHeros(g);
        paintDiscardPile(g);
        paintDeck(g);
        paintHand(g);
        paintBattlefield(g);
    }

    /**
     * Paint the discard piles
     *
     * @param g Graphics
     */
    private void paintDiscardPile(Graphics g) {
        ArrayList<Card> playerDiscardPile = magicStoneGame.getBattlefield().getPlayer().getDiscardDeck().getDicardPile();
        ArrayList<Card> aiDiscardPile = magicStoneGame.getBattlefield().getAi().getDiscardDeck().getDicardPile();
        if (playerDiscardPile.size() > 0) {
            g.drawImage(CardTextures.getTexture(playerDiscardPile.get(playerDiscardPile.size() - 1).getEnumCard()),
                    10, 360, 100, 150, this);
        }
        if (aiDiscardPile.size() > 0) {
            g.drawImage(CardTextures.getTexture(aiDiscardPile.get(aiDiscardPile.size() - 1).getEnumCard()),
                    10, 180, 100, 150, this);
        }
    }

    /**
     * Paints both heros on the battlefield
     *
     * @param g Grpahics
     */
    private void paintHeros(Graphics g) {
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
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawImage(StatTextures.getTexture(StatEnum.HEART_BLUE), 570, 17, 55, 55, this);
        g.drawString(Integer.toString(battlefield.getAi().getHealth()),
                600 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getHealth())) / 2, 48);
        g.drawImage(StatTextures.getTexture(StatEnum.ATTACK_BLUE), 655, 5, 60, 67, this);
        g.drawString(Integer.toString(battlefield.getAi().getAttack()),
                685 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getAi().getAttack())) / 2, 50);

        g.setColor(Color.BLUE);
        g.drawRect(0, getHeight() / 2, getWidth() - 1, getHeight() / 2); // Blue Half
        g.fillArc(540, 590, 200, 200, 0, 180); // Player Arc
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Discard", 23, 445);
        g.drawString("Deck", 1183, 445);
        g.drawString("Mana " + battlefield.getPlayer().getMana() + "/" + battlefield.getPlayer().getMaxMana(),
                (getWidth() / 2) - 40, 565);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawImage(StatTextures.getTexture(StatEnum.HEART), 570, 630, 55, 55, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getHealth()),
                600 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getHealth())) / 2, 665);
        g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), 655, 625, 60, 67, this);
        g.drawString(Integer.toString(battlefield.getPlayer().getAttack()),
                685 - g.getFontMetrics().stringWidth(Integer.toString(battlefield.getPlayer().getAttack())) / 2, 669);
    }

    /**
     * paint both players battlefields
     *
     * @param g Grpahics
     */
    private void paintBattlefield(Graphics g) {
        g.setColor(Color.WHITE);
        ArrayList<CreatureCard> playerCreatures = magicStoneGame.getBattlefield().getPlayer().getPlayedCreatures();
        ArrayList<CreatureCard> aiCreatures = magicStoneGame.getBattlefield().getAi().getPlayedCreatures();
        int i = 0;
        int xOffset = 200;
        for (CreatureCard c : playerCreatures) {
            if (c == null) {
                i++;
                continue;
            }
            EnumCard card = c.getEnumCard();
            g.drawImage(CardTextures.getTexture(card), 140 + i * xOffset, 360, 90, 135, this);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawImage(StatTextures.getTexture(StatEnum.ATTACK), 160 + i * xOffset + 83, 370, 50, 65, this);
            g.drawString(Integer.toString(c.getAttack()), 160 + i * xOffset + 100, 415);
            g.drawImage(StatTextures.getTexture(StatEnum.HEART), 167 + i * xOffset + 77, 450, 45, 45, this);
            g.drawString(Integer.toString(c.getHealth()), 162 + i * xOffset + 100, 475);
            i++;
        }
        i = 0;
        for (CreatureCard c : aiCreatures) {
            if (c == null) {
                i++;
                continue;
            }
            EnumCard card = c.getEnumCard();
            g.drawImage(CardTextures.getTexture(card), 140 + i * xOffset, 194, 90, 135, this);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g.drawImage(StatTextures.getTexture(StatEnum.ATTACK_BLUE), 160 + i * xOffset + 83, 200, 50, 65, this);
            g.drawString(Integer.toString(c.getAttack()), 160 + i * xOffset + 100, 245);
            g.drawImage(StatTextures.getTexture(StatEnum.HEART_BLUE), 167 + i * xOffset + 77, 280, 45, 45, this);
            g.drawString(Integer.toString(c.getHealth()), 162 + i * xOffset + 100, 305);
            i++;
        }
    }

    public void paintPositions(Graphics g, Color color, boolean attack) {
        Hero player = magicStoneGame.getBattlefield().getPlayer();
        g.setColor(color);
        int xOffset = 200;
        for(int i = 0; i < 5; i++) {
            if(!attack) {
                if(player.getPlayedCreatures().get(i) == null) g.drawRect(140 + i * xOffset, 396, 90, 135);
            } else {
                if (player.getPlayedCreatures().get(i) != null) {
                    if(!player.getPlayedCreatures().get(i).isUsed()) {
                        System.out.println(player.getPlayedCreatures().get(i).toString());
                        g.drawRect(140 + i * xOffset, 396, 90, 135);
                    }
                }
            }
        }
    }

    public void paintSelected(Graphics g, int i, Color color) {
        int xOffset = 150;
        int x = 0;
        g.setColor(color);
            if (i < 3) {
                x = 100 + i * xOffset;
                g.drawRect(x, 530, 100, 150);
            } else if (i < 6){
                x = 780 + (i - 3) * xOffset;
                g.drawRect(x, 530, 100, 150);
            } else {
                g.drawRect(10, 360, 100, 150); // Discard
            }
            g.setColor(Color.BLACK);
            i++;
    }

    /**
     * Ppaint both players hands
     *
     * @param g Graphics
     */
    private void paintHand(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 15));
        DeckHand playerHand = magicStoneGame.getBattlefield().getPlayer().getDeckHand();
        DeckHand aiHand = magicStoneGame.getBattlefield().getAi().getDeckHand();
        CardBack redBack = CardBack.RED_BACK;
        int xOffset = 150;
        int i = 0;
        int x = 0;

        for (Card c: playerHand.getDeckHand().values()) {
            c.display(g, this);
            if (c instanceof CreatureCard) {
                String attackAndHealth = ((CreatureCard) c).getAttack() + "/" + ((CreatureCard) c).getHealth();
                g.drawString(attackAndHealth, x + 130 - g.getFontMetrics().stringWidth(attackAndHealth),
                        670);
            } else {
                String value = String.valueOf(c.getEnumCard().getValue());
                g.drawString(value, x + 130 - g.getFontMetrics().stringWidth(value),
                        670);
            }
            i++;
        }
        i = 0;
        for (Card c : aiHand.getDeckHand().values()) {
            if (i == 6) break;
            if (i < 3) {
                g.drawImage(CardBackTextures.getTexture(redBack), 100 + i * xOffset, 10, 100, 150, this);
            } else {
                g.drawImage(CardBackTextures.getTexture(redBack), 780 + (i - 3) * xOffset, 10, 100, 150, this);
            }
            i++;
        }
    }

    /**
     * paints the deck for both players
     *
     * @param g Graphics
     */
    private void paintDeck(Graphics g) {
        int playerCards = magicStoneGame.getBattlefield().getPlayer().getDeck().getDeckList().size();
        int aiCards = magicStoneGame.getBattlefield().getAi().getDeck().getDeckList().size();
        CardBack redBack = CardBack.RED_BACK;
        CardBack blueBack = CardBack.BLUE_BACK;
        if (playerCards > 0) {
            g.drawImage(CardBackTextures.getTexture(blueBack), 1159, 360, 100, 150, this);
        }
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(playerCards), 1190, 440);
        if (aiCards > 0) {
            g.drawImage(CardBackTextures.getTexture(redBack), 1159, 180, 100, 150, this);
        }
        g.drawString(Integer.toString(aiCards), 1190, 260);
    }

    @Override
    public void update(Observable observed, Object message) {
        repaint();
    }

}
